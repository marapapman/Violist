package usc.sql.string.jsm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.*;

public class CFG1 {
	
	static Set<Node> nodes = new HashSet<Node>();
	static List<Edge> edgeList = new ArrayList<Edge>();
	//ASM line number (label) -> source code line number
	static Hashtable<String, Integer> lnSet = new Hashtable<String, Integer>();
	//to output unique edge
	static Set<String> edgeStrList = new HashSet<String>();
	
	private static Printer printer = new Textifier();
	private static TraceMethodVisitor mp = new TraceMethodVisitor(printer);
	private static int srcLn = 0;
	private static String asmLn = "";
	//label whether to construct a new edge from current bytecode
	public static String fromCurrent = null;
	public static BufferedWriter writer;
	
	public static String insnToString(AbstractInsnNode insn) throws IOException{		
		insn.accept(mp);
		StringWriter sw = new StringWriter();
		printer.print(new PrintWriter(sw));
		printer.getText().clear();
		String asmLnPrev = asmLn;
		if(insn.getType() == insn.LABEL){
			asmLn = ((LabelNode)insn).getLabel().toString();
			addLineMapping(asmLn, srcLn);
		}else if(insn.getType() == insn.LINE){
			srcLn = ((LineNumberNode)insn).line;
			asmLn = ((LineNumberNode)insn).start.getLabel().toString();
			addLineMapping(asmLn, srcLn);
			addNode(asmLn, srcLn);
		}
		addEdge(asmLnPrev, asmLn);
		if(insn.getType() == insn.JUMP_INSN){
			String asmGoLn = ((JumpInsnNode)insn).label.getLabel().toString();
			//only consider if jump instruction
			if(insn.getOpcode() != Opcodes.GOTO && insn.getOpcode() != Opcodes.JSR){
				addJumpEdge(asmLn, asmGoLn, 1);
			}else{
				addJumpEdge(asmLn, asmGoLn, 0);
			}
			
		}else if(insn.getType() == insn.INSN){
			if((insn.getOpcode() == Opcodes.RETURN || insn.getOpcode() == Opcodes.IRETURN
					|| insn.getOpcode() == Opcodes.LRETURN || insn.getOpcode() == Opcodes.FRETURN
					|| insn.getOpcode() == Opcodes.DRETURN || insn.getOpcode() == Opcodes.ARETURN)){
				addJumpEdge(asmLn, "exit", 0);
			}
		}else if(insn.getType() == insn.TABLESWITCH_INSN || insn.getType() == insn.LOOKUPSWITCH_INSN){
			addSwitchEdge(asmLn, insn);
		}
		return "";
	}
	
	public static void output_CFG() throws IOException{
		for(int j = 0; j < edgeList.size(); j++){
			Edge e = edgeList.get(j);
			String from = e.getSrcNode().equals("entry") ? "entry" : lnSet.get(e.getSrcNode()).toString();
			String to = e.getDestNode().equals("exit") ? "exit" : lnSet.get(e.getDestNode()).toString();
			String str = from + " -> " + to;
			if(!from.equals(to) && !edgeStrList.contains(str)){
				edgeStrList.add(str);
				writer.write("\t" + str + ";\n");
			}
		}
	}
	
	public static void addNode(String asmLn, int srcLn){
		Node n = new Node(asmLn, srcLn);
		if(!nodes.contains(n)){
			nodes.add(n);
		}
	}
	
	public static void addEdge(String from, String to){
		if(fromCurrent == null){
			addNode("entry", 0);  //assume the source line number of entry node is 0
			Edge e = new Edge("entry", to);
			fromCurrent = "notCurrent";
			edgeList.add(e);
			return;
		}else if(fromCurrent != null && fromCurrent.equals("current")){
			fromCurrent = "notCurrent";
			return;
		}
		if(!from.equals(to)){
			Edge e = new Edge(from, to);
			if(!edgeList.contains(e)){
				edgeList.add(e);
			}
		}
	}
	
	public static void addJumpEdge(String from, String to, int isIfJump){
		addEdge(from, to);
		if(isIfJump == 0){
			fromCurrent = "current";
		}
		if(to.equals("exit")){
			addNode("exit", -1);  //assume the source line number of exit node is -1
		}
	}
	
	public static void addSwitchEdge(String from, AbstractInsnNode insn){
		List<LabelNode> ln = new ArrayList<LabelNode>();
		String asmDefaultGoLn = "";
		if(insn.getType() == insn.TABLESWITCH_INSN){
			ln = ((TableSwitchInsnNode)insn).labels;
			asmDefaultGoLn = ((TableSwitchInsnNode)insn).dflt.getLabel().toString();
		}else if(insn.getType() == insn.LOOKUPSWITCH_INSN){
			ln = ((LookupSwitchInsnNode)insn).labels;
			asmDefaultGoLn = ((LookupSwitchInsnNode)insn).dflt.getLabel().toString();
		}
		for(int i = 0; i < ln.size(); i++){
			String asmGoLn = ln.get(i).getLabel().toString();
			addEdge(from, asmGoLn);
		}
		addEdge(from, asmDefaultGoLn);
	}
	
	public static void addLineMapping(String asmLn, Integer srcLn){
		lnSet.put(asmLn, srcLn);
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 2 && args[0].endsWith(".class")) {
			try {
				//InputStream in = new FileInputStream("/home/guimaocai/CFG/bin/Subject5.class");
				InputStream in = new FileInputStream(args[0]);
				ClassReader reader = new ClassReader(in);
				ClassNode classNode = new ClassNode();
				reader.accept(classNode, 0);
				@SuppressWarnings("unchecked")
				final List<MethodNode> methods = classNode.methods;
				int flag = 0;
				for (MethodNode m : methods) {
					if(!m.name.equals("main")){continue;}
					flag = 1;
					InsnList inList = m.instructions;
					//System.out.println(m.name);
					for (int i = 0; i < inList.size(); i++) {
						//System.out.println(inList.get(i));
						System.out.print(insnToString(inList.get(i)));
					}
				}
				if(flag == 0){
					System.err.println("Method main not found in " + args[0]);
				}
				//String file_path = "/home/guimaocai/workspace/CFG_asm/test.txt";
				String file_path = args[1];
		    	writer = new BufferedWriter(new FileWriter(new File(file_path)));
		    	writer.write("digraph control_flow_graph {\n\n");
		    	writer.write("\tnode [shape = rectangle]; entry exit;\n");
		    	writer.write("\tnode [shape = circle];\n\n");
				output_CFG();
				writer.write("}\n");
		        writer.close();
			} catch (IOException ex) {
	            ex.printStackTrace(System.err);
	        }
		} else {
	        System.out.println
	            ("Usage: ASM class-file output-file");
	        System.out.println("For example: javac -classpath ~/workspace/asm-all-4.1.jar CFG.java CFG.java");
	        System.out.println("For example: java -cp ~/workspace/asm-all-4.1.jar:. CFG Subject1.class out.dotty");
	    }
	}

}