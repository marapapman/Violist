package usc.sql.string;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import soot.Unit;
import usc.sql.ir.InterRe;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;
import edu.usc.sql.graphs.cdg.Dominator;
import edu.usc.sql.graphs.cdg.PostDominator;
import edu.usc.sql.graphs.cfg.BuildCFGs;
import edu.usc.sql.graphs.cfg.CFGInterface;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Map<String, CFGInterface> result = BuildCFGs
				.buildCFGs(
						"./target/classes",
						"usc/sql/string/testcase/SimpleTest.class");
		//System.out.println(result);
		
		String methodName = "<usc.sql.string.testcase.SimpleTest: void main(java.lang.String[])>";
		long start = System.currentTimeMillis();
		CFGInterface cfg = result.get(methodName);

		 
		//acacbcacacbcacbacbcacacbcacbacacbcacbacbc
		//acacbcacacbcacbacbca acbcacbacacbcacbacbc

		/*		
	    	System.out.println("OH yeah");
			Dominator pd = new Dominator(cfg.getAllNodes(),cfg.getAllEdges(),cfg.getEntryNode());

	 		Map<NodeInterface,List<NodeInterface>> postDominateTable = pd.getDominatorTable();
			for(Entry<NodeInterface,List<NodeInterface>> en:postDominateTable.entrySet())
			{
				System.out.print(en.getKey()+": ");
				for(NodeInterface n:en.getValue())
				System.out.print(n+" ");
				System.out.println();
			}
		*/
		
		//System.out.println(cfg.toDot());

		/*
		Reachability r = new Reachability(cfg.getAllNodes(), cfg.getAllEdges());
		r.computeReachability();
		
		List<RNode> allTheLists = new ArrayList<>();

		for (RNode rn : r.getRNode())
		{
			allTheLists.add(rn);
		}		
		
		Collections.sort(allTheLists, new Comparator<RNode>() {
			public int compare(RNode a1, RNode a2) {
				return Integer.parseInt(a1.getNode().getName()) - Integer.parseInt(a2.getNode().getName()); // assumes you want biggest to
												// smallest
			}
		});
		for (RNode rn : allTheLists) {
		  System.out.print(rn.getNode().getName() + ": ");
			System.out.print(((Node<Unit>)rn.getNode()).getActualNode()+" ");
			if(((Node<Unit>)rn.getNode()).getActualNode()!=null)
			{
				System.out.print(((Node<Unit>)rn.getNode()).getActualNode().getUseBoxes());
			}
			System.out.println();
		}
		*/

		LayerRegion lll = new LayerRegion(null);
		ReachingDefinition rd = new ReachingDefinition(cfg.getAllNodes(), cfg.getAllEdges(),lll.identifyBackEdges(cfg.getAllNodes(),cfg.getAllEdges(), cfg.getEntryNode()));	
		rd.outputToConsole();
		//rd.getInSet(n)	
		//System.out.println(rd.toDot());
		
		List<NodeInterface> tp= rd.topoSort(cfg.getAllNodes(),lll.identifyBackEdges(cfg.getAllNodes(),cfg.getAllEdges(), cfg.getEntryNode()));
		for(NodeInterface n:tp)
		{
		//	System.out.println(n.getName());
		}
		
		
		LayerRegion lr = new LayerRegion(cfg);
		RegionNode root = lr.getRoot();
		
		//lr.tranverseRTree(root,rd); 
		

		/*
		for(NodeInterface n:cfg.getAllNodes())
		{
			System.out.println(n.getName()+" "+ rd.getAllDef().get(n.getName()));
			System.out.println(((Node<Unit>)n).getActualNode());
			if(((Node)n).getActualNode()!=null)
			System.out.println(((Node<Unit>)n).getActualNode().getUseBoxes());
		}*/
		
		/*
		System.out.println("result: ");
		for(String s: ir.getUseMap().keySet())
		{
			
			System.out.println(s+" "+rd.getAllDef().get(s)+" = "+ir.getUseMap().get(s).toString());
		}
		
		System.out.println();		
		*/
		//InterReRD irrd = new InterReRD(cfg.getAllNodes(), rd, ir);
		//System.out.println(irrd.toHtml("19"));
 
		Translator t = new Translator(rd, lr,methodName,"/home/yingjun/Documents/StringAnalysis/MethodSummary/");
		
		Interpreter intp = new Interpreter(t,2);

		
		System.out.println();
		int lengthCount =0;
		
		for(Entry<String,List<String>> en:t.getTargetLines().entrySet())
		{
			System.out.println("Label = "+en.getKey());
			int count = 0;
			Set<String> value = new HashSet<>();
			
			for(String line: en.getValue())
			{
				value.addAll(intp.getValue(line));

				System.out.print("\n"+line+" "+rd.getAllDef().get(line)+" = \n");
			
				for(String output: intp.getValue(line))
					{
				count++;
					System.out.println(output+" ");
					if(output.length()<=11)
						lengthCount++;
					}
			}
			System.out.println("count: "+count);
			System.out.println("length count: "+lengthCount);
		//	System.out.println(value);
		}

		//System.out.println("\n"+t.toHtml("16"));
		long end = System.currentTimeMillis();
		System.out.println("\n"+"Running Time:"+(end-start)+ "ms");
		
		
	}

}
