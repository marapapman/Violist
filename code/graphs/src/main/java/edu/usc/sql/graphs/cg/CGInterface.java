/**
 * Define the necessary methods for Call Graph
 */
package edu.usc.sql.graphs.cg;

import java.util.List;

import edu.usc.sql.graphs.GraphInterface;
import edu.usc.sql.graphs.NodeInterface;

/**
 * @author wan
 *
 */
public interface CGInterface extends GraphInterface {
	
	/**
	 * Return the dot representation of this control flow graph
	 * @return
	 */
	
	public List<NodeInterface> getNodesInReverseTopologicalOrder();
}
