package edu.usc.sql.graphs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import att.grappa.Parser;

public class Graph implements GraphInterface {

	protected Set<NodeInterface> nodeSet; // all the nodes in this CFG
	//protected Set<EdgeInterface> edgeSet;
	
	protected String name;
	
	
	static public Graph fromDotty(File dottyFile) throws FileNotFoundException, Exception {
		
		Parser p = new Parser(new FileInputStream(dottyFile), System.err);
		p.parse();
		att.grappa.Graph dottyGraph = p.getGraph();
		
		att.grappa.Node[] nodes = dottyGraph.nodeElementsAsArray();
		
		Graph g = new Graph();
		Map<att.grappa.Node, Node> nodemap = new HashMap<att.grappa.Node, Node>();
		
		for (att.grappa.Node n: nodes) {
			Node nn = new Node(n.getName());
			Iterator<att.grappa.Attribute> itAttr = n.getLocalAttributePairs();
			while (itAttr.hasNext()) {
				att.grappa.Attribute attr = (att.grappa.Attribute) itAttr.next();
				nn.setAttribute(attr.getName(), attr.getStringValue());
			}
			nodemap.put(n, nn);		
			g.addNode(nn);
		}
		
		for (att.grappa.Node n: nodes) {
			Node N = nodemap.get(n);
			Iterator<att.grappa.Edge> itEdge = n.inEdgeElements();
			while (itEdge.hasNext()) {
				att.grappa.Edge e = (att.grappa.Edge) itEdge.next();
				Edge ee = new Edge(nodemap.get(e.getTail()), nodemap.get(e.getHead())); //oddly this is the right way to do this
				Iterator<att.grappa.Attribute> itAttr = e.getLocalAttributePairs();
				while (itAttr.hasNext()) {
					att.grappa.Attribute attr = (att.grappa.Attribute) itAttr.next();
					ee.setAttribute(attr.getName(), attr.getStringValue());
				}
				N.addInEdge(ee);
			}
			itEdge = n.outEdgeElements();
			while (itEdge.hasNext()) {
				att.grappa.Edge e = (att.grappa.Edge) itEdge.next();
				Edge ee = new Edge(nodemap.get(e.getTail()), nodemap.get(e.getHead())); //oddly, this is the right way to do this
				Iterator<att.grappa.Attribute> itAttr = e.getLocalAttributePairs();
				while (itAttr.hasNext()) {
					att.grappa.Attribute attr = (att.grappa.Attribute) itAttr.next();
					ee.setAttribute(attr.getName(), attr.getStringValue());
				}
				N.addOutEdge(ee);
			}
		}
		
		return g;
	}
	
	public Graph() {
		this.nodeSet =  new HashSet<NodeInterface>();
	}
	
	public void addNode(NodeInterface n) {
		nodeSet.add(n);
	}
	
	
	
	public Set<NodeInterface> getAllNodes() {
		return nodeSet;
	}

	public Set<EdgeInterface> getAllEdges() {
		Set<EdgeInterface> allEdges = new HashSet<EdgeInterface>();
		for (NodeInterface n: nodeSet) {
			allEdges.addAll(n.getOutEdges());
		}
		return allEdges;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

	@Override
	public String toDot() {
		StringBuilder dotGraph = new StringBuilder();
		dotGraph.append("digraph directed_graph {\n\tlabel=\"" + name + "\";\n");
		dotGraph.append("\tlabelloc=t;\n");
		for (NodeInterface node : nodeSet) {
			dotGraph.append("\t"+node.toDot() + "\n");
		}
		for (NodeInterface node : nodeSet) {
			for (EdgeInterface edge : node.getOutEdges()) {
				dotGraph.append("\t"+edge.toDot()+"\n");
			}
		}
		dotGraph.append("}\n");
		return dotGraph.toString();
	}

	public String toXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != this.getClass())
			return false;
		
		Graph b = (Graph) obj;
		return nodeSet.equals(b.getAllNodes()) && getAllEdges().equals(b.getAllEdges()) ;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + nodeSet.hashCode();
		return result;
	}

}
