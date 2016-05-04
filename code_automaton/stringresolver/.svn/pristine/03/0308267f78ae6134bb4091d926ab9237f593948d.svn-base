package sql.usc.StringResolver.Variables;

import java.util.Set;

import dk.brics.automaton.Automaton;
import soot.ArrayType;
import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.LongType;
import soot.RefType;
import soot.ShortType;
import soot.Type;
import soot.Value;
import soot.jimple.ArrayRef;
import sql.usc.StringResolver.MyConstant;
import sql.usc.StringResolver.Variable;

public class ArrayVariable implements Variable{
	ArrayRef aref;
	Type elementtype;
	String name;
	public  ArrayVariable()
	{

	}
	public  ArrayVariable(Value v)
	{
		if(!(v instanceof   ArrayRef))
			throw new Error("not an ArrayRef");
		aref=(ArrayRef)v;
		elementtype=aref.getType();
		name=v.toString();
		//throw new Error(elementtype.toString());

	}
	public boolean SameValue(Variable v)
	{
			if(!this.equals(v))
				return false;
			return true;
	}
	 public Variable clone()
	 {
		 ArrayVariable v=new ArrayVariable();
		 v.elementtype=this.elementtype;
		 v.name=this.name;
		 return v;
	 }
	public void setelementtype(Type t)
	{
		elementtype=t;
	}
	public String toString()
	{
		return name+" array of"+" "+elementtype;
	}
	public void setName(String n) {
		this.name=n;
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void addvalue(Variable a) {
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
	
	public Automaton StringValue() {
		// TODO Auto-generated method stub
		Type t=aref.getType();
		if(t instanceof ArrayType)
		{
			ArrayVariable r=new ArrayVariable();
			r.name=name;
			r.elementtype=((ArrayType)t).getElementType();
			return r.StringValue();
		}
		else if((t instanceof  IntType)||(t instanceof  LongType)||(t instanceof  ShortType)||(t instanceof  ByteType))
		{
			IntegerVariable r=new IntegerVariable();
			r.name=name;
			r.value.add(MyConstant.UNKNOWN_INT);
			return r.StringValue();
		}
		else if((t instanceof  DoubleType)||(t instanceof  FloatType))
		{
			DoubleVariable r=new DoubleVariable();
			r.name=name;
			r.value.add(MyConstant.UNKNOWN_DOUBLE);
			return r.StringValue();
		}
		else if((t instanceof  BooleanType))
		{
			BooleanVariable r=new BooleanVariable();
			r.name=name;
			return r.StringValue();
		}
		else if((t instanceof  CharType))
		{
			StringVariable r=new StringVariable();
			r.name=name;
			r.value=Automaton.makeAnyChar();
			return r.StringValue();
		}
		else if((t instanceof RefType)&&(t.toString().equals("java.lang.String")))
		{
			StringVariable r=new StringVariable();
			r.name=name;
			r.makeUniverse();
			return r.StringValue();
		}
		else if((t instanceof RefType)&&(t.toString().equals("java.lang.StringBuilder")))
		{
			StringVariable r=new StringVariable();
			r.name=name;
			r.makeUniverse();
			return r.StringValue();
		}
		else
		{
			ObjectVariable r=new ObjectVariable();
			r.name=name;
			r.type=t;
			return r.StringValue();
		}
	}
	
	public Set getvalue() {
		// TODO Auto-generated method stub
		Type t=aref.getType();
		if(t instanceof ArrayType)
		{
			ArrayVariable r=new ArrayVariable();
			r.name=name;
			r.elementtype=((ArrayType)t).getElementType();
			return r.getvalue();
		}
		else if((t instanceof  IntType)||(t instanceof  LongType)||(t instanceof  ShortType)||(t instanceof  ByteType))
		{
			IntegerVariable r=new IntegerVariable();
			r.name=name;
			r.value.add(MyConstant.UNKNOWN_INT);
			return r.getvalue();
		}
		else if((t instanceof  DoubleType)||(t instanceof  FloatType))
		{
			DoubleVariable r=new DoubleVariable();
			r.name=name;
			r.value.add(MyConstant.UNKNOWN_DOUBLE);
			return r.getvalue();
		}
		else if((t instanceof  BooleanType))
		{
			BooleanVariable r=new BooleanVariable();
			r.name=name;
			return r.getvalue();
		}
		else if((t instanceof  CharType))
		{
			StringVariable r=new StringVariable();
			r.name=name;
			r.value=Automaton.makeAnyChar();
			return r.getvalue();
		}
		else if((t instanceof RefType)&&(t.toString().equals("java.lang.String")))
		{
			StringVariable r=new StringVariable();
			r.name=name;
			r.makeUniverse();
			return r.getvalue();
		}
		else if((t instanceof RefType)&&(t.toString().equals("java.lang.StringBuilder")))
		{
			StringVariable r=new StringVariable();
			r.name=name;
			r.makeUniverse();
			return r.getvalue();
		}
		else
		{
			ObjectVariable r=new ObjectVariable();
			r.name=name;
			r.type=t;
			return r.getvalue();
		}
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
