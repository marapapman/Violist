package usc.edu.HTTPChecker.HTTPChecker;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import soot.Body;
import soot.SootMethod;
import soot.Unit;
import soot.UnitBox;
import soot.Value;
import soot.ValueBox;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.Jimple;
import soot.jimple.ParameterRef;
import soot.jimple.ReturnStmt;
import soot.jimple.Stmt;
import soot.jimple.StringConstant;
import soot.jimple.internal.JIdentityStmt;
import soot.jimple.internal.JimpleLocal;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.FlowSet;
import soot.util.Chain;

public class Checker {
	private SootMethod method;
	private UnitGraph cfg;
	private Set<Definition> interestingpoints;
	public boolean isInteresting(Definition df)
	{
		return interestingpoints.contains(df);
	}
	private boolean HasUsed(Unit s, Definition df){
		List<ValueBox> l=s.getUseBoxes();
		for(int i=0;i<l.size();i++)
		{
			ValueBox bv=l.get(i);
			if(bv.toString().equals(df.vb.toString()))
				return true;
		}
		return false;
	}
	public Checker(SootMethod m)
	{
		Body b=m.retrieveActiveBody();
		UnitGraph g=new BriefUnitGraph(m.retrieveActiveBody());

		HTTPAnalyzer httpan=new HTTPAnalyzer(g);
		Chain units = b.getUnits();
		Iterator stmtIt = units.snapshotIterator();
		int offset=0;
		// typical while loop for iterating over each statement
		while (stmtIt.hasNext()) {
			Stmt stmt= (Stmt)stmtIt.next();
			if(ToolKit.isHttpOpen(stmt))
			{
	            FlowSet set = (FlowSet) httpan.getFlowBefore(stmt);
	            System.out.println(set.size());

			}
			else if(stmt instanceof ReturnStmt)
			{
				System.out.println(stmt);
	            FlowSet set = (FlowSet) httpan.getFlowBefore(stmt);
	            System.out.println(set.size());
			}

		}
	}
	public Set<Definition> GetInterestingPoints(){
		return interestingpoints;
	}
	public void Dumptofile(PrintWriter pw,String sig){
		pw.println(sig+" "+interestingpoints);
	}
}
