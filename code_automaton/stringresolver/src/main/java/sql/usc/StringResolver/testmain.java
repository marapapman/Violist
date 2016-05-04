package sql.usc.StringResolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;


import soot.SootClass;
import soot.SootMethod;
import sql.usc.StringResolver.Variables.IntegerVariable;
import sql.usc.StringResolver.Variables.StringVariable;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;

public class testmain {
	public static void main(String argv[]) throws FileNotFoundException
	{

		ExtendedOperation.print();
		
		/*PrintWriter pw=new PrintWriter("/home/dingli/graph1.gv");
		StringResolver sr=new StringResolver();
		sr.addClassPath("/home/dingli/Dropbox/work/labproject/StringResolver/bin");
		sr.addClassPath("/usr/lib/jvm/java-7-openjdk-i386/jre/lib/rt.jar");
		SootClass sc=sr.loadClass("test.testapp");
		SootMethod methods=sc.getMethodByName("printweb");
		long start=System.currentTimeMillis();
		//System.
		MethodSummary ms=sr.doAnalyze(methods);
		//ms.display();
		Set<Definition> keyset=ms.getAllDefinitions();
		for(Definition def:keyset)
		{
			if(def.getoffset()==65)//in my test case, the string variable is r4 at 38, where 38 is the bytecode offset
			{
				System.out.println(def.v.getName());
				Variable[] vlist=new Variable[2];
				StringVariable p0=new StringVariable();
				p0.cleanvalue();
				p0.AddConst("Oh,Henry");
				IntegerVariable p1=new IntegerVariable();
				p1.cleanvalue();
				p1.AddConst((long)0);
				vlist[0]=p0;
				vlist[1]=p1;
				StringVariable value=(StringVariable)ms.BindQuery(def, vlist);
				Automaton va=value.StringValue();
				pw.println(value.toDot());//print the automaton in graphviz dot format
				
			}
		}
		long end=System.currentTimeMillis();
		System.out.println(end-start);
		pw.close();
		ms.display();
		IntegerVariable iv=new IntegerVariable();
		iv.AddConst(MyConstant.UNKNOWM_INT);*/
		//System.out.println(iv.StringValue().toDot());
		//StringValue
		//System.out.println(a.equals(b));




	}
}
