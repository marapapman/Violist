package usc.edu.HTTPChecker.HTTPChecker;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import edu.usc.sql.graphs.NodeInterface;
import edu.usc.sql.graphs.cfg.CFGInterface;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.options.Options;
import soot.toolkits.scalar.FlowSet;
import usc.sql.string.Interpreter;
import usc.sql.string.LayerRegion;
import usc.sql.string.ReachingDefinition;
import usc.sql.string.RegionNode;
import usc.sql.string.Translator;
import CallGraph.StringCallGraph;
import SootEvironment.AndroidApp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	
    	if(args.length!=3)
    	{
    		System.out.println("Usage: android_jar_path apk classlist.txt");
    	}
        AndroidApp App=new AndroidApp(args[0],args[1],args[2]);
		
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("/home/yingjun/Documents/StringAnalysis/TestCases/Android/output.txt",true)));

        for(CFGInterface cfg:App.getCallgraph().getRTOInterface())
    	{
        	//System.out.println(cfg.toDot());
        	
    		String signature=cfg.getSignature();
    		if(signature.equals("<LoggerLib.Logger: void <clinit>()>")||signature.equals("<LoggerLib.Logger: void reportString(java.lang.String,java.lang.String)>"))
    		continue;
    		LayerRegion lll = new LayerRegion(null);
    		ReachingDefinition rd = new ReachingDefinition(cfg.getAllNodes(), cfg.getAllEdges(),lll.identifyBackEdges(cfg.getAllNodes(),cfg.getAllEdges(), cfg.getEntryNode()));	   		
    		List<NodeInterface> tp= rd.topoSort(cfg.getAllNodes(),lll.identifyBackEdges(cfg.getAllNodes(),cfg.getAllEdges(), cfg.getEntryNode()));
		
    		LayerRegion lr = new LayerRegion(cfg);
    		RegionNode root = lr.getRoot();
    		
    		System.out.println(signature);
    		Translator t = new Translator(rd, lr,signature,"/home/yingjun/Documents/StringAnalysis/MethodSummary/");
    		
    		Interpreter intp = new Interpreter(t,3);
    		
    		
    		System.out.println();
    		int count = 0;
    		int breakcount = 3;
    		for(Entry<String,List<String>> en:t.getTargetLines().entrySet())
    		{
    			System.out.println("Label = "+en.getKey());
    			pw.println("Label = "+en.getKey());
    			for(String line: en.getValue())
    			{
    		
    				System.out.print("\n"+line+" "+rd.getAllDef().get(line)+" = \n");
    				pw.println("\n"+line+" "+rd.getAllDef().get(line)+" = ");
    				for(String output: intp.getValue(line))
    				{
    					pw.println(output);
    					System.out.println(output+" ");
    				}
    			}
    			count++;
    			if(count==breakcount)
    				break;
    			
    		}
    		pw.flush();
    		
    		
    		
    	}
        pw.close();
        /*
        GlobalAnalyzer ga=new GlobalAnalyzer(App);
        PrintWriter ps=new PrintWriter("./result.txt");
        ga.DumeResult(ps);
        ps.close();*/

    }
}
