package edu.usc.sql.graphs.cdg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import soot.jimple.parser.node.TWith;
import edu.usc.sql.graphs.Edge;
import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Graph;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;

//This class makes use of functions from PostDominator.java and builds the control dependancy table in a Map. The algorithm is built
//As per the procedure given in "Program Dependence Graph and its Use in Optimization"
public class CDG {
	
	//The Map component which has a record of the node and its CDG
	protected static Graph CDGraph = new Graph();
	protected static HashSet<EdgeInterface> SPairs = new HashSet<EdgeInterface>();

	@SuppressWarnings("null")
	public CDG(Set<NodeInterface> allNode,Set<EdgeInterface> allEdge,NodeInterface exit)
	{
		//need to add the START node with edges to Entry and Exit
		//Added in the CDG Test
		
		
		
//		//Add the Entry and Exit Node
//		Node entry = new Node("Entry");
//		Node exit1 = new Node("Exit");
//		allNode.add(entry);
//		allNode.add(exit1);
//		
//		for(NodeInterface nodeIter:allNode)
//		{
//			if(nodeIter.getInEdges().isEmpty())
//			{
//				Edge entryEdge = new Edge(entry,nodeIter);
//				allEdge.add(entryEdge);
//			}
//			if(nodeIter.getOutEdges().isEmpty())
//			{
//				Edge exitEdge = new Edge(nodeIter,exit1);
//				allEdge.add(exitEdge);
//			}
//		}
//		
//		//Add a Start node with T to Entry and F to Exit
//		Node start= new Node("Start");
//		allNode.add(start);
//		Edge startEntry = new Edge(start,entry,"True");
//		allEdge.add(startEntry);
//		Edge startExit = new Edge(start,exit,"False");
//		allEdge.add(startExit);

		
		//Creating the Post Dominator for the nodes and edges
		PostDominator PD = new PostDominator(allNode, allEdge, exit);
		

		//Traversing through the edges of the CFG to figure out S
		//S= all node pairs which are not Post Dominant on each other
		 //TODO: Change Map to Set - SPairs
		
		for (EdgeInterface edgeInterface : allEdge) {
			if(!PD.isPostDominate(edgeInterface.getDestination(),edgeInterface.getSource()))
					{
						//If there is no Post Dominance i.e. B does not post Dominate A. Store it as SPair(Algo)
						
						System.out.println("Added:"+edgeInterface);
				
						SPairs.add(edgeInterface);

					}
		}
		
		
		//Now that we have SPairs, let us calculate the LCM. The LCM is provided in the PostDominator class
		//getLeastCommonPostDominator()
		
		//Calculating the control Dependant of A out of the SPairs as per the Algo.
		for (EdgeInterface SEdgeItr : SPairs)
		{
		    System.out.println("\n S Pair:="+SEdgeItr.getSource() + " : " + SEdgeItr.getDestination());
		    
		    //Retrieving the LCM
		    NodeInterface LCM = PD.getLeastCommonPostDominator(SEdgeItr.getSource(), SEdgeItr.getDestination());
		    
		    System.out.println("LCM:"+LCM);
		    
		    //Path from one node to the other node is obtained by the Post Dominance function: getPathBetweenTwoNodes()
		 
		    //Check if LCM = A or LCM is parent of A in the third parameter
		    Set<NodeInterface> PathBet=PD.getPathBetweenTwoNodes(SEdgeItr.getDestination(), LCM, LCM==SEdgeItr.getSource());
		    
		    	//Traversing through the paths from LCM to B and adding all nodes to Control Dependant on A
		    	
		    	//ArrayList<Node> tempDep=null;
		    	
		    	for(NodeInterface NodeItr: PathBet)
		    	{
		    		// To add A to the CDGraph
		    		
		    		//Get the source node A
		    		Node tempNode = null;
		    		int tempnodeflag=0;
					for(NodeInterface checkANode:CDGraph.getAllNodes())
		    		{
						//If the tempnode A is already present in the CDGraph, assign tempNode to A
		    			if(checkANode.getName().equals(SEdgeItr.getSource().getName()))
		    			{
		    				tempNode = (Node) checkANode;
		    				
		    				tempnodeflag=1;
		    				break;
		    			}
		    			else
		    			{
		    				tempnodeflag=0;
		    			}
		    		}
		    		
					//If TempNode A is not present in the CDGraph, create it
		    		if(tempnodeflag==0)
		    		{
		    			tempNode = new Node(SEdgeItr.getSource().getName());
		    			tempNode.setActualNode(((Node) SEdgeItr.getSource()).getActualNode());
		    		}
		    		
		    		//Adding the Destination Node to the CDGraph
		    		Node returnNode = null;
		    		int returnnodeflag=0;
					for(NodeInterface checkBNode:CDGraph.getAllNodes())
		    		{
						//If the returnnode path node is already present in the CDGraph, assign returnNode to path node
		    			if(checkBNode.getName().equals(NodeItr.getName()))
		    			{
		    				returnNode = (Node) checkBNode;
		    				returnnodeflag=1;
		    				break;
		    			}
		    			else
		    			{
		    				returnnodeflag=0;
		    			}
		    		}
		    		//If returnNode is not present in the CDGraph
		    		if(returnnodeflag==0)
		    		{
		    			returnNode = new Node(NodeItr.getName());
		    			returnNode.setActualNode(((Node) NodeItr).getActualNode());
		    		}
		    		
		    		if(tempNode!=null && returnNode!=null)
		    		{
		    			//If the A and the Path is same
			    		if(tempNode.equals(returnNode))
			    		{
			    			break;
			    		}

			    		String tempLabel="";
			    		
			    		
			    		//Assigning the Truth Value to the edge (Truth value should be same as the path truth value)
			    		for(EdgeInterface EdgeItr: allEdge)
			    		{
			    			if(EdgeItr.getSource().getName().equals(tempNode.getName()) && EdgeItr.getDestination().getName().equals(returnNode.getName()))
			    					{
			    						tempLabel=EdgeItr.getLabel();
			    					}
			    		}
			    		
			    		//Adding an edge from the A to the path node
			    		Edge tempEdge = new Edge(tempNode,returnNode,tempLabel);
			    		
			    		//Adding an out edge to tempNode A
			    		tempNode.addOutEdge(tempEdge);
			    		returnNode.addInEdge(tempEdge);
			    		
			    		//Adding Nodes to the CDGraph
			    		CDGraph.addNode(tempNode);
			    		CDGraph.addNode(returnNode);
		    		}
		    		
		    		
		    	}
		    	
		}
		    
	}
		

	public Graph getControlDependantGraph()
	{

		return CDGraph;
		
	}
	
	
	//getControlDependants will return the list of nodes that depends on the node
	public ArrayList<NodeInterface> getControlDependants(Node root)
	{
		ArrayList<NodeInterface> tempList = new ArrayList<NodeInterface>();
		
		Set<EdgeInterface> EdgesOut=CDGraph.getAllEdges();
		
		for (EdgeInterface edgeIter : EdgesOut) 
		{
			//If the source of the edge is the root node inputted the create a list of children
			if(edgeIter.getSource().equals(root))
			{
				tempList.add(edgeIter.getDestination());
			}
		}
		
		return tempList;
			
	}
	

}
