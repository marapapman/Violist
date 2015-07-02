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

import CallGraph.StringCallGraph;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Sources;
import soot.options.Options;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.StronglyConnectedComponentsFast;
import soot.util.Chain;

public class AndroidApp {
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
	 public AndroidApp(){
		 
	 }
	 public  AndroidApp(String androidpath, String ApkPath,String classlist)
	 {
		 //String rtpath="/home/dingli/AppChecker/libs/rt.jar";
		 	//String  Path="/home/dingli/HTTPChecker/oriclasses";
	        Options.v().set_src_prec(Options.src_prec_apk);
	      	Options.v().set_android_jars(androidpath);
	      	Options.v().set_whole_program(true);
	      	Options.v().set_verbose(false);
	      	Options.v().set_keep_line_number(true);
	      	Options.v().set_keep_offset(true);
	      	Options.v().set_allow_phantom_refs(true);
	      	//Options.v().set_output_format(Options.src_prec_J);

	      	List<String> stringlist=new LinkedList<String>();
	      	stringlist.add(ApkPath);
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
						if(!sc.getName().startsWith("android.support"));
						{
							allmethods.addAll(sc.getMethods());
							sc.setApplicationClass();
							appclasses.add(sc);
							for(SootMethod sm:sc.getMethods())
							{
								if(sm.isConcrete())
									entryPoints.add(sm);
							}
							/*try{
								SootMethod onCreate = sc.getMethodByName("onCreate");
								if(onCreate.isConcrete())
								{
									entryPoints.add(onCreate);
									System.out.println(onCreate);
								}

							}
							catch(Exception e)
							{
								//System.out.println("do not conatin main method");
							}
							try{
								SootMethod doInBackground = sc.getMethodByName("doInBackground");
								if(doInBackground.isConcrete())
								{
									//entryPoints.add(doInBackground);
									System.out.println(doInBackground);
								}

							}
							catch(Exception e)
							{
								//System.out.println("do not conatin the main method");
							}*/
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
			System.out.println(this.callgraph.getRTOdering().size());
			for(SootMethod msm:allmethods)
			{
				methodsignatures.add(msm.getSignature());
			}
			
	 }
	public void loadAppClasses(String Path){
		ArrayList<File> filelist=new ArrayList<File>();
		//Options.v().set_src_prec(Options.src_prec_J);
		Options.v().set_whole_program(true);
		Options.v().set_verbose(false);
		Options.v().set_keep_line_number(true);
		Options.v().set_keep_offset(true);
		Options.v().set_allow_phantom_refs(true);
		List<SootMethod> entryPoints=new ArrayList<SootMethod>();
		//Options.v().set_omit_excepting_unit_edges(true);
		Set<SootMethod> allmethods=new HashSet<SootMethod>();
		listf(Path,filelist);
		
		this.addClassPath(Path);

		for(File f:filelist)
		{
			String path=f.getAbsolutePath();
			String classname=path.replaceAll(Path, "");
			if(classname.startsWith("/"))
			{
				classname=classname.replaceFirst("/", "");
			}
			classname=classname.replaceAll("/", ".");
			classname=classname.replaceAll(".jimple", "");

			System.out.println(classname);
			try{
				SootClass sc=Scene.v().loadClassAndSupport(classname);
				allmethods.addAll(sc.getMethods());
				sc.setApplicationClass();
				appclasses.add(sc);
				try{
					SootMethod onCreate = sc.getMethodByName("onCreate");
					entryPoints.add(onCreate);
					System.out.println(onCreate);
				}
				catch(Exception e)
				{
					//System.out.println("do not conatin main method");
				}
				try{
					SootMethod doInBackground = sc.getMethodByName("doInBackground");
					entryPoints.add(doInBackground);
					System.out.println(doInBackground);
				}
				catch(Exception e)
				{
					//System.out.println("do not conatin the main method");
				}


			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

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
		//this.callgraph.display();



		
	}
	public boolean isAppMethod(String sig){
		return this.methodsignatures.contains(sig);
	}
	public boolean isAppMethod(SootMethod m)
	{
		return this.methodsignatures.contains(m.getSignature());

	}
	public  SootClass loadClass(String classname)//only for test
		{
		Options.v().set_whole_program(true);
		Options.v().set_verbose(false);
		Options.v().set_keep_line_number(true);
		Options.v().set_keep_offset(true);
		//Options.v().set_omit_excepting_unit_edges(true);

			SootClass sc=Scene.v().loadClassAndSupport(classname);
			appclasses.add(sc);
			

			Scene.v().loadNecessaryClasses();
			return sc;
		}
	public  void loadAllClasses(Set<String> allclassname)
	{
		Options.v().set_whole_program(true);
		Options.v().set_verbose(false);
		Options.v().set_keep_line_number(true);
		Options.v().set_keep_offset(true);
		//Options.v().set_omit_excepting_unit_edges(true);

		for(String s:allclassname)
		{
			SootClass sc=Scene.v().loadClassAndSupport(s);
			appclasses.add(sc);

		}
		Scene.v().loadNecessaryClasses();
	}
	public void addClassPath(String classpath)
	{
		String cp=classpath + File.pathSeparator + Scene.v().getSootClassPath();
		Scene.v().setSootClassPath(cp);


	}



}
