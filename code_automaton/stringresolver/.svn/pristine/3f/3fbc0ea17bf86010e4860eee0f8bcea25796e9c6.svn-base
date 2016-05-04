package sql.usc.StringResolver.Variables;

import java.util.Set;

import dk.brics.automaton.Automaton;
import soot.Type;
import sql.usc.StringResolver.Variable;

public class ObjectVariable implements Variable{
	String name;
	Type type;
	public boolean SameValue(Variable v)
	{
		if(!this.equals(v))
			return false;
		return true;
	}
	public Variable clone()
	{
		ObjectVariable v=new ObjectVariable();
		v.type=this.type;
		 v.name=this.name;
		 return v;
	}
	public String toString()
	{
		return name+" "+type;
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
		
	}
	
	public Automaton StringValue() {
		// TODO Auto-generated method stub
		return Automaton.makeAnyString();
	}
	
	public Set getvalue() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void cleanvalue() {
		// TODO Auto-generated method stub
		
	}
	
	public void AddConst(Object o) {
		// TODO Auto-generated method stub
		
	}
	
	public void makeEmpty() {
		// TODO Auto-generated method stub
		
	}
	
	public void makeUniverse() {
		// TODO Auto-generated method stub
		
	}
}
