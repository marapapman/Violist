/**
 * 
 */
package edu.usc.sql.graphs.cg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Graph;
import edu.usc.sql.graphs.NodeInterface;

/**
 * @author wan
 *
 */
public abstract class AbstractCG extends Graph implements CGInterface {
	protected String sourceFile;
    
	/**
	 * Return the all the nodes in reverse topological order
	 */
	public List<NodeInterface> getNodesInReverseTopologicalOrder() {
		List<NodeInterface> nodeList = new ArrayList<NodeInterface>();
		Set<NodeInterface> allNodes = new HashSet<NodeInterface>();
		allNodes.addAll(getAllNodes());
		Set<NodeInterface> noIncomingSet = new HashSet<NodeInterface>();
		//Topological Sorting
		for(NodeInterface n : allNodes){
			if(n.getInEdges().size() == 0){
				noIncomingSet.add(n);
		    }
		}
		
		while(!noIncomingSet.isEmpty()) {
			NodeInterface n = noIncomingSet.iterator().next();
			noIncomingSet.remove(n);
			
			nodeList.add(n);
			
			//for each node m with an edge e from n to m do
			for(Iterator<EdgeInterface> it = n.getOutEdges().iterator();it.hasNext();){
		        //remove edge e from the graph
		        EdgeInterface e = it.next();
		        NodeInterface m = e.getDestination();
		        it.remove();//Remove edge from n
		        m.getInEdges().remove(e);//Remove edge from m
	
		        //if m has no other incoming edges then insert m into S
		        if(m.getInEdges().isEmpty()){
		          noIncomingSet.add(m);
		        }
			}
		}
		
		//Check to see if all edges are removed
	    boolean cycle = false;
	    for(NodeInterface n : allNodes){
	    	if(!n.getInEdges().isEmpty()){
	    		cycle = true;
	    		break;
	    	}
	    }
	    if(cycle) {
	    	System.out.println("Cycle present, topological sort not possible");
	    } else {
//	    	System.out.println("Topological Sort: ");
	    	//Reverse the order
	      	Collections.reverse(nodeList);
//	    	for(NodeInterface n : nodeList) {
//	      		System.out.print(n.getNodeContent() + "  ");
//	      	}
	    }
		
		return nodeList;
	}
	
	
	
	
	
}
