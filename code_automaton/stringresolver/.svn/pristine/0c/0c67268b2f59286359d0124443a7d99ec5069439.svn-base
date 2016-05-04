package sql.usc.StringResolver.Variables;

import java.util.HashSet;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import sql.usc.StringResolver.MyConstant;
import sql.usc.StringResolver.Variable;

public class BooleanVariable implements Variable{
	Set<Long> value=new HashSet<Long>();
	String name;
	public boolean SameValue(Variable v)
	{
		if(!this.equals(v))
			return false;
		return this.value.equals(v.getvalue());
	}
	 public Variable clone()
	 {
		 BooleanVariable v=new BooleanVariable();
		 v.value=new HashSet<Long>();
		 for(Long l:value)
			 v.value.add(l.longValue());
		 v.name=this.name;
		 return v;
	 }
	public Automaton StringValue()
	{
		RegExp exp=new RegExp("true|false");
		Automaton a=exp.toAutomaton();
		return a;
	}
	public String toString()
	{
		return name+" boolean"+" "+value;
	}
	
	public void setName(String name) {
		this.name=name;
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		return name;
		// TODO Auto-generated method stub
	}
	
	public void addvalue(Variable a) {
		if(a instanceof BooleanVariable)
			value.addAll(a.getvalue());
		else
			throw new Error("wrong type for BooleanVariable");
		if(value.contains(MyConstant.UNKNOWN_INT))
		{
			value=new HashSet<Long>();
			value.add(MyConstant.UNKNOWN_INT);
		}

		// TODO Auto-generated method stub
		
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
	
	public Set getvalue() {
		return value;
		// TODO Auto-generated method stub
	}
	
	public void cleanvalue() {
		value=new HashSet<Long>();
		// TODO Auto-generated method stub
		
	}
	
	public void AddConst(Object o) {
		// TODO Auto-generated method stub
		value.add((Long)o);
		if(value.contains(MyConstant.UNKNOWN_INT))
		{
			value=new HashSet<Long>();
			value.add(MyConstant.UNKNOWN_INT);
		}
		
	}
	
	public void makeEmpty() {
		// TODO Auto-generated method stub
		value=new HashSet<Long>();
	}
	
	public void makeUniverse() {
		// TODO Auto-generated method stub
		AddConst(MyConstant.UNKNOWN_INT);
	}
}
