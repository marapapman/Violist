package edu.usc.sql.graphs.cdg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.NodeInterface;

public class PostDominator {
	private Set<NodeInterface> allNode;
	private Set<EdgeInterface> allEdge;
	private NodeInterface exit;
	
	private Map<NodeInterface,Set<NodeInterface>> postdomSet = new HashMap<>();
	private Map<NodeInterface,NodeInterface> immediatePostdomMap = new HashMap<>();
	private Map<NodeInterface,List<NodeInterface>> dTable = new HashMap<>();
	
	public PostDominator(Set<NodeInterface> allNode,Set<EdgeInterface> allEdge,NodeInterface exit)
	{
		this.allNode = allNode;
		this.allEdge = allEdge;
		this.exit = exit;
	//	this.allNode=allNode;
		for(NodeInterface n:allNode)
		{	
			Set<NodeInterface> dSet = new HashSet<>();
			postdomSet.put(n, dSet);
			
			List<NodeInterface> temp = new ArrayList<>();
			dTable.put(n,temp);
		}
		
		computePostDominator();
		computeImmediatePostDominator();
		
	}
	private void initialize()
	{
		for(NodeInterface n:allNode)
		{
			if(n.getOutEdges().isEmpty())
				postdomSet.get(n).add(n);
			else
			{
				for(NodeInterface nn: allNode)
					postdomSet.get(n).add(nn);
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
	public boolean isPostDominate(NodeInterface source,NodeInterface dest)
	{
		for(NodeInterface n:allNode)
		{

			if(n.equals(dest))
				if(postdomSet.get(n).contains(source))
					return true;
		}
		return false;
	}
	public void computePostDominator()
	{
		initialize();

		boolean change = true;
		
		while(change)
		{
			change = false;
			for(NodeInterface n: allNode)
			{
				//exclude the exit node
				if(!n.getOutEdges().isEmpty())
				{
					Set<NodeInterface> temp = new HashSet<>();
					
					//initialize
					NodeInterface first = n.getOutEdges().iterator().next().getDestination();
					for(NodeInterface nn:postdomSet.get(first))
						temp.add(nn);
					//intersect			
					for(EdgeInterface e:n.getOutEdges())
						temp.retainAll(postdomSet.get(e.getDestination()));
					//union itself
					if(!temp.contains(n))
						temp.add(n);
										
					
					if(!compareTwoSet(postdomSet.get(n), temp))
					{
						change=true;
						postdomSet.get(n).clear();
						for(NodeInterface nn:temp)
						{
							postdomSet.get(n).add(nn);
						}
		
					}
				}
			}
		}
		

	}
	
	
	public Map<NodeInterface,Set<NodeInterface>> getPostDominatorSet()
	{
		return postdomSet;
	}
	
	public Map<NodeInterface,List<NodeInterface>> getPostDominatorTable()
	{
		for(NodeInterface dn: allNode)
		{
			for(NodeInterface n:postdomSet.get(dn))
				dTable.get(n).add(dn);
		}
		return dTable;
	}
	
	
	//Implementation of the computation of immediate post dominator from the book:
	// Advanced Compiler Design and Implementation 1st Edition by Steven S. Muchnick (Figure 7.15) 
	public Map<NodeInterface,NodeInterface> computeImmediatePostDominator(){
		Map<NodeInterface,Set<NodeInterface>> tmp = new HashMap<NodeInterface,Set<NodeInterface>>();
		
		for (NodeInterface n : allNode) {
			Set<NodeInterface> tempSet = new HashSet<NodeInterface>(postdomSet.get(n));
			tempSet.remove(n);
			tmp.put(n,tempSet );
		}
		
		Map<NodeInterface,Set<NodeInterface>> tmpCopy = new HashMap<NodeInterface,Set<NodeInterface>>();
		for (Entry<NodeInterface, Set<NodeInterface>> en : tmp.entrySet()) {
			tmpCopy.put(en.getKey(), new HashSet<NodeInterface>(en.getValue()));
		}

		for (NodeInterface n : allNode) {
			if(n == exit) continue;
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
			if(n == exit) continue;
			immediatePostdomMap.put(n, tmpCopy.get(n).iterator().next());
		}
		
		
		return immediatePostdomMap;
	}
	
	public Map<NodeInterface,NodeInterface> getImmediatePostDominator(){
		return immediatePostdomMap;
	}
	
	// L is A or the parent of A
	public NodeInterface getLeastCommonPostDominator(NodeInterface a, NodeInterface b){
		// first check if L is A
		NodeInterface bAncestor = b;
		while (bAncestor != null){
			if(bAncestor == a)
				return a;
			bAncestor = immediatePostdomMap.get(bAncestor);
		}
		
		//now check if L is parent of A
		NodeInterface parentOfA = immediatePostdomMap.get(a);
		bAncestor = b;
		while (bAncestor != null){
			if(bAncestor == parentOfA)
				return parentOfA;
			bAncestor = immediatePostdomMap.get(bAncestor);
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
			bAncestor = immediatePostdomMap.get(bAncestor);
		}
		// L is not parent of B;
		if(bAncestor == null)
			return null;
		if(bAncestor == L && includeL)
			nodeSet.add(bAncestor);
		
		return nodeSet;
	}


}

