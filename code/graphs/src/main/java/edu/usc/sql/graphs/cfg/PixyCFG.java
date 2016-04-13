package edu.usc.sql.graphs.cfg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soot.SootMethod;
import at.ac.tuwien.infosys.www.pixy.Checker;
import at.ac.tuwien.infosys.www.pixy.conversion.CfgEdge;
import at.ac.tuwien.infosys.www.pixy.conversion.ControlFlowGraph;
import at.ac.tuwien.infosys.www.pixy.conversion.ProgramConverter;
import at.ac.tuwien.infosys.www.pixy.conversion.TacActualParameter;
import at.ac.tuwien.infosys.www.pixy.conversion.TacConverter;
import at.ac.tuwien.infosys.www.pixy.conversion.TacFunction;
import at.ac.tuwien.infosys.www.pixy.conversion.TacOperators;
import at.ac.tuwien.infosys.www.pixy.conversion.Variable;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.AbstractCfgNode;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.AssignArray;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.AssignBinary;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.AssignReference;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.AssignSimple;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.AssignUnary;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.BasicBlock;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Call;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.CallBuiltinFunction;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.CallPreparation;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.CallReturn;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.CallUnknownFunction;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.CfgEntry;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.CfgExit;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Define;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Echo;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Empty;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.EmptyTest;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Eval;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Global;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.If;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Include;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.IncludeEnd;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.IncludeStart;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Isset;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Static;
import at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes.Unset;
import edu.usc.sql.graphs.Edge;
import edu.usc.sql.graphs.EdgeInterface;
import edu.usc.sql.graphs.Node;
import edu.usc.sql.graphs.NodeInterface;

/**
 * @author wan
 *
 */
public class PixyCFG extends AbstractCFG {
	public SootMethod getMethod(){
		return null;
	}
	public static Map<String, CFGInterface> buildCFGs(String rootDir, String component) {
		String php = rootDir + File.separator + component;
		String phpFileName = php.substring(php.lastIndexOf(File.separator) + 1);
		Checker checker = new Checker(php);
		
		 // convert the whole program (with file inclusions)
		 ProgramConverter programConverter = checker.initialize();
		 TacConverter tac = programConverter.getTac();
		
		 Map<String, CFGInterface> cfgMap = new HashMap<String, CFGInterface>();
		 for (TacFunction tf : tac.getUserFunctions().values()) {
			 CFGInterface pixyCfg = new PixyCFG(phpFileName, tf);
			 cfgMap.put(tf.getName(), pixyCfg);
		 }
		 
		 return cfgMap;
	}

	public PixyCFG(String graphName, TacFunction method) {

		ControlFlowGraph pixyCfg = method.getControlFlowGraph();
		List<AbstractCfgNode> pixyList = new ArrayList<>();
		List<Node<AbstractCfgNode>> nodeList = new ArrayList<Node<AbstractCfgNode>>();
		translateCFGNode(pixyCfg.getHead(), pixyList, nodeList);
		this.name = name;
		nodeSet = new HashSet<NodeInterface>();
		nodeSet.addAll(nodeList);
	}


	private Node<AbstractCfgNode> translateCFGNode(AbstractCfgNode cfgNode, List<AbstractCfgNode> pixyList, List<Node<AbstractCfgNode>> nodeList) {
		String name = getCfgNodeName(cfgNode);
		Node<AbstractCfgNode> node = new Node<AbstractCfgNode>();
		node.setNodeContent(name);
		node.setActualNode((AbstractCfgNode) cfgNode);
		pixyList.add((AbstractCfgNode) cfgNode);
		nodeList.add(node);

		for (int i = 0; i < 2; i++) {

			CfgEdge outEdge = cfgNode.getOutEdge(i);

			if (outEdge != null) {

				AbstractCfgNode succNode = outEdge.getDestination();
				Node<AbstractCfgNode> succCFGNode = null;
				if(!pixyList.contains(succNode)) {
					succCFGNode = translateCFGNode(succNode, pixyList, nodeList);
				} else {
					int index = pixyList.indexOf(succNode);
					succCFGNode = nodeList.get(index);
				}

				String label = "";
				if (outEdge.getType() != CfgEdge.NORMAL_EDGE) {
					label = outEdge.getName();
				}
				Edge edge = new Edge(node, succCFGNode, label);
				Set<EdgeInterface> outEdges = node.getOutEdges();
				if(!outEdges.contains(edge)) {
					outEdges.add(edge);
					node.setOutEdges(outEdges);
				}

				Set<EdgeInterface> inEdges = succCFGNode.getInEdges();
				if (!inEdges.contains(edge)) {
					inEdges.add(edge);
					succCFGNode.setInEdges(inEdges);
				}

			}
		}
		return node;
	}

