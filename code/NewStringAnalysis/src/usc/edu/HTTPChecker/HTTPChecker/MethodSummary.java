package usc.edu.HTTPChecker.HTTPChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import SootEvironment.AndroidApp;
import soot.Body;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.Stmt;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.FlowSet;
import soot.util.Chain;
class DominationPoint{
	SootInstruction ins;
	Set<SootInstruction> Dominators=new HashSet<SootInstruction>();
	public String toString(){
		String r="";
		r+=ins.toString()+":\n";
		for(SootInstruction dom:Dominators)
		{
			r+="	"+dom.toString()+"\n";
		}
		return r;
	}
}

public class MethodSummary {
	SootMethod sm;
	Set<DominationPoint> internalPostDomination=new HashSet<DominationPoint>();
	Set<SootInstruction> entryPostDominators=new HashSet<SootInstruction>();
	Set<SootInstruction> httpinstructions=new HashSet<SootInstruction>();
	int HTTPcnt;
	public String DominatorStrings(){
		String r="";
		r+=sm.getSignature()+"\n";
		for(DominationPoint dp:internalPostDomination)
		{
			r+=dp.toString()+"\n";
		}
		return r;
	}
	public boolean ReturnHasHTTP(){
		if(entryPostDominators.size()>0)
			return true;
		return false;
	}
	public boolean HasHTTP(){
		if(httpinstructions.size()>0)
			return true;
		return false;
	}
	public void Analyze(HashMap<String, MethodSummary> summarytable){
		Body b=this.sm.retrieveActiveBody();
		UnitGraph g=new BriefUnitGraph(b);
		HTTPAnalyzer httpan=new HTTPAnalyzer(g,summarytable);
		Chain units = b.getUnits();
		Iterator stmtIt = units.snapshotIterator();
		while (stmtIt.hasNext()) {
			Unit stmt= (Unit)stmtIt.next();
        	int offset=httpan.getOffset(stmt);
        	SootInstruction sins=new SootInstruction(this.sm,stmt,offset);
        	entryPostDominators.add(sins);

		}
		// typical while loop for iterating over each statement
		int fullins=entryPostDominators.size();
		stmtIt = units.snapshotIterator();

		while (stmtIt.hasNext()) {
			Unit stmt= (Unit)stmtIt.next();

			if(ToolKit.isHttpOpen(stmt))
			{
				this.HTTPcnt++;
	            FlowSet set = (FlowSet) httpan.getFlowAfter(stmt);
            	int offset=httpan.getOffset(stmt);
            	SootInstruction sins=new SootInstruction(this.sm,stmt,offset);
            	httpinstructions.add(sins);
	            if(set.size()>0)
	            {
	            	DominationPoint dp=new DominationPoint();
	            	dp.ins=sins;
	            	Iterator ir=set.iterator();
	            	while(ir.hasNext())
	            	{
	            		SootInstruction next=(SootInstruction)ir.next();
	            		dp.Dominators.add(next);
	            	}
	            	this.internalPostDomination.add(dp);
	            }
	            

			}
			else if(ToolKit.isInvocation(stmt))
			{
				String signature=ToolKit.getInvokeSignature(stmt);
				if(summarytable.containsKey(signature))
				{
					MethodSummary ms=summarytable.get(signature);
					if(ms.HasHTTP())
					{
		            	int offset=httpan.getOffset(stmt);
		            	SootInstruction sins=new SootInstruction(this.sm,stmt,offset);
		            	httpinstructions.add(sins);
					}
				}
			}
			else if(g.getHeads().contains(stmt))
			{
	            FlowSet set = (FlowSet) httpan.getFlowBefore(stmt);
	            if(set.size()<fullins)
	            {
		            Set<SootInstruction> tempset=new HashSet<SootInstruction>();
		          	Iterator ir=set.iterator();
	            	while(ir.hasNext())
	            	{
	            		SootInstruction next=(SootInstruction)ir.next();
	            		tempset.add(next);
	            	}
	            	entryPostDominators.retainAll(tempset);
	            }
			}
			
		}
		if(entryPostDominators.size()==fullins)
		{
			entryPostDominators=new HashSet<SootInstruction>();
		}
	}
	public MethodSummary(SootMethod m){
		this.sm=m;

	}
	public boolean isInteresting(){
		return internalPostDomination.size()>0;
	}
	public void display(){
		System.out.println("==========start==============");
		System.out.println(sm);
		System.out.println("++++++++++return+++++++++++++");

		System.out.println(entryPostDominators.size());

		System.out.println(entryPostDominators);
		System.out.println("++++++++++Dominators+++++++++++++");

		System.out.println(internalPostDomination.size());

		for(DominationPoint dp:internalPostDomination)
		{
			System.out.println(dp.ins);
			System.out.println(dp.Dominators.size());

			System.out.println(dp.Dominators);

		}


		System.out.println("==========end==============");

	}
}
