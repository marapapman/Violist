package edu.usc.sql.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;

public class Node<T> implements NodeInterface {

	@Deprecated
	private String nodeContent; //Content for each node
	
	private Set<EdgeInterface> inEdges; // Incoming Edges
	protected Set<EdgeInterface> outEdges; // Outgoing Edges
	private T actualNode;
	protected String name;
	private Integer offset;
	protected static int ID=0;
	
	protected Map<String,Object> attributes;

	public Node(String name, Set<EdgeInterface> inEdges, java.util.Set<EdgeInterface> outEdges, Map<String,Object> attributes) {
		this.inEdges = inEdges;
		this.outEdges = outEdges;
		this.name = name;
		this.attributes = attributes;
	}
	
	public Node(String name, Set<EdgeInterface> inEdges, java.util.Set<EdgeInterface> outEdges) {
		this.inEdges = inEdges;
		this.outEdges = outEdges;
		this.name = name;
		this.attributes = new HashMap<String, Object>();
	}
	
	public Node(String name) {
		this.name = name;
		this.inEdges = new HashSet<EdgeInterface>();
		this.outEdges = new HashSet<EdgeInterface>();
		this.attributes = new HashMap<String, Object>();
	}
	
	public Node() {
		this.name=ID+"";
		ID++;
		this.inEdges = new HashSet<EdgeInterface>();
		this.outEdges = new HashSet<EdgeInterface>();
		this.attributes = new HashMap<String, Object>();
	}
	
	//***************************
	// Some fields for WAM
	//***************************
	private boolean isEntryPoint = false;
	private boolean isTail = false;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}
	
	public Object getAttribute(String name) {
		return attributes.get(name);
	}
	
	@Deprecated
	public String getLabel() {
		return attributes.get("label").toString();
	}
	
	@Deprecated
	public String getNodeContent() {
		return nodeContent;
	}

	public Set<EdgeInterface> getInEdges() {
		return inEdges;
	}

	public Set<EdgeInterface> getOutEdges() {
		return outEdges;
	}

	@Deprecated
	public void setNodeContent(String nodeContent) {
		this.nodeContent = nodeContent;
	}

	public void addInEdge(EdgeInterface inEdge) {
		this.inEdges.add(inEdge);
	}
	
	public void addOutEdge(EdgeInterface outEdge) {
		this.outEdges.add(outEdge);
	}
	
	public void setInEdges(Set<EdgeInterface> inEdges) {
		this.inEdges = inEdges;
	}

	public void setOutEdges(Set<EdgeInterface> outEdges) {
		this.outEdges = outEdges;
	}

	public T getActualNode() {
		return actualNode;
	}

	public void setActualNode(T actualNode) {
		this.actualNode = actualNode;
	}
	private String getActualNodeSignature(){
		if(this.actualNode==null)
			return "null";
		else{
			return this.actualNode.toString();
		}
	}
	//***********************************
	// Methods for WAM
	//***********************************
	public boolean isEntryPoint() {
		return isEntryPoint;
	}

	public void setEntryPoint(boolean isEntryPoint) {
		this.isEntryPoint = isEntryPoint;
	}
	
	public Set<NodeInterface> predecessors() {
		Set<NodeInterface> predNodes = new HashSet<NodeInterface>();
		for(EdgeInterface e : inEdges) {
			NodeInterface node = (NodeInterface) e.getSource();
			predNodes.add(node);
		}
		return predNodes;
	}
	
	public boolean isTail() {
		return isTail;
	}

	public void setTail(boolean isTail) {
		this.isTail = isTail;
	}

	public Set<NodeInterface> successors() {
		Set<NodeInterface> succNodes = new HashSet<NodeInterface>();
		for(EdgeInterface e : outEdges) {
			NodeInterface node = (NodeInterface) e.getDestination();
			succNodes.add(node);
		}
		return succNodes;
	}
	
	public boolean match(List<String> pf_sig) {
		Stmt s = (Stmt) getActualNode();
		if (s.containsInvokeExpr()) {
			InvokeExpr ie = s.getInvokeExpr();
			for (String pf:pf_sig) {
				if (pf.equalsIgnoreCase(ie.getMethod().getSignature())) {
					return true;
				} 
			}
		}
		return false;
	}

	protected String getAttributeString() {
		StringBuilder attributeString = new StringBuilder("");
		for(String name:attributes.keySet()) {
			attributeString.append(", " + name+"=\""+attributes.get(name).toString()+"\"");
		}
		String result = attributeString.toString(); 
		if (!result.isEmpty()) {
			result = result.substring(2);
		}
		return result;
	}
	
	@Override
	public String toDot() {	
		return offset + " ["+getAttributeString()+"];";
	}

	@Override
	public String toXML() {
		return null;
	}
	
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != this.getClass())
			return false;
		
		Node b = (Node) obj;
		
		return this.getActualNodeSignature().equals(b.getActualNodeSignature()) && attributes.equals(b.attributes) && this.offset==b.offset;
	}
	
	@Override
	public int hashCode() {

		int result = 17;
		result = 37 * result + name.hashCode();
		for(String name:attributes.keySet()) {
			result = 37 * result + attributes.get(name).hashCode();
		}
		return result;
	}

	@Override
	public void setOffset(int offset) {
		// TODO Auto-generated method stub
		this.offset=offset;
		
	}

	@Override
	public Integer getOffset() {
		// TODO Auto-generated method stub
		return this.offset;
	}
	
}
