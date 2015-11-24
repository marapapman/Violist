/**
 * 
 */
package edu.usc.sql.graphs.cfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.IfInstruction;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ReturnInstruction;
import org.apache.bcel.generic.Select;

import soot.SootMethod;
import edu.usc.sql.graphs.Edge;
import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;

/**
 * @author wan
 *
 */
public class BcelCFG extends AbstractCFG {
	public SootMethod getMethod(){
		return null;
	}
	private static List<String> nodeNameList = new ArrayList<String>();
	//private Map<NodeInterface, Integer> node2Int = new HashMap<NodeInterface, Integer>();
	
	// Static Dotty file strings.
	protected static final String[] dottyFileHeader = new String[] {
		"digraph control_flow_graph {",
		"",
		""
	};
	protected static final String[] dottyFileFooter = new String[] {
		"}"
	};
	protected static final String dottyEntryNode = "entry";
	protected static final String dottyExitNode = "exit";
	
	
	// Dotty file edge templates.
	protected static final String dottyLineFormat = "	%1$s -> %2$s;%n";
	protected static final String dottyLineLabelFormat = "	%1$s -> %2$s [label = \"%3$s\"];%n";
	protected static final String newDottyLineFormat = "	%1$s -> %2$s;	%3$s%n";
	protected static final String newDottyLineLabelFormat = "	%1$s -> %2$s [label = \"%3$s\"];	%4$s%n";

	// Map associating line number with instruction.
	SortedMap<Integer, InstructionHandle> statements = new TreeMap<Integer, InstructionHandle>();
	
	
/*
	public String toDot() {
		StringBuilder dotGraph = new StringBuilder();
		dotGraph.append("digraph ControlFlowGraph {\n  label=\"" + name + "\";\n");
		dotGraph.append("  labelloc=t;\n");
		int i = 0;
		for (NodeInterface node : nodeSet) {
			dotGraph.append("  n" + i + " [label=\"" + node.getNodeContent().replace("\"", "\\\"") + "\"];\n");
			node2Int.put(node, i);
			i++;
		}
		for (NodeInterface node : nodeSet) {
			for (EdgeInterface edge : node.getOutEdges()) {
				int source = node2Int.get(edge.getSource());
				int dest = node2Int.get(edge.getDestination());
				dotGraph.append("  n" + source + " -> n" + dest);
				if(!edge.getLabel().equals("")) {
					dotGraph.append(" [label=\"" + edge.getLabel() + "\"]");
				}
				dotGraph.append(";\n");
			}
		}
		dotGraph.append("}\n");
		return dotGraph.toString();
	}*/

