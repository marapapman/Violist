package edu.usc.sql.graphs;

import java.util.HashMap;
import java.util.Map;


public class Edge implements EdgeInterface {

	private NodeInterface source;	//start node of the edge
	private NodeInterface destination;	//end node of the edge
	
	protected Map<String,Object> attributes = new HashMap<String, Object>();
	
	public Edge(NodeInterface source, NodeInterface destination, String label) {
		this.source = source;
		this.destination = destination;
		this.attributes.put("label", label);
	}
	
	public Edge(NodeInterface source, NodeInterface destination) {
		this.source = source;
		this.destination = destination;
		this.attributes.put("label", "");
	}
	
	@Deprecated
	public String getLabel() {
		return attributes.get("label").toString();
	}

	@Deprecated
	public void setLabel(String label) {
		this.attributes.put("label", label);
	}
	
	public NodeInterface getSource() {
		return source;
	}
	public void setSource(NodeInterface n) {
		source = n;
	}
	public NodeInterface getDestination() {
		return destination;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != this.getClass())
			return false;
		
		Edge b = (Edge) obj;
		return source.equals(b.getSource()) && destination.equals(b.getDestination()) && attributes.equals(b.attributes);
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + attributes.hashCode();
		result = 37 * result + source.hashCode();
		result = 37 * result + destination.hashCode();
		return result;
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
	public String toString() {
		return source.getName() + " -> " + destination.getName() + getAttributeString();
	}
	
	@Override
	public String toDot() {
		//return source.getName() + " -> " + destination.getName() + " ["+getAttributeString()+"];";
		return source.getOffset() + " -> " + destination.getOffset() + " ["+getAttributeString()+"];";
	}

	@Override
	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

}
