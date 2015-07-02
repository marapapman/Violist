package usc.edu.HTTPChecker.HTTPChecker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import usc.sql.string.Interpreter;
import usc.sql.string.LayerRegion;
import usc.sql.string.ReachingDefinition;
import usc.sql.string.RegionNode;
import usc.sql.string.Translator;
import edu.usc.sql.graphs.NodeInterface;
import edu.usc.sql.graphs.cfg.BuildCFGs;
import edu.usc.sql.graphs.cfg.CFGInterface;
import SootEvironment.AndroidApp;
import SootEvironment.JavaApp;

public class JApp {
    public static void main( String[] args ) throws FileNotFoundException
    {
    	if(args.length!=3)
    	{
    		System.out.println("Usage: rt.jar app_folder classlist.txt");
    	}
    	JavaApp App=new JavaApp(args[0],args[1],args[2],"void main(java.lang.String[])");
    	
    	
    	for(CFGInterface cfg:App.getCallgraph().getRTOInterface())
    	{
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
    		
    		Interpreter intp = new Interpreter(t,2);
    		
    		
    		
    		System.out.println();
    		int count = 0;
    		int breakcount = 3;
    		for(Entry<String,List<String>> en:t.getTargetLines().entrySet())
    		{
    			System.out.println("Label = "+en.getKey());
    			for(String line: en.getValue())
    			{
    		
    				System.out.print("\n"+line+" "+rd.getAllDef().get(line)+" = \n");
    				for(String output: intp.getValue(line))
    					System.out.println(output+" ");
    			}
    			count++;
    			if(count==breakcount)
    				break;
    			
    		}
    			
 
    		
    		
    	}

    }
}
