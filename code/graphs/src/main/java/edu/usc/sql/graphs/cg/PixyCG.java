package edu.usc.sql.graphs.cg;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import at.ac.tuwien.infosys.www.pixy.Checker;
import at.ac.tuwien.infosys.www.pixy.analysis.interprocedural.CallGraphNode;
import at.ac.tuwien.infosys.www.pixy.analysis.interprocedural.ConnectorComputation;
import at.ac.tuwien.infosys.www.pixy.conversion.ProgramConverter;
import at.ac.tuwien.infosys.www.pixy.conversion.TacConverter;
import at.ac.tuwien.infosys.www.pixy.conversion.TacFunction;
import edu.usc.sql.graphs.Edge;
import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;


public class PixyCG extends AbstractCG {

	public PixyCG(String rootDir, String component) {
		
		String php = rootDir + File.separator + component;
		Checker checker = new Checker(php);

		// convert the whole program (with file inclusions)
		ProgramConverter programConverter = checker.initialize();
		TacConverter tac = programConverter.getTac();

		while (checker.getConnectorComp() == null) {
			checker.analyzeTaint(tac, false);
		}
		ConnectorComputation connectorComp = checker.getConnectorComp();
		at.ac.tuwien.infosys.www.pixy.analysis.interprocedural.CallGraph callgraph = connectorComp.getCallGraph();
	
		
		Set<TacFunction> allFunc = (Set<TacFunction>) callgraph.getFunctions();

		Map<String, NodeInterface> nodeMap = new HashMap<String, NodeInterface>();
		MethodAnalysisInterface methodTranslator = new MethodAnalysis();
		for (TacFunction f : allFunc) {
			String currentFunc = methodTranslator.getCanonicalName(f);
			NodeInterface node = null;
			if (nodeMap.containsKey(currentFunc)) {
				node = nodeMap.get(currentFunc);
				nodeMap.remove(currentFunc);
			} else {
				node = new Node<TacFunction>();
				node.setNodeContent(currentFunc);
			}

			// Assign Nodes and Edges
			Set<EdgeInterface> inEdges = node.getInEdges();
			Set<CallGraphNode> callers = new HashSet<CallGraphNode>();
			callers.addAll(callgraph.getCallers(f));
			if (!callers.isEmpty()) {
				for (CallGraphNode c : callers) {
					String preccessContent = methodTranslator.getCanonicalName(c.getFunction());
					NodeInterface preccessNode = null;
					if (nodeMap.containsKey(preccessContent)) {
						preccessNode = nodeMap.get(preccessContent);
						nodeMap.remove(preccessContent);
					} else {
						preccessNode = new Node<TacFunction>();
						preccessNode.setNodeContent(preccessContent);
					}
					EdgeInterface edge = new Edge(preccessNode, node, "");
					Set<EdgeInterface> outEdges = preccessNode.getOutEdges();
					outEdges.add(edge);
					preccessNode.setOutEdges(outEdges);
					nodeMap.put(preccessContent, preccessNode);
					inEdges.add(edge);
				}
			}
			node.setInEdges(inEdges);
			nodeMap.put(currentFunc, node);
		}
		
		this.nodeSet = new HashSet<NodeInterface>();
		nodeSet.addAll(nodeMap.values());
	}
	


}
