/**
 * This interface defines all the necessary methods for Node
 */
package edu.usc.sql.graphs;

import java.util.Set;

/**
 * @author Mian Wan
 *
 */
public interface NodeInterface {
	
	/**
	 * Return the content of this node
	 * @return
	 */
	public String getNodeContent();
	
	/**
	 * Return all the incoming edges
	 * @return
	 */
	public Set<EdgeInterface> getInEdges();
	
	/**
	 * Return all the outgoing edges
	 * @return
	 */
	public Set<EdgeInterface> getOutEdges();
	
	/**
	 * Set the Node Content
	 * @param nodeContent
	 */
	public void setNodeContent(String nodeContent);
	
	/**
	 * Set all the incoming edges
	 * @param inEdges
	 */
	public void setInEdges(Set<EdgeInterface> inEdges);
	
	/**
	 * Set all the outgoing edges
	 * @param outEdges
	 */
	public void setOutEdges(Set<EdgeInterface> outEdges);
	
	public String getName() ;

	public void setName(String label) ;
	public void setOffset(int offset);
	public void setAttribute(String name, Object value);
	public Object getAttribute(String name);
	public Integer getOffset();
	public String toString();
	public String toDot();
	public String toXML();
}
