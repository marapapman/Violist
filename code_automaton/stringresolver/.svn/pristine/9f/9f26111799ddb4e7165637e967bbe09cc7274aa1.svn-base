package sql.usc.StringResolver.Variables;

import dk.brics.automaton.Automaton;
import soot.ArrayType;
import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.Local;
import soot.LongType;
import soot.RefType;
import soot.ShortType;
import soot.Type;
import soot.Value;
import soot.jimple.Constant;
import soot.jimple.DoubleConstant;
import soot.jimple.FloatConstant;
import soot.jimple.InstanceFieldRef;
import soot.jimple.IntConstant;
import soot.jimple.LongConstant;
import soot.jimple.StringConstant;
import sql.usc.StringResolver.MyConstant;
import sql.usc.StringResolver.Variable;

public class VariableOperation {
	public static Variable LocalVariablefromValue(Value v)
	{
		if(!(v instanceof Local))
		{
			throw new Error("not a Local Variable");
		}
		Type t=v.getType();
		if(t instanceof ArrayType)
		{
			ArrayVariable r=new ArrayVariable();
			r.name=v.toString();
			r.elementtype=((ArrayType)t).getElementType();
			return r;
		}
		else if((t instanceof  IntType)||(t instanceof  LongType)||(t instanceof  ShortType)||(t instanceof  ByteType))
		{
			IntegerVariable r=new IntegerVariable();
			r.name=v.toString();
			r.value.add(MyConstant.UNKNOWN_INT);
			return r;
		}
		else if((t instanceof  DoubleType)||(t instanceof  FloatType))
		{
			DoubleVariable r=new DoubleVariable();
			r.name=v.toString();
			r.value.add(MyConstant.UNKNOWN_DOUBLE);
			return r;
		}
		else if((t instanceof  BooleanType))
		{
			BooleanVariable r=new BooleanVariable();
			r.name=v.toString();
			return r;
		}
		else if((t instanceof  CharType))
		{
			StringVariable r=new StringVariable();
			r.name=v.toString();
			r.value=Automaton.makeAnyChar();
			return r;
		}
		else if((t instanceof RefType)&&(t.toString().equals("java.lang.String")))
		{
			StringVariable r=new StringVariable();
			r.name=v.toString();
			r.value=Automaton.makeEmpty();
			return r;
		}
		else if((t instanceof RefType)&&(t.toString().equals("java.lang.StringBuilder")))
		{
			StringVariable r=new StringVariable();
			r.name=v.toString();
			r.value=Automaton.makeEmpty();
			return r;
		}
		else
		{
			ObjectVariable r=new ObjectVariable();
			r.name=v.toString();
			r.type=t;
			return r;
		}
		
	} 
	public static Variable ConstVariablefromValue(Value v)
	{
		if(!(v instanceof Constant))
		{
			throw new Error("not a Local Variable");
		}
		Type t=v.getType();
		if((v instanceof IntConstant))
		{
			IntConstant aha=(IntConstant)v;
			IntegerVariable int_targ=new IntegerVariable();
			int_targ.makeEmpty();

			int_targ.AddConst((long)aha.value);
			return int_targ;

		}

		else if((v instanceof LongConstant))
		{
			IntegerVariable long_targ=new IntegerVariable();
			long constv=((LongConstant)v).value;
			long_targ.makeEmpty();
			long_targ.AddConst(constv);
			return long_targ;

		}
		else if((v instanceof FloatConstant))
		{
			DoubleVariable float_targ=new DoubleVariable();
			double constv=((FloatConstant)v).value;
			float_targ.makeEmpty();
			float_targ.AddConst(constv);
			return float_targ;

		}
		else if((v instanceof DoubleConstant))
		{
			DoubleVariable double_targ=new DoubleVariable();
			double constv=((DoubleConstant)v).value;
			double_targ.makeEmpty();
			double_targ.AddConst(constv);
			return double_targ;

		}
		else if((v instanceof StringConstant))
		{
			StringVariable string_targ=new StringVariable();
			String tmpv=((StringConstant)v).value;
			//System.out.println(tmpv);
			string_targ.makeEmpty();
			string_targ.AddConst(tmpv);
			return string_targ;
		}
		else
		{
			ObjectVariable r=new ObjectVariable();
			r.type=t;
			return r;
		}
		
	}
	
}
