package edu.usc.sql.graphs;

public interface EdgeInterface {

	public String getLabel();
	public void setLabel(String label);

	public NodeInterface getSource();
	public void setSource(NodeInterface n);
	public NodeInterface getDestination();
	
	public void setAttribute(String name, Object value);
	public Object getAttribute(String name);
	
	public String toString();
	public String toDot();
	public String toXML();
}
