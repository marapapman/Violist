package edu.usc.sql.graphs.cg;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;

import edu.usc.sql.graphs.Edge;
import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;

public class BcelCG extends AbstractCG {

	private Map<String, Set<String>> callMap = new HashMap<String, Set<String>>();
	
	public BcelCG(String classFileName) {
		JavaClass cls = null;
		try {
			cls = (new ClassParser( classFileName )).parse();
		} catch (IOException e) {
			e.printStackTrace( System.out );
			System.out.println( "Error while parsing " + classFileName + "." );
			System.exit( 1 );
		}
		
		ConstantPool cp = cls.getConstantPool();
		ConstantPoolGen cpg = new ConstantPoolGen(cp);
		
		MethodAnalysisInterface methodTranslator = new MethodAnalysis(); 
		for(Method m : cls.getMethods()) {
			InstructionList insList = new InstructionList(m.getCode().getCode());
			InstructionHandle[] handles = insList.getInstructionHandles();
			String caller = methodTranslator.getCanonicalName(m);
			Set<String> calleeSet = new HashSet<String>();
			for (InstructionHandle h : handles) {
				Instruction in = h.getInstruction();
				if (in instanceof InvokeInstruction) {
					String callee = methodTranslator.getCanonicalName((InvokeInstruction) in, cpg);
					if (!calleeSet.contains(callee))
						calleeSet.add(callee);
				}
			}
			if (!callMap.containsKey(caller)) {
				callMap.put(caller, calleeSet);
			} else {
				System.err.println("Errors in callMap Initialization!");
			}
		}
		
		Map<String, NodeInterface> nodeMap = new HashMap<String, NodeInterface>();
		for (String currentFunc: callMap.keySet()) {
			if(currentFunc.equals("<init>"))
				continue;
			NodeInterface node = null;
        	if (nodeMap.containsKey(currentFunc)) {
        		node = nodeMap.get(currentFunc);
        		nodeMap.remove(currentFunc);
        	} else {
        		node = new Node<String>();
        		node.setNodeContent(currentFunc);;
        	}
        	
        	// Assign Nodes and Edges
        	Set<EdgeInterface> outEdges = node.getOutEdges();
        	Set<String> callees = callMap.get(currentFunc);
        	
			if (!callees.isEmpty()) {
				for(String succ : callees) {
			        NodeInterface successNode = null;
			        if(nodeMap.containsKey(succ)) {
			        	successNode = nodeMap.get(succ);
			        	nodeMap.remove(succ);
			        } else {
			        	successNode = new Node<String>();
			        	successNode.setNodeContent(succ);
			        }
			        EdgeInterface edge = new Edge(node, successNode, "");
		            Set<EdgeInterface> inEdges = successNode.getInEdges();
		            inEdges.add(edge);
		            successNode.setInEdges(inEdges);
		            nodeMap.put(succ, successNode);
		            outEdges.add(edge);
				}
			}
			node.setOutEdges(outEdges);
			nodeMap.put(currentFunc, node);
		}
		
		this.sourceFile = classFileName.substring(classFileName.lastIndexOf(File.separator) + 1);;
		this.nodeSet = new HashSet<NodeInterface>();
		nodeSet.addAll(nodeMap.values());
	}

}
