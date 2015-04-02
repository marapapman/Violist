package usc.edu.HTTPChecker.HTTPChecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soot.SootMethod;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.Stmt;
import soot.jimple.internal.JIdentityStmt;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.DominatorsFinder;
import soot.toolkits.graph.MHGDominatorsFinder;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ArraySparseSet;
import soot.toolkits.scalar.BackwardFlowAnalysis;
import soot.toolkits.scalar.FlowSet;
import soot.toolkits.scalar.ForwardFlowAnalysis;

public class HTTPAnalyzer extends BackwardFlowAnalysis{
	private SootMethod method; 
	UnitGraph g;
    FlowSet emptySet = new ArraySparseSet();
    FlowSet FullSet = new ArraySparseSet();
    HashMap<String, MethodSummary> summarytable=null;
	private Map<Unit, FlowSet> unitToGenerateSet;
	private Hashtable<Unit, Integer> offsettable=new Hashtable<Unit, Integer>();
	public HTTPAnalyzer(UnitGraph graph) {
		super(graph);
		this.g=graph;
		method=graph.getBody().getMethod();
	    unitToGenerateSet = new HashMap<Unit, FlowSet>(graph.size() * 2 + 1, 0.7f);
	    int offset=0;
	       for(Iterator unitIt = graph.iterator(); unitIt.hasNext();){
	    	   Unit s = (Unit) unitIt.next();
           		SootInstruction ins=new SootInstruction(this.method,s,offset);
           		FullSet.add(ins);
	            FlowSet genSet = emptySet.clone();
	            if(ToolKit.isHttpOpen((Stmt)s))
	            {
	            	genSet.add(ins);
	            }
	            unitToGenerateSet.put(s, genSet);
	            offsettable.put(s, offset);
	            offset++;
	            
	        }
	       doAnalysis();
		// TODO Auto-generated constructor stub
	}
	public HTTPAnalyzer(UnitGraph graph, HashMap<String, MethodSummary> summarytable) {
		super(graph);
		this.g=graph;
		method=graph.getBody().getMethod();
		this.summarytable=summarytable;
	    unitToGenerateSet = new HashMap<Unit, FlowSet>(graph.size() * 2 + 1, 0.7f);
	    int offset=0;
	       for(Iterator unitIt = graph.iterator(); unitIt.hasNext();){
	    	   Unit s = (Unit) unitIt.next();
           		SootInstruction ins=new SootInstruction(this.method,s,offset);
           		FullSet.add(ins);
	            FlowSet genSet = emptySet.clone();
	            if(ToolKit.isHttpOpen(s))
	            {
	            	//System.out.println(genSet);

	            	genSet.add(ins);

	            }
	            else if(ToolKit.isInvocation(s))
	            {
	            	String signature=ToolKit.getInvokeSignature(s);
	            	if(summarytable.containsKey(signature))
	            	{
	            		MethodSummary summary=summarytable.get(signature);
	            		if(summary.HasHTTP())
	            			genSet.add(ins);
	            	}
	            }
	            if(genSet.size()>0)
	            {
	            	System.out.println(genSet);
	            }
	            unitToGenerateSet.put(s, genSet);
	            offsettable.put(s, offset);
	            offset++;
	            
	        }
	       doAnalysis();
		// TODO Auto-generated constructor stub
	}
	public int getOffset(Unit u){
		return offsettable.get(u);
	}
	@Override
	protected void flowThrough(Object inValue, Object unit, Object outValue) {
		// TODO Auto-generated method stub
		 FlowSet
         in = (FlowSet) inValue,
         out = (FlowSet) outValue;
	        Unit    s   = (Unit)    unit;

	     FlowSet gen=unitToGenerateSet.get(s);
//	            System.out.println("s: "+s+" is not start node");
	            FlowSet domsOfSuccs = FullSet.clone();
	        
	            // for each pred of s
	            Iterator succsIt = g.getSuccsOf(s).iterator();
	            while (succsIt.hasNext()){
	                Unit succ = (Unit)succsIt.next();
	                FlowSet next = (FlowSet) unitToBeforeFlow.get(succ);
	                in.intersection(next, in);
	            }
	        
	       
	            out.intersection(in, out);
	            out.union(gen, out);
	}

	@Override
	protected void copy(Object source, Object dest) {
		// TODO Auto-generated method stub
	    FlowSet
        sourceSet = (FlowSet) source,
        destSet = (FlowSet) dest;

    sourceSet.copy(destSet);
		
	}

	@Override
	protected Object entryInitialFlow() {
		// TODO Auto-generated method stub		
		return emptySet.clone();
	}

	@Override
	protected void merge(Object in1, Object in2, Object out) {
		// TODO Auto-generated method stub
        FlowSet
        inSet1 = (FlowSet) in1,
        inSet2 = (FlowSet) in2,
        outSet = (FlowSet) out;
        inSet1.intersection(inSet2, outSet);
		
	}

	@Override
	protected Object newInitialFlow() {
		// TODO Auto-generated method stub
		return  FullSet.clone();
	}
    

}
