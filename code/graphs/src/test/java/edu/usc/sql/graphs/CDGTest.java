package edu.usc.sql.graphs;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import soot.toolkits.graph.BriefUnitGraph;
import edu.usc.sql.graphs.cdg.CDG;
import edu.usc.sql.graphs.cdg.Dominator;
import edu.usc.sql.graphs.cdg.PostDominator;
import edu.usc.sql.graphs.cfg.CFGInterface;
import edu.usc.sql.graphs.cfg.SootCFG;

public class CDGTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Test program to build CFG from a class and generate Dominance, Post Dominance and CDG of individual nodes
		
		
		Map<String, CFGInterface> cfgMap = new HashMap<String, CFGInterface>();
		 
		 cfgMap = SootCFG.buildCFGs("/resources/", "hw/Subject3.class"); 

		 CFGInterface cfg = cfgMap.get("<hw.Subject3: void main(java.lang.String[])>");

		 
		 System.out.println("Edges:"+cfg.getAllEdges());
		 System.out.println("Nodes"+cfg.getAllNodes());
		 
		 
		 //Adding Entry and Exit node to the CFG
//		 Node entry = new Node("Entry");
//		 Node exit1 = new Node("Exit");
//		 
//		 
//		 Edge entryEdge = new Edge(entry,cfg.getEntryNode());
//		 cfg.getAllEdges().add(entryEdge);
//		 entry.addOutEdge(entryEdge);
//		 
//		 
//		 Set<EdgeInterface> temp1=cfg.getEntryNode().getInEdges();
//		 temp1.add(entryEdge);
//		 cfg.getEntryNode().setInEdges(temp1);
//		
//		 
//		 Edge exitEdge = new Edge(cfg.getExitNode(),exit1);
//		 cfg.getAllEdges().add(exitEdge);
//		 
//		 exit1.addInEdge(entryEdge);
//		 
//		 Set<EdgeInterface> temp2=cfg.getExitNode().getOutEdges();
//		 temp2.add(exitEdge);
//		 
//		 cfg.getExitNode().setOutEdges(temp2);
		 
			
		//Add a Start node with T to Entry and F to Exit to the CFG
			Node start= new Node("Start");
			Edge startEntry = new Edge(start,cfg.getEntryNode(),"True");
			cfg.getAllEdges().add(startEntry);
			Edge startExit = new Edge(start,cfg.getExitNode(),"False");
			cfg.getAllEdges().add(startExit);
			
			start.addOutEdge(startEntry);
			start.addOutEdge(startExit);
			
			Set<EdgeInterface> temp1=cfg.getEntryNode().getInEdges();
			 temp1.add(startEntry);
			 cfg.getEntryNode().setInEdges(temp1);
			
				Set<EdgeInterface> temp2=cfg.getExitNode().getInEdges();
				 temp2.add(startExit);
				 cfg.getExitNode().setInEdges(temp2);
			
			

			cfg.getAllNodes().add(start);

		 System.out.println(cfg.toDot());
		 
		 System.out.println("Calculating Dominance:");
		 
		 Dominator Dom = new Dominator(cfg.getAllNodes(), cfg.getAllEdges(), cfg.getEntryNode());
		 
		 PostDominator PostDom = new PostDominator(cfg.getAllNodes(), cfg.getAllEdges(), cfg.getExitNode());
		 
		 CDG cdg = new CDG(cfg.getAllNodes(), cfg.getAllEdges(), cfg.getExitNode());
		 
		 System.out.println("Node and its dependants:"); 
		  
		 
		 System.out.println(cdg.getControlDependantGraph().toDot());
		
	}

}
