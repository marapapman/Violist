package sql.usc.StringResolver;

import java.util.Set;

import soot.Local;
import soot.Value;
import soot.jimple.ArrayRef;
import soot.jimple.FieldRef;
import soot.jimple.InstanceFieldRef;
import soot.jimple.StaticFieldRef;
import soot.tagkit.LineNumberTag;
import sql.usc.StringResolver.Variables.ArrayVariable;
import sql.usc.StringResolver.Variables.FieldVariable;
import sql.usc.StringResolver.Variables.VariableOperation;

public class Definition {
	public Variable v; 
	private int offset;
	public int linenumber;
	//this definition is not created from other definitions, it is a constant, parameter, or a new object
	public boolean leaf;
	public int opcode;
	Definition op1;
	Definition op2;
	Set<Definition> dependset;
	
	public Definition clone(){
		Definition d=new Definition();
		d.v=this.v.clone();
		d.linenumber=this.linenumber;
		d.offset=this.offset;
		d.leaf=this.leaf;
		return d;
		
	}
	public void setoffset(int v)
	{
		offset=v;
	}
	public int getoffset()
	{
		return offset;
	}
	public int hashCode() {
		return 30*v.hashCode()+2*offset;
	}
	public boolean sameVariable(Variable comev)
	{
		return v.equals(comev);
	}
	public String toString()
	{
		return "{"+v+" "+offset+"}";
	}
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Definition))
			return false;
		Definition variable=(Definition)obj;
		//System.out.println("def "+variable.v);
		if(!this.v.equals(variable.v))
			return false;
		if(this.offset!=variable.offset)
			return false;
		return true;
	}
	public static Definition DefFromValue(Value v, int offset, LineNumberTag tag)
	{
		if(v instanceof Local)//local variable
		{
			Definition d=new Definition();
			//Variable myv=new Variable();
			Variable myv=VariableOperation.LocalVariablefromValue(v);
			d.v=myv;
			d.setoffset(offset);
			d.linenumber=tag.getLineNumber();
			return d;		
		}
		//other things are pending for implementation
		else if(v instanceof  FieldRef)
		{
			Definition d=new Definition();
			//Variable myv=new Variable();
			Variable myv=new FieldVariable(v);
			d.v=myv;
			d.setoffset(offset);
			d.linenumber=tag.getLineNumber();
			return d;
		}
		else if(v instanceof   ArrayRef)
		{
			//ArrayRef aref=(ArrayRef)v;
			//return DefFromValue(aref.getBase(), offset, tag);
			//throw new Error("access to ArrayRef is imcommplished");
			Definition d=new Definition();
			//Variable myv=new Variable();
			Variable myv=new ArrayVariable(v);
			d.v=myv;
			d.setoffset(offset);
			d.linenumber=tag.getLineNumber();
			return d;
		}
		else
		{
			throw new Error("Hmm, something funny happens");
		}
	}
}
