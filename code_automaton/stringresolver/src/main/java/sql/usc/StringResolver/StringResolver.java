package sql.usc.StringResolver;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Unit;
import soot.Value;
import soot.jimple.ArrayRef;
import soot.jimple.FieldRef;
import soot.jimple.InstanceFieldRef;
import soot.options.Options;
import soot.tagkit.BytecodeOffsetTag;
import soot.tagkit.LineNumberTag;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.UnitGraph;

public class StringResolver {
	private  Set<SootClass> appclasses=new HashSet<SootClass>();
	 public Set<SootClass> getAllAppClasses()
	 {
		 return appclasses;
	 }
	public  SootClass loadClass(String classname)//only for test
		{
			Options.v().set_whole_program(true);
			Options.v().set_verbose(false);
			Options.v().set_keep_line_number(true);
			Options.v().set_keep_offset(true);
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
		for(String s:allclassname)
		{
			SootClass sc=Scene.v().loadClassAndSupport(s);
			appclasses.add(sc);

		}
		Scene.v().loadNecessaryClasses();
	}
	public static void addClassPath(String classpath)
	{
		String cp=classpath + File.pathSeparator + Scene.v().getSootClassPath();
		Scene.v().setSootClassPath(cp);


	}

	public MethodSummary doAnalyze(SootMethod method)
	{
		if(!method.isConcrete())
			return null;
		MethodSummary report=new MethodSummary(method);
		return report;

	}

		
}