	public String toDotDot() {
		StringBuilder dotGraph = new StringBuilder();
		dotGraph.append("digraph ControlFlowGraph {\n  label=\"" + name + "\";\n");
		dotGraph.append("  labelloc=t;\n");
		int i = 0;
		for (NodeInterface node : nodeSet) {
			if(!node.getNodeContent().equals("entry")&&!node.getNodeContent().equals("exit"))
			i = Integer.parseInt(node.getNodeContent());
			dotGraph.append(node.getNodeContent() + " [label=\"" + statements.get(i) + "\"];\n");
			
		}
		for (NodeInterface node : nodeSet) {
			for (EdgeInterface edge : node.getOutEdges()) {
				Node source = (Node) edge.getSource();
				Node dest = (Node) edge.getDestination();
				dotGraph.append(source.getNodeContent() + " -> " + dest.getNodeContent());
				if(!edge.getLabel().equals("")) {
					dotGraph.append(" [label=\"" + edge.getLabel() + "\"]");
				}
				dotGraph.append(";\n");
			}
		}
		dotGraph.append("}\n");
		return dotGraph.toString();
	}
	/**
	 * Loads an instruction list and creates a new CFG.
	 * 
	 * @param instructions Instruction list from the method to create the CFG from.
	 */
	public BcelCFG( InstructionList instructions ) {
		InstructionHandle[] handles = instructions.getInstructionHandles();
		for (InstructionHandle h : handles) {
			statements.put(h.getPosition(), h);
		}
		 
		Set<Integer> lines = statements.keySet();
		List<NodeInterface> nodeList = new ArrayList<NodeInterface>();
		NodeInterface entry = new Node();
		entry.setNodeContent("entry");
		nodeNameList.add("entry");
		nodeList.add(entry);
		
		boolean begin = true;
		for (Integer l : lines) {
			InstructionHandle h = statements.get(l);
			Instruction in = h.getInstruction();
			int current = h.getPosition();
			int next = -1;
			if (h.getNext() != null)
				next = h.getNext().getPosition();
			
			NodeInterface src = null;
			if(!nodeNameList.contains(current+"")) {
				nodeNameList.add(current + "");
				src = new Node();
				src.setNodeContent(current + "");
				nodeList.add(src);
			} else {
				int index = nodeNameList.indexOf(current + "");
				src = nodeList.get(index);
			}
			
			if (begin) {
				EdgeInterface e = new Edge(entry, src, "");
				Set<EdgeInterface> outEntry = entry.getOutEdges();
				outEntry.add(e);
				entry.setOutEdges(outEntry);
				Set<EdgeInterface> inSrc = src.getInEdges();
				inSrc.add(e);
				src.setInEdges(inSrc);
				begin = false;
			}
			if (in instanceof BranchInstruction) {
				String label = translateBranch(in.getName());
				String counterLabel = counterBranch(in.getName());
				//Print basic info
				int target = ((BranchInstruction) in).getTarget().getPosition();
				NodeInterface dest = null;
				
				if(!nodeNameList.contains(target + "")) {
					nodeNameList.add(target + "");
					dest = new Node();
					dest.setNodeContent(target + "");
					nodeList.add(dest);
				} else {
					int index = nodeNameList.indexOf(target + "");
					dest = nodeList.get(index);
				}
				EdgeInterface e = new Edge(src, dest, label);
				Set<EdgeInterface> inEdges = dest.getInEdges();
				inEdges.add(e);
				dest.setInEdges(inEdges);
				Set<EdgeInterface> outEdges = src.getOutEdges();
				outEdges.add(e);
				src.setOutEdges(outEdges);
				
				//Print the counterLabel(Extra Info)
				if (in instanceof IfInstruction) {
					if(!nodeNameList.contains(next + "")) {
						nodeNameList.add(next + "");
						dest = new Node();
						dest.setNodeContent(next + "");
						nodeList.add(dest);
					} else {
						int index = nodeNameList.indexOf(next + "");
						dest = nodeList.get(index);
					}
					EdgeInterface eIf = new Edge(src, dest, counterLabel);
					Set<EdgeInterface> inEdgesIf = dest.getInEdges();
					inEdges.add(eIf);
					dest.setInEdges(inEdgesIf);
					Set<EdgeInterface> outEdgesIf = src.getOutEdges();
					outEdges.add(eIf);
					src.setOutEdges(outEdgesIf); 
				} //TableSwitch & LookupSwitch's cases(Extra Info)
				else if (in instanceof Select) {
					InstructionHandle[] targets = ((Select) in).getTargets();
					int[] cases = ((Select) in).getMatchs();
					for (int i = 0; i < targets.length; i++) {
						if(!nodeNameList.contains(targets[i].getPosition() + "")) {
							nodeNameList.add(targets[i].getPosition() + "");
							dest = new Node();
							dest.setNodeContent(targets[i].getPosition() + "");
							nodeList.add(dest);
						} else {
							int index = nodeNameList.indexOf(targets[i].getPosition() + "");
							dest = nodeList.get(index);
						}
						Edge eSwitch = new Edge(src, dest, cases[i] + "");
						Set<EdgeInterface> inEdgesSwitch = dest.getInEdges();
						inEdges.add(eSwitch);
						dest.setInEdges(inEdgesSwitch);
						Set<EdgeInterface> outEdgesSwitch = src.getOutEdges();
						outEdges.add(eSwitch);
						src.setOutEdges(outEdgesSwitch); 
					}
				}
			}
			else if (in instanceof ReturnInstruction) {
				NodeInterface exit = null;
				if(!nodeNameList.contains("exit")) {
					nodeNameList.add("exit");
					exit = new Node();
					exit.setNodeContent("exit");
					nodeList.add(exit);
				} else {
					int index = nodeNameList.indexOf("exit");
					exit = nodeList.get(index);
				}
				Edge eExit = new Edge(src, exit, "");
				Set<EdgeInterface> inEdgesExit = exit.getInEdges();
				inEdgesExit.add(eExit);
				exit.setInEdges(inEdgesExit);
				Set<EdgeInterface> outEdgesExit = src.getOutEdges();
				outEdgesExit.add(eExit);
				src.setOutEdges(outEdgesExit); 
			} else {
				NodeInterface nextNode = null;
				if(!nodeNameList.contains(next + "")) {
					nodeNameList.add(next + "");
					nextNode = new Node();
					nextNode.setNodeContent(next + "");
					nodeList.add(nextNode);
				} else {
					int index = nodeNameList.indexOf(next + "");
					nextNode = nodeList.get(index);
				}
				EdgeInterface eNormal = new Edge(src, nextNode, "");
				Set<EdgeInterface> inEdgesNormal = nextNode.getInEdges();
				inEdgesNormal.add(eNormal);
				nextNode.setInEdges(inEdgesNormal);
				Set<EdgeInterface> outEdgesNormal = src.getOutEdges();
				outEdgesNormal.add(eNormal);
				src.setOutEdges(outEdgesNormal); 
			}
				
		}
		this.name = "main";
		nodeSet = new HashSet<NodeInterface>();
		nodeSet.addAll(nodeList);
	}
	
