package SootEvironment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.usc.sql.graphs.cfg.CFGInterface;
import CallGraph.StringCallGraph;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Value;
import soot.jimple.InterfaceInvokeExpr;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Sources;
import soot.options.Options;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.StronglyConnectedComponentsFast;
import soot.util.Chain;

public class JavaApp {
	private  Set<SootClass> appclasses=new HashSet<SootClass>();
	StringCallGraph callgraph;
	private Set<String> methodsignatures=new HashSet<String>();
	public StringCallGraph getCallgraph(){
		return callgraph;
	}
	private void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	} 
	 public Set<SootClass> getAllAppClasses()
	 {
		 return appclasses;
	 } 
	 public Set<String> getImplementMethodsSig(Value v)
	 {
	 Set<String> methodsig=new HashSet<String>();
	 Set<SootClass> implecalss=new HashSet<SootClass>();
	 if(v instanceof InterfaceInvokeExpr)
	 {
	 InterfaceInvokeExpr iv=(InterfaceInvokeExpr)v;
	 String interfacesig=iv.getMethod().getSignature();
	 String interfacename=iv.getMethod().getDeclaringClass().toString();
	 for(SootClass sc:appclasses)
	 {
	 if(sc.implementsInterface(interfacename))
	 {
	 String classstring=sc.getName();
	 String[] tempholder=interfacesig.split(":");
	 methodsig.add("<"+classstring+":"+tempholder[1]);
	 implecalss.add(sc);
	 }
	 }
	 }
	 return methodsig;
	 }
	 
	 public  JavaApp(String jrepath, String appPath,String classlist, String mainsub)
	 {
		 //String rtpath="/home/dingli/AppChecker/libs/rt.jar";
		 	//String  Path="/home/dingli/HTTPChecker/oriclasses";
	        Options.v().set_soot_classpath(jrepath+":"+appPath);
	      	Options.v().set_whole_program(true);
	      	Options.v().set_verbose(false);
	      	Options.v().set_keep_line_number(true);
	      	Options.v().set_keep_offset(true);
	      	Options.v().set_allow_phantom_refs(true);
	      	//Options.v().set_output_format(Options.src_prec_J);

	      	List<String> stringlist=new LinkedList<String>();
	      	stringlist.add(appPath);
	      	Options.v().set_process_dir(stringlist);
			ArrayList<File> filelist=new ArrayList<File>();
			List<SootMethod> entryPoints=new ArrayList<SootMethod>();
			//Options.v().set_omit_excepting_unit_edges(true);
			Set<SootMethod> allmethods=new HashSet<SootMethod>();
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(classlist));
				String line;
				while ((line = br.readLine()) != null) {
					try{
						SootClass sc=Scene.v().loadClassAndSupport(line);
						//System.out.println("sig"+sc.getName());
						allmethods.addAll(sc.getMethods());
						sc.setApplicationClass();
						appclasses.add(sc);

						for(SootMethod m:sc.getMethods())
						{

							if(m.isStatic()&&m.getSubSignature().equals(mainsub))
								entryPoints.add(m);

						}
						


					}
					catch(Exception e)
					{
						e.printStackTrace();
					}

				}
				br.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scene.v().loadNecessaryClasses();
			Scene.v().setEntryPoints(entryPoints);
			CHATransformer.v().transform();
			CallGraph cg = Scene.v().getCallGraph();
			this.callgraph=new StringCallGraph(cg,allmethods);
			for(SootMethod msm:allmethods)
			{
				methodsignatures.add(msm.getSignature());
			}
			
	 }





}
