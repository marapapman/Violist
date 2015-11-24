/**
 * Define the necessary methods for Control Flow Graph
 */
package edu.usc.sql.graphs.cfg;

import soot.SootMethod;
import edu.usc.sql.graphs.GraphInterface;
import edu.usc.sql.graphs.NodeInterface;

/**
 * @author Mian Wan
 *
 */
public interface CFGInterface extends GraphInterface {
	
	
	/**
	 * Return entry node
	 * @return
	 */
	public NodeInterface getEntryNode();
	
	/**
	 * Return exit node
	 * @return
	 */
	public NodeInterface getExitNode();
	public String getSignature();
	
}
