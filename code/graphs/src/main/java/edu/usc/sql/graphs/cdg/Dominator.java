package edu.usc.sql.graphs.cdg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;

public class Dominator {
	private Set<NodeInterface> allNode;
	private Set<EdgeInterface> allEdge;
	private NodeInterface entry;
//	private Set<DNode> allDNode = new HashSet<>();
	private Map<NodeInterface,Set<NodeInterface>> domSet = new HashMap<>();
	private Map<NodeInterface,NodeInterface> immediateDomMap = new HashMap<>();
	private Map<NodeInterface,List<NodeInterface>> dTable = new HashMap<>();
	
	public Dominator(Set<NodeInterface> allNode,Set<EdgeInterface> allEdge,NodeInterface entry)
	{
		this.allNode = allNode;
		this.allEdge = allEdge;
	    this.entry = entry;
		for(NodeInterface n:allNode)
		{	
			Set<NodeInterface> dSet = new HashSet<>();
			domSet.put(n, dSet);
			
			List<NodeInterface> temp = new ArrayList<>();
			dTable.put(n,temp);
		}
		computeDominator();
		//computeImmediateDominator();
	}
	private void initialize()
	{
		for(NodeInterface n:allNode)
		{
			if(n.getInEdges().isEmpty())
			{
				domSet.get(n).add(n);
			}
			else
			{
				for(NodeInterface nn: allNode)
					domSet.get(n).add(nn);
			}

		}
	}
	private boolean compareTwoSet(Set<NodeInterface> oldset,Set<NodeInterface> newset)
	{
		if(oldset.containsAll(newset)&&newset.containsAll(oldset))
			return true;
		else 
			return false;
	}
	public boolean isDominate(NodeInterface source,NodeInterface dest)
	{
		for(NodeInterface n:allNode)
		{
			if(n.equals(dest))
				if(domSet.get(n).contains(source))
					return true;
		}
		return false;
	}
	public void computeDominator()
	{
		initialize();

		boolean change = true;
		
		while(change)
		{
			
			
			change = false;
			for(NodeInterface n: allNode)
			{
				//exclude the entry node
				if(!n.getInEdges().isEmpty())
				{
					Set<NodeInterface> temp = new HashSet<>();
					
					//initialize
		
					//NodeInterface first = preList.get(n).get(0);
					NodeInterface first = n.getInEdges().iterator().next().getSource();
					for(NodeInterface nn: domSet.get(first))
						temp.add(nn);
					//intersect

					for(EdgeInterface e:n.getInEdges())
						temp.retainAll(domSet.get(e.getSource()));
					//union itself
					if(!temp.contains(n))
						temp.add(n);
										
					
					if(!compareTwoSet(domSet.get(n), temp))
					{
						change=true;
						domSet.get(n).clear();
						for(NodeInterface nn:temp)
						{
							domSet.get(n).add(nn);
						}
		
					}
				}
			}
		}
		/*
		for(NodeInterface n: allNode)
		{
			System.out.print(n.getName()+": ");
			for(NodeInterface in:domSet.get(n))
				System.out.print(in.getName()+" ");
			System.out.println("size: "+domSet.get(n).size());
		}
		*/
		
	}
	
	public Map<NodeInterface,Set<NodeInterface>> getDominatorSet()
	{
		return domSet;
	}
	
	public Map<NodeInterface,List<NodeInterface>> getDominatorTable()
	{
		for(NodeInterface dn: allNode)
		{
			for(NodeInterface n:domSet.get(dn))
				dTable.get(n).add(dn);
		}
		return dTable;
	}
	
	
	//Implementation of the computation of immediate dominator from the book:
	// Advanced Compiler Design and Implementation 1st Edition by Steven S. Muchnick (Figure 7.15) 
	public Map<NodeInterface,NodeInterface> computeImmediateDominator(){
		Map<NodeInterface,Set<NodeInterface>> tmp = new HashMap<NodeInterface,Set<NodeInterface>>();
		
		for (NodeInterface n : allNode) {
			Set<NodeInterface> tempSet = new HashSet<NodeInterface>(domSet.get(n));
			tempSet.remove(n);
			tmp.put(n,tempSet );
		}
		
		Map<NodeInterface,Set<NodeInterface>> tmpCopy = new HashMap<NodeInterface,Set<NodeInterface>>();
		for (Entry<NodeInterface, Set<NodeInterface>> en : tmp.entrySet()) {
			tmpCopy.put(en.getKey(), new HashSet<NodeInterface>(en.getValue()));
		}

		for (NodeInterface n : allNode) {
			if(n == entry) continue;
			for (NodeInterface s : tmp.get(n)) {
				for (NodeInterface t : tmp.get(n)) {
					if(t == s) continue;
					if(tmp.get(s).contains(t)){
						tmpCopy.get(n).remove(t);
					}
				}
			}
		}
		
		for (NodeInterface n : allNode) {
			if(n == entry) continue;
			immediateDomMap.put(n, tmpCopy.get(n).iterator().next());
		}
		
		
		return immediateDomMap;
	}
	
	public Map<NodeInterface,NodeInterface> getImmediatePostDominator(){
		return immediateDomMap;
	}
	
	// L is A or the parent of A
	public NodeInterface getLeastCommonDominator(NodeInterface a, NodeInterface b){
		// first check if L is A
		NodeInterface bAncestor = b;
		while (bAncestor != null){
			if(bAncestor == a)
				return a;
			bAncestor = immediateDomMap.get(bAncestor);
		}
		
		//now check if L is parent of A
		NodeInterface parentOfA = immediateDomMap.get(a);
		bAncestor = b;
		while (bAncestor != null){
			if(bAncestor == parentOfA)
				return parentOfA;
			bAncestor = immediateDomMap.get(b);
		}
		
		// null should never be returned according to the proved claim in 
		// the program dependence graph paper
		return null;
	}
	
	// L has to be a parent of B in the post dominance tree
	public Set<NodeInterface> getPathBetweenTwoNodes(NodeInterface B, NodeInterface L, boolean includeL){
		HashSet<NodeInterface> nodeSet = new HashSet<NodeInterface>();
		
		NodeInterface bAncestor = B;
		while (bAncestor != null && bAncestor != L){
			nodeSet.add(bAncestor);
			bAncestor = immediateDomMap.get(bAncestor);
		}
		// L is not parent of B;
		if(bAncestor == null)
			return null;
		if(bAncestor == L && includeL)
			nodeSet.add(bAncestor);
		
		return nodeSet;
	}
}

