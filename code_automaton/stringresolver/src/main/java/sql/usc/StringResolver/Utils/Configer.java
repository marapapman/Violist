package sql.usc.StringResolver.Utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.bcel.classfile.ClassFormatException;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CHATransformer;
import soot.jimple.toolkits.callgraph.CallGraph;
import sql.usc.StringResolver.StringResolver;
import wam.configuration.ConfMainMethod;
import wam.configuration.WAMConfiguration;
import wam.configuration.exceptions.WAMConfigurationException;

public class Configer {
	public Set<SootMethod> entries=new HashSet<SootMethod>(); 
	public CallGraph cg;
	StringResolver sr=new StringResolver();
	public Configer(String pathtoconfig)  throws WAMConfigurationException, ClassFormatException, IOException
	{
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-i386/jre/lib/rt.jar");
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-i386/jre/lib/jce.jar");
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-amd64/jre/lib/rt.jar");
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-amd64/jre/lib/jce.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jasper.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jasper-el.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/annotations-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/commons-dbcp.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jsp-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-i18n-es.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/catalina-ant.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/commons-pool.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/servlet-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-i18n-fr.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/catalina-ha.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/el-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-i18n-ja.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/catalina.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jasper-el.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-coyote.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-util.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/catalina-tribes.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jasper.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-dbcp.jar");

		WAMConfiguration conf=WAMConfiguration.load(pathtoconfig);
		Set<String> allclasses=new HashSet<String>();
		for(String s:conf.getAllClassPaths())
		{
			sr.addClassPath(s);
			Set<String> classes=ClassReader.readClasses(s);
			allclasses.addAll(classes);
		}
		Set<String> subset=new HashSet<String>();
		for(String s:allclasses)
		{
				subset.add(s);
		}
		sr.loadAllClasses(subset);
		Utils.Log("loading finished");
		List<SootMethod> entries=new LinkedList<SootMethod>();

		for(ConfMainMethod mm:conf.getMainMethods())
		{
			SootClass sc=Scene.v().loadClassAndSupport(mm.classname);
			SootMethod methods=sc.getMethod(mm.subsig);
			entries.add(methods);
		}
		Scene.v().setEntryPoints(entries);
		CHATransformer.v().transform();
		Utils.Log("Initilization finished");

	}
	/*public static StringResolver ConfigWith(String pathtoconfig) throws WAMConfigurationException, ClassFormatException, IOException
	{


		//sr.addClassPath("/home/dingli/wam_test/Applications/bookstore/build/new/");
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-i386/jre/lib/rt.jar");
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-i386/jre/lib/jce.jar");
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-amd64/jre/lib/rt.jar");
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-amd64/jre/lib/jce.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jasper.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jasper-el.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/annotations-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/commons-dbcp.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jsp-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-i18n-es.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/catalina-ant.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/commons-pool.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/servlet-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-i18n-fr.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/catalina-ha.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/el-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-api.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-i18n-ja.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/catalina.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jasper-el.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-coyote.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-util.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/catalina-tribes.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/jasper.jar");
		sr.addClassPath("/usr/share/tomcat7/lib/tomcat-dbcp.jar");

		WAMConfiguration conf=WAMConfiguration.load(pathtoconfig);
		Set<String> allclasses=new HashSet<String>();
		for(String s:conf.getAllClassPaths())
		{
			sr.addClassPath(s);
			Set<String> classes=ClassReader.readClasses(s);
			allclasses.addAll(classes);
		}
		Set<String> subset=new HashSet<String>();
		for(String s:allclasses)
		{
			//if(s.equals("org.apache.jsp.Books_jsp"))
			//System.out.println(s);
				subset.add(s);
		}
		sr.loadAllClasses(subset);
		Utils.Log("loading finished");
		List<SootMethod> entries=new LinkedList<SootMethod>();

		for(ConfMainMethod mm:conf.getMainMethods())
		{
			//System.out.println(mm.classname);
			SootClass sc=Scene.v().loadClassAndSupport(mm.classname);
			SootMethod methods=sc.getMethod(mm.subsig);
			entries.add(methods);
		}
		Scene.v().setEntryPoints(entries);
		CHATransformer.v().transform();
		//CallGraph cg=Scene.v().getCallGraph();
		//System.out.println("call graph is built");
		Utils.Log("Initilization finished");
		return sr;
	}*/
}