	public static String getCfgNodeName(AbstractCfgNode cfgNodeX) {
		if (cfgNodeX instanceof BasicBlock) {
			BasicBlock cfgNode = (BasicBlock) cfgNodeX;
			StringBuilder label = new StringBuilder("basic block\\n");
			for (AbstractCfgNode containedNode : cfgNode.getContainedNodes()) {
				label.append(getCfgNodeName(containedNode));
				label.append("\\n");
			}
			return label.toString();
		} else if (cfgNodeX instanceof AssignSimple) {
			AssignSimple cfgNode = (AssignSimple) cfgNodeX;
			String leftString = cfgNode.getLeft().toString();
			String rightString = cfgNode.getRight().toString();
			return (leftString + " = " + rightString);
		} else if (cfgNodeX instanceof AssignBinary) {
			AssignBinary cfgNode = (AssignBinary) cfgNodeX;
			String leftString = cfgNode.getLeft().toString();
			String leftOperandString = cfgNode.getLeftOperand().toString();
			String rightOperandString = cfgNode.getRightOperand().toString();
			int op = cfgNode.getOperator();

			return (
					leftString +
					" = " +
					leftOperandString +
					" " + TacOperators.opToName(op) + " " +
					rightOperandString);
		} else if (cfgNodeX instanceof AssignUnary) {
			AssignUnary cfgNode = (AssignUnary) cfgNodeX;
			String leftString = cfgNode.getLeft().toString();
			String rightString = cfgNode.getRight().toString();
			int op = cfgNode.getOperator();

			return (
					leftString +
					" = " +
					" " + TacOperators.opToName(op) + " " +
					rightString);
		} else if (cfgNodeX instanceof AssignReference) {
			AssignReference cfgNode = (AssignReference) cfgNodeX;
			String leftString = cfgNode.getLeft().toString();
			String rightString = cfgNode.getRight().toString();
			return (leftString + " =& " + rightString);
		} else if (cfgNodeX instanceof If) {
			If cfgNode = (If) cfgNodeX;
			String leftOperandString = cfgNode.getLeftOperand().toString();
			String rightOperandString = cfgNode.getRightOperand().toString();
			int op = cfgNode.getOperator();

			return (
					"if " +
							leftOperandString +
							" " + TacOperators.opToName(op) + " " +
							rightOperandString);
		} else if (cfgNodeX instanceof Empty) {
			return ";";
		} else if (cfgNodeX instanceof CfgEntry) {
			return "entry";
		} else if (cfgNodeX instanceof CfgExit) {
			CfgExit cfgNode = (CfgExit) cfgNodeX;
			return "exit " + cfgNode.getEnclosingFunction().getName();
		} else if (cfgNodeX instanceof Call) {
			Call cfgNode = (Call) cfgNodeX;
			String objectString = "";
			Variable object = cfgNode.getObject();
			if (object != null) {
				objectString = object + "->";
			}

			// construct parameter list
			StringBuilder paramListStringBuf = new StringBuilder();
			for (Iterator<TacActualParameter> iter = cfgNode.getParamList().iterator(); iter.hasNext(); ) {
				TacActualParameter param = iter.next();
				if (param.isReference()) {
					paramListStringBuf.append("&");
				}
				paramListStringBuf.append(param.getPlace());
				if (iter.hasNext()) {
					paramListStringBuf.append(", ");
				}
			}

			return (objectString + cfgNode.getFunctionNamePlace().toString() + "(" + paramListStringBuf.toString() + ")");
		} else if (cfgNodeX instanceof CallPreparation) {
			CallPreparation cfgNode = (CallPreparation) cfgNodeX;

			// construct parameter list
			StringBuilder paramListStringBuf = new StringBuilder();
			for (Iterator<TacActualParameter> iter = cfgNode.getParamList().iterator(); iter.hasNext(); ) {
				TacActualParameter param = iter.next();
				if (param.isReference()) {
					paramListStringBuf.append("&");
				}
				paramListStringBuf.append(param.getPlace());
				if (iter.hasNext()) {
					paramListStringBuf.append(", ");
				}
			}

			return (
					"prepare " +
							cfgNode.getFunctionNamePlace().toString() + "(" +
							paramListStringBuf.toString() + ")");
		} else if (cfgNodeX instanceof CallReturn) {
			CallReturn cfgNode = (CallReturn) cfgNodeX;
			return ("call-return (" + cfgNode.getTempVar() + ")");
		} else if (cfgNodeX instanceof CallBuiltinFunction) {
			CallBuiltinFunction cfgNode = (CallBuiltinFunction) cfgNodeX;

			// construct parameter list
			StringBuilder paramListStringBuf = new StringBuilder();
			for (Iterator<TacActualParameter> iter = cfgNode.getParamList().iterator(); iter.hasNext(); ) {
				TacActualParameter param = iter.next();
				if (param.isReference()) {
					paramListStringBuf.append("&");
				}
				paramListStringBuf.append(param.getPlace());
				if (iter.hasNext()) {
					paramListStringBuf.append(", ");
				}
			}

			return (
					cfgNode.getFunctionName() + "(" +
							paramListStringBuf.toString() + ") " + "<" +
							cfgNode.getTempVar() + ">");
		} else if (cfgNodeX instanceof CallUnknownFunction) {
			CallUnknownFunction cfgNode = (CallUnknownFunction) cfgNodeX;

			// construct parameter list
			StringBuilder paramListStringBuf = new StringBuilder();
			for (Iterator<TacActualParameter> iter = cfgNode.getParamList().iterator(); iter.hasNext(); ) {
				TacActualParameter param = iter.next();
				if (param.isReference()) {
					paramListStringBuf.append("&");
				}
				paramListStringBuf.append(param.getPlace());
				if (iter.hasNext()) {
					paramListStringBuf.append(", ");
				}
			}

			return ("UNKNOWN: " +
					cfgNode.getFunctionName() + "(" +
					paramListStringBuf.toString() + ") " + "<" +
					cfgNode.getTempVar() + ">");
		} else if (cfgNodeX instanceof AssignArray) {
			AssignArray cfgNode = (AssignArray) cfgNodeX;
			String leftString = cfgNode.getLeft().toString();
			return (leftString + " = array()");
		} else if (cfgNodeX instanceof Unset) {
			Unset cfgNode = (Unset) cfgNodeX;
			String unsetMe = cfgNode.getOperand().getVariable().toString();
			return ("unset(" + unsetMe + ")");
		} else if (cfgNodeX instanceof Echo) {
			Echo cfgNode = (Echo) cfgNodeX;
			String echoMe = cfgNode.getPlace().toString();
			return ("echo(" + echoMe + ")");
		} else if (cfgNodeX instanceof Global) {
			Global cfgNode = (Global) cfgNodeX;
			String globMe = cfgNode.getOperand().toString();
			return ("global " + globMe);
		} else if (cfgNodeX instanceof Static) {
			Static cfgNode = (Static) cfgNodeX;
			String statMe = cfgNode.getOperand().getVariable().toString();
			String initial;
			if (cfgNode.hasInitialPlace()) {
				initial = " = " + cfgNode.getInitialPlace().toString();
			} else {
				initial = "";
			}
			return ("static " + statMe + initial);
		} else if (cfgNodeX instanceof Isset) {
			Isset cfgNode = (Isset) cfgNodeX;
			String checkMe = cfgNode.getRight().getVariable().toString();
			String leftString = cfgNode.getLeft().getVariable().toString();
			return (leftString + " = " + "isset(" + checkMe + ")");
		} else if (cfgNodeX instanceof EmptyTest) {
			EmptyTest cfgNode = (EmptyTest) cfgNodeX;
			String checkMe = cfgNode.getRight().getVariable().toString();
			String leftString = cfgNode.getLeft().getVariable().toString();
			return (leftString + " = " + "empty(" + checkMe + ")");
		} else if (cfgNodeX instanceof Eval) {
			Eval cfgNode = (Eval) cfgNodeX;
			String evalMe = cfgNode.getRight().getVariable().toString();
			String leftString = cfgNode.getLeft().getVariable().toString();
			return (leftString + " = " + "eval(" + evalMe + ")");
		} else if (cfgNodeX instanceof Define) {
			Define cfgNode = (Define) cfgNodeX;
			return ("define(" +
					cfgNode.getSetMe() + ", " +
					cfgNode.getSetTo() + ", " +
					cfgNode.getCaseInsensitive() + ")");
		} else if (cfgNodeX instanceof Include) {
			Include cfgNode = (Include) cfgNodeX;
			String leftString = cfgNode.getTemp().toString();
			String rightString = cfgNode.getIncludeMe().toString();
			return (leftString + " = include " + rightString);
		} else if (cfgNodeX instanceof IncludeStart) {
			return ("incStart");
		} else if (cfgNodeX instanceof IncludeEnd) {
			return ("incEnd");
		} else {
			return "to-do: " + cfgNodeX.getClass();
		}
	}
	@Override
	public String getSignature() {
		// TODO Auto-generated method stub
		return null;
	}


}