	//Translate the compare instruction to label
	public static String translateBranch(String branch) {
		String tmp = branch.toLowerCase();
		String label = "";
		switch (tmp) {
			case "if_acmpeq": 
				label = "=="; 
				break;
			case "if_acmpne": 
				label = "!="; 
				break;
			case "if_icmpeq": 
				label = "=="; 
				break;
			case "if_icmpge": 
				label = ">="; 
				break;
			case "if_icmpgt": 
				label = ">"; 
				break;
			case "if_icmple": 
				label = "<="; 
				break;
			case "if_icmplt": 
				label = "<"; 
				break;
			case "if_icmpne": 
				label = "!="; 
				break;
			case "ifeq": 
				label = "==0"; 
				break;
			case "ifge": 
				label = ">=0"; 
				break;
			case "ifgt": 
				label = ">0"; 
				break;
			case "ifle": 
				label = "<0"; 
				break;
			case "iflt":
				label = "<0";
				break;
			case "ifne": 
				label = "!=0"; 
				break;
			case "ifnonnull": 
				label = "!=NULL"; 
				break;
			case "ifnull": 
				label = "==NULL"; 
				break;
			case "tableswitch": 
			case "lookupswitch": 
				label = "default";
				break;
			default: 
				label = "";
		}
				
		return label;
	}
	
	//Get the counter label of each comparison instruction  
	public static String counterBranch(String branch) {
		String tmp = branch.toLowerCase();
		String label = "";
		switch (tmp) {
			case "if_acmpeq": 
				label = "!="; 
				break;
			case "if_acmpne": 
				label = "=="; 
				break;
			case "if_icmpeq": 
				label = "!="; 
				break;
			case "if_icmpge": 
				label = "<"; 
				break;
			case "if_icmpgt": 
				label = "<="; 
				break;
			case "if_icmple": 
				label = ">"; 
				break;
			case "if_icmplt": 
				label = ">="; 
				break;
			case "if_icmpne": 
				label = "=="; 
				break;
			case "ifeq": 
				label = "!=0"; 
				break;
			case "ifge": 
				label = "<0"; 
				break;
			case "ifgt": 
				label = "<=0"; 
				break;
			case "ifle": 
				label = ">0"; 
				break;
			case "iflt":
				label = ">=0";
				break;
			case "ifne": 
				label = "==0"; 
				break;
			case "ifnonnull": 
				label = "==NULL"; 
				break;
			case "ifnull": 
				label = "!=NULL";
				break;
			default: 
				label = "";
		}
				
		return label;
	}

	@Override
	public String getSignature() {
		// TODO Auto-generated method stub
		return null;
	}

}
