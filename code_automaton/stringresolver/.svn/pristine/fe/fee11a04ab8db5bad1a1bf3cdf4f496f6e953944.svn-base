package sql.usc.StringResolver.Variables;

import java.util.HashSet;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import sql.usc.StringResolver.MyConstant;
import sql.usc.StringResolver.Variable;

public class IntegerVariable implements Variable{
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
		 IntegerVariable v=new IntegerVariable();
		 v.value=new HashSet<Long>();
		 for(Long l:value)
			 v.value.add(l.longValue());
		 v.name=this.name;
		 return v;
	 }
	public Automaton StringValue()
	{
		RegExp exp=new RegExp("-?[0-9]+");
		Automaton a=exp.toAutomaton();
		if(value.size()==0)
			return a;
		Automaton r=Automaton.makeEmpty();
		for(Long v:value)
		{
			if(v==MyConstant.UNKNOWN_INT)
				return a;
			else
				r=r.union(Automaton.makeString(v.toString()));
		}
		return r;

	}
	public String toString()
	{
		return name+" integer"+" "+value;
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
		if(a instanceof IntegerVariable)
			value.addAll(a.getvalue());
		else
			throw new Error("wrong type");
		if(value.contains(MyConstant.UNKNOWN_INT))
		{
			value=new HashSet<Long>();
			value.add(MyConstant.UNKNOWN_INT);
		}
		
	}
	
	public Set getvalue() {
		// TODO Auto-generated method stub
		return value;
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
