package sql.usc.StringResolver.Variables;

import java.util.HashSet;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import sql.usc.StringResolver.MyConstant;
import sql.usc.StringResolver.Variable;

public class DoubleVariable  implements Variable{
	Set<Double> value=new HashSet<Double>();
	String name; 
	public boolean SameValue(Variable v)
	{
		if(!this.equals(v))
			return false;
		return this.value.equals(v.getvalue());
	}
	 public Variable clone()
	 {
		 DoubleVariable v=new DoubleVariable();
		 v.value=new HashSet<Double>();
		 for(Double l:value)
			 v.value.add(l.doubleValue());
		 v.name=this.name;
		 return v;
	 }
	public Automaton StringValue()
	{
		RegExp exp=new RegExp("-?[0-9]+");
		RegExp exp1=new RegExp("-?[0-9]+\\.[0-9]+");
		RegExp exp2=new RegExp("-?[0-9]+\\.[0-9]+E-?[0-9]+");
		Automaton a=exp.toAutomaton();
		a=a.union(exp1.toAutomaton());
		a=a.union(exp2.toAutomaton());
		Automaton r=Automaton.makeEmpty();
		for(Double v:value)
		{
			if(v==MyConstant.UNKNOWN_DOUBLE)
				r=r.union(a);
			else
				r=r.union(Automaton.makeString(v.toString()));
		}
		return r;
	}
	public String toString()
	{
		//return null;
		return name+" double"+" "+value;
	}
	
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name=name;
		
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void addvalue(Variable a) {
		// TODO Auto-generated method stub
		if(a instanceof DoubleVariable)
			value.addAll(a.getvalue());
		else
			throw new Error("wrong type");
		if(value.contains(MyConstant.UNKNOWN_DOUBLE))
		{
			value=new HashSet<Double>();
			value.add(MyConstant.UNKNOWN_DOUBLE);
		}
		
	}
	
	public Set getvalue() {
		// TODO Auto-generated method stub
		return value;
	}
	public int hashCode(){
		return name.hashCode();
		
	}
	public boolean equals(Object obj){
		if(!(obj instanceof Variable))
			return false;
		Variable v=(Variable)obj;
		if(name.equals(v.getName()))
		{
			//System.out.println("true");
			return true;
		}
		return false;
	}
	
	public void cleanvalue() {
		value=new HashSet<Double>();
		// TODO Auto-generated method stub
		
	}
	
	public void AddConst(Object o) {
		// TODO Auto-generated method stub
		value.add((Double)o);
		if(value.contains(MyConstant.UNKNOWN_DOUBLE))
		{
			value=new HashSet<Double>();
			value.add(MyConstant.UNKNOWN_DOUBLE);
		}
		
	}
	
	public void makeEmpty() {
		// TODO Auto-generated method stub
		value=new HashSet<Double>();
	}
	
	public void makeUniverse() {
		// TODO Auto-generated method stub
		AddConst(MyConstant.UNKNOWN_DOUBLE);
	}
}
