package edu.usc.sql.graphs.cg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import soot.MethodOrMethodContext;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Targets;
import soot.options.Options;
import edu.usc.sql.graphs.Edge;
import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;


public class SootCG extends AbstractCG {

	static final private String PROPERTY_FILE = "properties.txt";
	static final private String JCE_PROPERTY = "jce";
	static private String jce_classpath;

	static  {
		try { 
			Properties defaultProps = new Properties();
			FileInputStream in = new FileInputStream(PROPERTY_FILE);
			defaultProps.load(in);
			in.close();
			jce_classpath = defaultProps.getProperty(JCE_PROPERTY);
		} catch (FileNotFoundException fnfe) {
			throw new ExceptionInInitializerError(fnfe);
		} catch (IOException ioe) {
			throw new ExceptionInInitializerError(ioe);
		}
	}

	public SootCG(String rootDir, Set<String> classnames) {

	}

	public SootCG(String rootDir, String classname) throws Exception {

		String sootClassPath = Scene.v().getSootClassPath()	+ File.pathSeparator + rootDir + File.pathSeparator + jce_classpath;
		Scene.v().setSootClassPath(sootClassPath);

		Options.v().set_keep_line_number(true);
		Options.v().set_whole_program(true);

		classname = classname.substring(0, classname.length() - 6);

		SootClass c = Scene.v().loadClassAndSupport(classname);
		Scene.v().loadNecessaryClasses();
		c.setApplicationClass();

		List<SootMethod> entryPoints = getEntryPoints(c);
		if (entryPoints.isEmpty()) {
			throw new Exception("No entry points found in " + classname);
		}
		Scene.v().setEntryPoints(entryPoints);
		Scene.v().setMainClass(c);

		CHATransformer.v().transform();
		CallGraph cg = Scene.v().getCallGraph();


		List<SootMethod> methodList = c.getMethods();
		MethodAnalysisInterface methodTranslator = new MethodAnalysis();
		Map<String, NodeInterface> nodeMap = new HashMap<String, NodeInterface>();

		for (SootMethod sm : methodList) {
			String currentFunc = methodTranslator.getCanoicalName(sm);

			NodeInterface node = null;
			if (nodeMap.containsKey(currentFunc)) {
				node = nodeMap.get(currentFunc);
				nodeMap.remove(currentFunc);
			} else {
				node = new Node<Unit>();
				node.setNodeContent(currentFunc);
			}

			Set<EdgeInterface> outEdges = node.getOutEdges();

			Iterator<MethodOrMethodContext> targets = new Targets(cg.edgesOutOf(sm));
			while (targets.hasNext()) {
				SootMethod tgt = (SootMethod) targets.next();
				String succ = methodTranslator.getCanoicalName(tgt);

				NodeInterface successNode = null;
				if (nodeMap.containsKey(succ)) {
					successNode = nodeMap.get(succ);
					nodeMap.remove(succ);
				} else {
					successNode = new Node<SootMethod>();
					successNode.setNodeContent(succ);
				}
				EdgeInterface edge = new Edge(node, successNode, "");

				Set<EdgeInterface> inEdges = successNode.getInEdges();
				inEdges.add(edge);
				successNode.setInEdges(inEdges);
				nodeMap.put(succ, successNode);
				outEdges.add(edge);
				// System.out.println(tgt.getSignature());
			}
			node.setOutEdges(outEdges);
			nodeMap.put(currentFunc, node);
		}

		this.nodeSet = new HashSet<NodeInterface>();
		nodeSet.addAll(nodeMap.values());
	}

	private static List<SootMethod> getEntryPoints(SootClass c) {
		List<SootMethod> entryPoints = new ArrayList<SootMethod>();
		if (c.declaresMethodByName("main")) {
			entryPoints.add(c.getMethodByName("main"));
		}
		if (c.declaresMethodByName("_jspService")) {
			entryPoints.add(c.getMethodByName("_jspService"));
		}
		if (c.declaresMethodByName("service")) {
			entryPoints.add(c.getMethodByName("service"));
		}
		if (c.declaresMethodByName("doPost")) {
			entryPoints.add(c.getMethodByName("doPost"));
		}
		if (c.declaresMethodByName("doGet")) {
			entryPoints.add(c.getMethodByName("doGet"));
		}

		return entryPoints;
	}
}
