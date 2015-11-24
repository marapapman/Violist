package edu.usc.sql.graphs;

import java.util.Set;

public interface GraphInterface {
	/**
	 * Return all the nodes in this call graph
	 * @return
	 */
	public Set<NodeInterface> getAllNodes();

	/**
	 * Return all the edges in this call graph
	 * @return
	 */
	public Set<EdgeInterface> getAllEdges();

	/**
	 * Return the dot representation of this call graph
	 * @return
	 */
	public String toDot();
	public String toString();
	public String toXML();
	
}
