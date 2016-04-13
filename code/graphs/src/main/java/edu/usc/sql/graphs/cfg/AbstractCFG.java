/**
 * 
 */
package edu.usc.sql.graphs.cfg;

import edu.usc.sql.graphs.Graph;
import edu.usc.sql.graphs.NodeInterface;

/**
 * @author Mian Wan
 *
 */
public abstract class AbstractCFG extends Graph  implements CFGInterface {

	protected String sourceFile; // the source file of this CFG

	
	public String getSourceFile() {
		return sourceFile;
	}
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	public String getGraphName() {
		return name;
	}
	public void setGraphName(String graphName) {
		this.name = graphName;
	}
	
	@Override
	public NodeInterface getEntryNode() {
		for (NodeInterface n: nodeSet) {
			if(n.getNodeContent().equals("entry")) {
				return n;
			}
		}
		return null;
	}

	@Override
	public NodeInterface getExitNode() {
		for (NodeInterface n: nodeSet) {
			if(n.getNodeContent().equals("exit")) {
				return n;
			}
		}
		return null;
	}
	
}
