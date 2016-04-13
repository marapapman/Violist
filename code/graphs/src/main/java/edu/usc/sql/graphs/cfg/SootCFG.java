/**
 * 
 */
package edu.usc.sql.graphs.cfg;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soot.PhaseOptions;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.IdentityStmt;
import soot.jimple.Stmt;
import soot.jimple.internal.JCaughtExceptionRef;
import soot.options.Options;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.DirectedGraph;
import soot.util.Chain;
import edu.usc.sql.graphs.Edge;
import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;

/**
 * @author mian
 *
 */
public class SootCFG extends AbstractCFG {
	private String methodsig="";
	public static Map<String, CFGInterface> buildCFGs(String rootDir, String component) {

		String classPath = rootDir;
		String sootClassPath = Scene.v().getSootClassPath() + File.pathSeparator + classPath;
		
		//System.out.println(sootClassPath);
		
		Scene.v().setSootClassPath(sootClassPath);

		Options.v().set_keep_line_number(true);
		Options.v().set_keep_offset(true);
		//PhaseOptions.v().setPhaseOption("jb","use-original-names");
		
		String className = component.replace(File.separator, ".");
		className = className.substring(0, className.length()-6);

		SootClass sootClass = Scene.v().loadClassAndSupport(className);
		Scene.v().loadNecessaryClasses();
		sootClass.setApplicationClass();
		
		List<SootMethod> methodList = sootClass.getMethods();

		Map<String, CFGInterface> cfgMap = new HashMap<String, CFGInterface>();
		for (SootMethod sm : methodList) {
			CFGInterface sootCfg = new SootCFG(sm.toString(),sm);
			cfgMap.put(sm.toString(), sootCfg);
		}
		
		return cfgMap;
	}
	public String getSignature(){
		return this.methodsig;
	}
	public SootCFG(String graphName, SootMethod method) {
		this.methodsig=method.getSignature();
		DirectedGraph<Unit> sootCfg = new BriefUnitGraph(method.retrieveActiveBody());
		//System.out.println(method);
		//Later added into Set.
		Node<Unit> entry = new Node<Unit>();
		entry.setName("entry");
		entry.setNodeContent("entry");
		entry.setActualNode(null);
		entry.setOffset(-1);

		Node<Unit> exit = new Node<Unit>();
		exit.setName("exit");
		exit.setNodeContent("exit");
		exit.setActualNode(null);
		exit.setOffset(sootCfg.size()+1);
		HashMap<Unit, Integer> offsetmap=new HashMap<Unit, Integer>();
		
		// get a snapshot iterator of the unit since we are going to
		// mutate the chain when iterating over it.
		//
		Iterator stmtIt = sootCfg.iterator();
		int offset=0;
		// typical while loop for iterating over each statement
		while (stmtIt.hasNext()) {

			// cast back to a statement.
			Unit stmt = (Unit) stmtIt.next();
			offsetmap.put(stmt, offset);
			offset++;
		}
		
		// Without entry & exit nodes
		Map<Unit, Node<Unit>> nodeMap = new HashMap<Unit, Node<Unit>>();

		Set<EdgeInterface> entryOutEdges = entry.getOutEdges();

		// Setting the entry node's outEdges
		for(Unit u : sootCfg.getHeads()) {
			Node<Unit> successNode = null;
			if (nodeMap.containsKey(u)) {
				successNode = nodeMap.get(u);
				nodeMap.remove(u);
			} else {
				successNode = new Node<Unit>();
				successNode.setNodeContent(u.toString());
				successNode.setActualNode(u);
				//****************************
				//     For WAM 
				//****************************
				if(isMethodEntryPoint(sootCfg, u)) {
					successNode.setEntryPoint(true);
				}
			}
			successNode.setOffset(offsetmap.get(u));

			EdgeInterface edge;
			if(successNode.isEntryPoint())
				edge = new Edge(entry, successNode, "real");
			else 
				edge = new Edge(entry, successNode, "fake");
			Set<EdgeInterface> inEdges = successNode.getInEdges();
			inEdges.add(edge);
			successNode.setInEdges(inEdges);
			nodeMap.put(u, successNode);
			entryOutEdges.add(edge);
		}
		entry.setOutEdges(entryOutEdges);


		Set<EdgeInterface> exitInEdges = exit.getInEdges();
		// Setting the exit node's inEdges
		for(Unit u : sootCfg.getTails()) {
			Node<Unit> precessNode = null;
			if (nodeMap.containsKey(u)) {
				precessNode = nodeMap.get(u);
				nodeMap.remove(u.toString());
			} else {
				precessNode = new Node<Unit>();
				precessNode.setNodeContent(u.toString());
				precessNode.setActualNode(u);
				precessNode.setTail(true);
			}
			precessNode.setOffset(offsetmap.get(u));

			EdgeInterface edge = new Edge(precessNode, exit, "");
			Set<EdgeInterface> outEdges = precessNode.getOutEdges();
			outEdges.add(edge);
			precessNode.setOutEdges(outEdges);

			nodeMap.put(u, precessNode);
			exitInEdges.add(edge);
		}
		exit.setInEdges(exitInEdges);

		Iterator<Unit> it = sootCfg.iterator();
		while (it.hasNext()) {
			Unit u = it.next();
			Node<Unit> node = null;
			if (nodeMap.containsKey(u)) {
				node = nodeMap.get(u);
				node.setOffset(offsetmap.get(u));
				nodeMap.remove(u.toString());
			} else {
				node = new Node<Unit>();
				node.setNodeContent(u.toString());
				node.setActualNode(u);
				node.setOffset(offsetmap.get(u));

				
			}

			Set<EdgeInterface> outEdges = node.getOutEdges();
			List<Unit> successors = sootCfg.getSuccsOf(u);

			if(!successors.isEmpty()) {
				for (Unit succU : successors) {
					Node<Unit> successNode = null;
					if (nodeMap.containsKey(succU)) {
						successNode = nodeMap.get(succU);
						nodeMap.remove(succU);
					} else {
						successNode = new Node<Unit>();
						successNode.setNodeContent(succU.toString());
						successNode.setActualNode(succU);
					}
					successNode.setOffset(offsetmap.get(succU));

					EdgeInterface edge = new Edge(node, successNode, "");
					Set<EdgeInterface> inEdges = successNode.getInEdges();
					try{
					inEdges.add(edge);
					}
					catch(Exception e){
						System.out.println("offset "+node.getNodeContent());
						throw e;
					}
					successNode.setInEdges(inEdges);

					nodeMap.put(succU, successNode);

					outEdges.add(edge);
				}
			}
			node.setOutEdges(outEdges);
			node.setOffset(offsetmap.get(u));

			nodeMap.put(u, node);
		}

		//Initialize the member's of cfg
		this.name = name; 
		//System.out.println(nodeMap.values().size());
		nodeSet = new HashSet<NodeInterface>();
		nodeSet.add(entry);
		nodeSet.add(exit);
		nodeSet.addAll(nodeMap.values());
	}



	//*************************************************
	//    Copy and modify from WAM's Analysis.java
	//*************************************************
	private boolean isMethodEntryPoint(DirectedGraph<Unit> cfg, Unit s) {
		if (cfg.getHeads().contains(s)) {
			if (s instanceof IdentityStmt) {
				IdentityStmt is = (IdentityStmt)s;
				if (is.getRightOp() instanceof JCaughtExceptionRef) {
					return false;
				} 
			}
			return true;
		}
		return false;
	}
}
