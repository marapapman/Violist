package sql.usc.StringResolver.Variables;

import java.util.Set;

import sql.usc.StringResolver.ExtendedOperation;
import sql.usc.StringResolver.Variable;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;

public class StringVariable implements Variable{
	Automaton value;
 	String name;
 	public void setAuto(Automaton a)
 	{
 		value=a;
 	}
	public boolean SameValue(Variable v)
	{
		if(!this.equals(v))
			return false;
		//if(this.value.getNumberOfStates())
		return this.value.equals(v.StringValue());
	}
 	public StringVariable()
 	{
 		value=Automaton.makeEmpty();
 	}
	public Variable clone()
	{
		StringVariable v=new StringVariable();
		v.value=this.value.clone();
		 v.name=this.name;
		 return v;
	}
public String toString()
{
	return name+" "+"String"+" "+value.getFiniteStrings();
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
	this.name=name;
	// TODO Auto-generated method stub
	
}

public String getName() {
	// TODO Auto-generated method stub
	return name;
}

public void addvalue(Variable a) {
		this.value=this.value.union(a.StringValue());
		//this.value.determinize();
		//this.value.minimize();
		if(this.value.isEmptyString())
			this.value=Automaton.makeEmptyString();
		
	// TODO Auto-generated method stub
	
}

public Automaton StringValue() {
	// TODO Auto-generated method stub
	return value;
}

public Set getvalue() {
	// TODO Auto-generated method stub
	return value.getFiniteStrings();
}

public void cleanvalue() {
	value=Automaton.makeEmptyString();
	// TODO Auto-generated method stub
	
}


public void AddConst(Object o) {
	// TODO Auto-generated method stub
	value=value.union(Automaton.makeString((String)o));
}
public void Replace(Variable a1, Variable a2)
{
	if(!(a1 instanceof StringVariable))
		throw new Error("wrong input");
	if(!(a2 instanceof StringVariable))
		throw new Error("wrong input");
	value=ExtendedOperation.replace(value, a1.StringValue(), a2.StringValue());
	String seperator=""+ExtendedOperation.parameter_seperator;

	Automaton tmp1=ExtendedOperation.makeAnyString();
	
	Automaton tmp2=Automaton.makeString(seperator);
	Automaton tmp3=tmp2.concatenate(tmp1);
	Automaton a3_0=tmp3.concatenate(Automaton.makeString(seperator));
	Automaton a3_1=ExtendedOperation.makeAnyString().concatenate(a3_0);
	Automaton a3=a3_1.concatenate(ExtendedOperation.makeAnyString());
	if(!value.intersection(a3).isEmpty())//it includes some parameters
		value=ExtendedOperation.makeAnyString();
	//value.determinize();
	//value.minimize();
}
public void Concatenate(Variable v)
{
	Automaton a=v.StringValue();
	//System.out.println("in cat "+a.getFiniteStrings());
	//System.out.println("in cat "+value.getFiniteStrings());
	value=value.concatenate(a);
	//value.determinize();
	//value.minimize();
	//System.out.println("in cat "+value.getFiniteStrings());
}
public void WidenWith(StringVariable v)
{
	//System.out.println("Widenning "+this+" "+v);
	this.value=ExtendedOperation.Widen(this.value, v.value);
	//value.determinize();
	//value.minimize();
}
public void MakeAllString()
{
	this.value=ExtendedOperation.makeAnyString();
}
public String toDot()
{
	return this.value.toDot();
}

public void makeEmpty() {
	// TODO Auto-generated method stub
	this.value=Automaton.makeEmpty();
}

public void makeUniverse() {
	// TODO Auto-generated method stub
	value=Automaton.makeString("any");
}
}
