package sql.usc.StringResolver;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;


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
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.ValueBox;
import soot.dava.internal.javaRep.DIdentityStmt;
import soot.grimp.internal.GIdentityStmt;
import soot.jimple.ArithmeticConstant;
import soot.jimple.ArrayRef;
import soot.jimple.AssignStmt;
import soot.jimple.Constant;
import soot.jimple.DefinitionStmt;
import soot.jimple.DoubleConstant;
import soot.jimple.FieldRef;
import soot.jimple.FloatConstant;
import soot.jimple.IdentityStmt;
import soot.jimple.InstanceFieldRef;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.IntConstant;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.LongConstant;
import soot.jimple.NewExpr;
import soot.jimple.ParameterRef;
import soot.jimple.SpecialInvokeExpr;
import soot.jimple.StringConstant;
import soot.jimple.internal.JIdentityStmt;
import soot.tagkit.BytecodeOffsetTag;
import soot.tagkit.LineNumberTag;
import soot.toolkits.graph.BriefUnitGraph;
import soot.toolkits.graph.UnitGraph;
import sql.usc.StringResolver.Variables.ArrayVariable;
import sql.usc.StringResolver.Variables.BooleanVariable;
import sql.usc.StringResolver.Variables.DoubleVariable;
import sql.usc.StringResolver.Variables.FieldVariable;
import sql.usc.StringResolver.Variables.IntegerVariable;
import sql.usc.StringResolver.Variables.ObjectVariable;
import sql.usc.StringResolver.Variables.StringVariable;
import sql.usc.StringResolver.Variables.VariableOperation;

public class MethodSummary implements Serializable{
	// at this time , we don't handle array and fieldref yet
	//Hashtable<Variable> localvariable;	//public pu
	SootMethod sootmethod;
	private Hashtable<Definition, Variable> Valuetable=new Hashtable<Definition, Variable>();
	private Hashtable<Unit, Set<Definition>> IN=new Hashtable<Unit,  Set<Definition>>();
	private Hashtable<Unit,  Set<Definition>> OUT=new Hashtable<Unit,  Set<Definition>>();
	private Set<Definition> parameters=new HashSet<Definition>();
	private Hashtable<Definition, Relience> dominate=new Hashtable<Definition, Relience>();
	private UnitGraph cfg;
	private final int lowthreshold=2;
	private final int highthreshold=10;
	public String getMethodname()
	{
		return sootmethod.getName();
	}
	public String getSig()
	{
		return sootmethod.getSignature();
	}
	public Set<Definition> SearchSameValue(Set<Definition> set, Value v)
	{
		Set<Definition> samevalue=new HashSet<Definition>();
		Variable in=VarFromValue(v);
		for(Definition def:set)
		{
			if(def.sameVariable(in))
				samevalue.add(def);
		}
		return samevalue;
	}
	public UnitGraph getCFG()
	{
		return cfg;
	}
	public Set<Definition> getAllDefinitions()
	{
		return Valuetable.keySet();
	}
	public Set<Definition> QueryInset(Unit u)
	{
		return IN.get(u);
	}
	public Set<Definition> QueryOUTset(Unit u)
	{
		return OUT.get(u);
	}
	public Variable QueryValue(Definition def)
	{
		return Valuetable.get(def);
	}
	public Variable BindQuery(Definition def, Variable[] values)
	{
		if(values.length!=sootmethod.getParameterCount())
			throw new Error("the number of parameter dose not match");
		for(int i=0;i<values.length;i++)
		{
			Type t=sootmethod.getParameterType(i);
			if(t instanceof ArrayType)
			{
				if(!(values[i] instanceof ArrayVariable))
					throw new Error("the parameter "+i+" does not match Arraytype");
			}
			else if((t instanceof  IntType)||(t instanceof  LongType)||(t instanceof  ShortType)||(t instanceof  ByteType))
			{
				if(!(values[i] instanceof IntegerVariable))
					throw new Error("the parameter "+i+" does not match Integer or Long or Short");
			}
			else if((t instanceof  DoubleType)||(t instanceof  FloatType))
			{
				if(!(values[i] instanceof DoubleVariable))
					throw new Error("the parameter "+i+" does not match Integer or Double or Float");
			}
			else if((t instanceof  BooleanType))
			{
				if(!(values[i] instanceof BooleanVariable))
					throw new Error("the parameter "+i+" does not match Boolean");
			}
			else if((t instanceof  CharType))
			{
				if(!(values[i] instanceof StringVariable))
					throw new Error("the parameter "+i+" does not match String or Char");
			}
			else if((t instanceof RefType)&&(t.toString().equals("java.lang.String")))
			{
				if(!(values[i] instanceof StringVariable))
					throw new Error("the parameter "+i+" does not match String or Char");
			}
			else if((t instanceof RefType)&&(t.toString().equals("java.lang.StringBuilder")))
			{
				if(!(values[i] instanceof StringVariable))
					throw new Error("the parameter "+i+" does not match String or Char");
			}
			else
			{
				if(!(values[i] instanceof ObjectVariable))
					throw new Error("the parameter "+i+" does not match Object");
			}

				
		}
		Variable v = Valuetable.get(def);
		
		if(v instanceof StringVariable)
		{
			Automaton rvalue=v.StringValue();
			for(int i=0;i<values.length;i++)
			{
				if(values[i] instanceof StringVariable)
				{
					String seperator=""+ExtendedOperation.parameter_seperator;
					String key=seperator+"parameter"+i+seperator;
					Automaton para=Automaton.makeString(key);
					rvalue=ExtendedOperation.replace(rvalue, para,values[i].StringValue() );
				}
			}
			((StringVariable) v).setAuto(rvalue);
			return v;
		}
		else
		{
			return v;
		}
	}
	/**
	 * Caculate the KILL Set of Unit u
	 * @param u
	 * @return
	 */
	private Set<Definition> KILL(Unit u)
	{
		Set<Definition> kill_set=new HashSet<Definition>();
		Set<Definition> gen_set= GEN(u);
		Set<Definition> in_set=IN.get(u);
		//if()
		Iterator<Definition> irgen=gen_set.iterator();
		Iterator<Definition> irin=in_set.iterator();
		while(irgen.hasNext())
		{
			Definition newdef=irgen.next();
			while(irin.hasNext())
			{
				Definition ain=irin.next();
				if(ain.v.equals(newdef.v))
					kill_set.add(ain);
					
			}
		}
		return kill_set;
		
	}
	private Variable VarFromValue(Value v)
	{
		if(v instanceof Local)//local variable
		{
			//Variable myv=new Variable();
			Variable myv=VariableOperation.LocalVariablefromValue(v);
			return myv;		
		}
		else if(v instanceof Constant)
		{
			Variable myv=VariableOperation.ConstVariablefromValue(v);
			return myv;		
		}
		//other things are pending for implementation
		else if(v instanceof  FieldRef){
			//System.out.println("access to FieldRef is imcommplished");
			return new FieldVariable(v);
			//throw new Error("access to InstanceFieldRef is imcommplished");
		}
		else if(v instanceof   ArrayRef)
		{
			//System.out.println("access to ArrayRef is imcommplished");
			//Variable myv=VariableOperation.LocalVariablefromValue(v);
			return new ArrayVariable(v);
			//throw new Error("access to ArrayRef is imcommplished");
		}
		else
		{
			throw new Error("Hmm, something funny happens");
		}
		//return null;		

	}
	/**
	 * Caculate the GEN Set of Unit u
	 * @param u
	 * @return
	 */
	private Set<Definition> GEN(Unit u)
	{
		Set<Definition> gen_set=new HashSet<Definition>();
		Iterator<ValueBox> ir=u.getDefBoxes().iterator();
		LineNumberTag tag=(LineNumberTag)u.getTag("LineNumberTag");
		BytecodeOffsetTag jtag=(BytecodeOffsetTag)u.getTag("BytecodeOffsetTag");
		int offset;
		if(jtag==null)
			offset=-1;
		else
			offset=jtag.getBytecodeOffset();
		while(ir.hasNext())
		{
			ValueBox vb=ir.next();
			Value v=vb.getValue();
			Definition def=Definition.DefFromValue(v, offset, tag);
			if(def!=null)
				gen_set.add(def);
		}
		if(u instanceof InvokeStmt)//handle StringBuilder.append
		{
			InvokeStmt ismt=(InvokeStmt)u;
			InvokeExpr iexp=ismt.getInvokeExpr();
			if(iexp instanceof  InstanceInvokeExpr)
			{
				Value base=((InstanceInvokeExpr) iexp).getBase();
				Definition def=Definition.DefFromValue(base, offset, tag);
				if(def!=null)
					gen_set.add(def);
			}
		}
		return gen_set;
	}
	private void dataflow()
	{
		Iterator<Unit> ir=cfg.iterator();
		while(ir.hasNext())
		{
			Unit u=ir.next();
			Set<Definition> in_set=new HashSet<Definition>();
			Set<Definition> out_set=new HashSet<Definition>();

			IN.put(u,in_set);
			OUT.put(u, out_set);
		}
		boolean flag=true;
		while(flag)
		{	flag=false;
			ir=cfg.iterator();
			while(ir.hasNext())
			{
				Unit u=ir.next();
				Set<Definition> in_set;
				Set<Definition> old_in_set=IN.get(u);
				Set<Definition> new_in_set=new HashSet<Definition>();
				Iterator<Unit> pres=cfg.getPredsOf(u).iterator();
				while(pres.hasNext())
				{
					Unit pre=pres.next();
					new_in_set.addAll(OUT.get(pre));
				}
				/**
				 * In = In U all the predessor's Out
				 */
				if(!old_in_set.equals(new_in_set))
				{
					/**
					 * Not reaching a fixed point, set the flag to true
					 */
					flag=true;
					/*Set<Definition> gen_set=GEN(u);
					Set<Definition> kill_set=KILL(u);
					System.out.println("changed");
					System.out.println(u);
					System.out.println("old: "+old_in_set);
					System.out.println("new: "+new_in_set);
					System.out.println("gen: "+gen_set);
					System.out.println("kill: "+kill_set);*/

					in_set=new_in_set;
					IN.put(u, new_in_set);
				}
				else
				{
					in_set=old_in_set;
				}
				Set<Definition> gen_set=GEN(u);
				Set<Definition> kill_set=KILL(u);
				/**
				 * Out = (In - Kill) U Gen
				 */
				Set<Definition> out_set=new HashSet<Definition>();
				out_set.addAll(in_set);
				out_set.removeAll(kill_set);
				out_set.addAll(gen_set);
				OUT.put(u, out_set);
				/*System.out.println(u);
				System.out.println("in: "+in_set);
				System.out.println("gen: "+gen_set);
				System.out.println("kill: "+kill_set);
				System.out.println("out: "+out_set);*/

			
			}
		}
	}
	public void display()
	{
		Set<Definition> de=Valuetable.keySet();
		for(Definition def:de)
		System.out.println(def.v.getName()+" "+def.getoffset()+" = "+Valuetable.get(def));

	}
	/**
	 * Assign the pair <Definition, Variable> according to Out Set
	 */
	private void initvaluetable()
	{
		Iterator<Unit> ir=cfg.iterator();
		while(ir.hasNext())
		{
			Unit u=ir.next();
			Set<Definition> out_set=OUT.get(u);
			Iterator<Definition> outir=out_set.iterator();
			while(outir.hasNext())
			{
				Definition n=outir.next();
				Valuetable.put(n, n.v);
			}
		}
		
	}
	private void String_init_handler(Unit u)
	{
		InvokeStmt ismt=(InvokeStmt)u;
		InvokeExpr iexp=ismt.getInvokeExpr();
		Set<Definition> out=OUT.get(u);
		Value base=((InstanceInvokeExpr) iexp).getBase();
		Variable var=VarFromValue(base);
		Iterator<Definition> outir=out.iterator();
		while(outir.hasNext())
		{
			Definition def=outir.next();
			//System.out.println("bb"+def);
			/**
			 * When this definition is not created from other definition
			 */
			if(def.sameVariable(var))
			{
				def.leaf=true;
				Variable value=Valuetable.get(def);
				StringVariable stringv=(StringVariable)value;
				// Make the new stringv's value is empty
				stringv.cleanvalue();
				Valuetable.put(def, value);
				//System.out.println("aa"+Valuetable.get(def));
			}
		}
	}
	private void StringBuilder_init_handler(Unit u)
	{
		Set<Definition> in=IN.get(u);
		Set<Definition> out=OUT.get(u);
		InvokeStmt ismt=(InvokeStmt)u;
		InvokeExpr iexp=ismt.getInvokeExpr();
		Value base=((InstanceInvokeExpr) iexp).getBase();
		Variable var=VarFromValue(base);
		StringVariable input=new StringVariable();
		input.makeEmpty();
		//If StringBuild has no arguments, it's a empty string
		if(iexp.getArgCount()==0)
			input.cleanvalue();
		else
		{
			Value par=iexp.getArg(0);
			/**
			 * If the only argument is a variable, not a constant.
			 * Search the variable from In Set, get the value from Valuetable, then merge into input 
			 */
			if(!(par instanceof StringConstant))
			{
				Variable parav=VarFromValue(par);
				for(Definition def:in)
				{
					if(def.sameVariable(parav))
					{
						StringVariable par1=(StringVariable)Valuetable.get(def);
						input.addvalue(par1);
						//System.out.println("aa"+Valuetable.get(def));
					}
				}
			}
			/**
			 * Or it's a string constant, then merge the constant value into input
			 */
			else{
				StringConstant sc=(StringConstant)par;
				input.AddConst(sc.value);
			//System.out.println("aa"+sc.value);
			}
		}
		for(Definition def:out)
		{
			//System.out.println("bb"+def);
			if(def.sameVariable(var))
			{
				//System.out.println("StringBuilder_init_handler "+input+" "+var);

				def.leaf=false;
				Variable value=Valuetable.get(def);
				StringVariable stringv=(StringVariable)value;
				stringv.makeEmpty();
				stringv.addvalue(input);
				Valuetable.put(def, stringv);
				//System.out.println("aa"+Valuetable.get(def));
			}
		}
	}
	private void Assign_Conatant_Handler(AssignStmt ass)
	{
		Variable target=VarFromValue(ass.getLeftOp());
		Value exp=ass.getRightOp();
		
		//System.out.println(exp);
		if((exp instanceof IntConstant)&&(target instanceof IntegerVariable))
		{
			IntegerVariable int_targ=(IntegerVariable)target;
			long constv=((IntConstant)exp).value;
			int_targ.makeEmpty();
			int_targ.AddConst(constv);
		}
		else if((exp instanceof IntConstant)&&(target instanceof BooleanVariable))
		{
			BooleanVariable bool_targ=(BooleanVariable)target;
			long constv=((IntConstant)exp).value;
			bool_targ.makeEmpty();
			bool_targ.AddConst(constv);
		}
		else if((exp instanceof LongConstant))
		{
			IntegerVariable long_targ=(IntegerVariable)target;
			long constv=((LongConstant)exp).value;
			long_targ.makeEmpty();
			long_targ.AddConst(constv);
		}
		else if((exp instanceof FloatConstant))
		{
			DoubleVariable float_targ=(DoubleVariable)target;
			double constv=((FloatConstant)exp).value;
			float_targ.makeEmpty();
			float_targ.AddConst(constv);
		}
		else if((exp instanceof DoubleConstant))
		{
			DoubleVariable double_targ=(DoubleVariable)target;
			double constv=((DoubleConstant)exp).value;
			double_targ.makeEmpty();
			double_targ.AddConst(constv);
		}
		else if((exp instanceof StringConstant))
		{
			//Debuglog.log(target.getName());
			StringVariable string_targ;
			if(target instanceof StringVariable)
				 string_targ=(StringVariable)target;
			else
			{
				target=new StringVariable();
				string_targ=(StringVariable)target;

			}
			String tmpv=((StringConstant)exp).value;
			//System.out.println(tmpv);
			string_targ.makeEmpty();
			string_targ.AddConst(tmpv);
		}
		Set<Definition> out=OUT.get(ass);
		Iterator<Definition> outir=out.iterator();
		while(outir.hasNext())
		{
			Definition def=outir.next();
			//System.out.println("bb"+def);
			if(def.sameVariable(target))
			{
				def.leaf=true;
				Variable value=Valuetable.get(def);
				def.opcode=MyConstant.opcode_assign;

				value.makeEmpty();
				value.addvalue(target);
				Valuetable.put(def, value);
				//System.out.println("aa"+Valuetable.get(def));
			}
		}
		
	}
	/**
	 * Handle the ValueOf(), merge the value of it 
	 * @param invoke
	 * @param in_set
	 * @return
	 */
	public Variable String_ValueofObj_Handler(InvokeExpr invoke, Set<Definition> in_set)//return the possible values
	{
		Variable r=new StringVariable();
		if(!invoke.getMethodRef().toString().equals("<java.lang.String: java.lang.String valueOf(java.lang.Object)>"))
		{
			throw new Error("wrong invoke String_ValueofObj_Handler");
		}
		Value arg=invoke.getArg(0);
		Type t=arg.getType();
		if(!t.toString().equals("java.lang.String"))
		{
			throw new Error("unknown type");
		}
		Iterator<ValueBox> vbir=invoke.getUseBoxes().iterator();
		while(vbir.hasNext())
		{
			ValueBox vb=vbir.next();
			Value v=vb.getValue();
			if(v instanceof StringConstant)
				{
					StringConstant sc=(StringConstant)v;
					r.makeEmpty();
					r.AddConst(sc.value);
				}
			else if(v instanceof Local)
			{
				
				Variable var=VariableOperation.LocalVariablefromValue(v);
				Iterator<Definition> ir=in_set.iterator();
				r.makeEmpty();
				while(ir.hasNext())
				{
					Definition def=ir.next();
					if(def.sameVariable(var))
					{
						Variable defv=Valuetable.get(def);
						//System.out.println(def+" = "+defv);
						//System.out.println(defv.StringValue().toDot());

						r.addvalue(defv);
						//System.out.println(r.StringValue().toDot());

					}
				}
				//System.out.println(r.StringValue().toDot());
			}
			else
				throw new Error("Someting interesting happens in String_ValueofObj_Handler");
		}
		return r;
	}
	public Variable String_append_Handler(InvokeExpr invoke, Set<Definition> in_set)
	{
		if(!invoke.getMethodRef().toString().startsWith("<java.lang.StringBuilder: java.lang.StringBuilder append"))
		{
			throw new Error("wrong invoke StringBuilder append");
		}
		if(!(invoke instanceof InstanceInvokeExpr))
			throw new Error("wrong type");
		Value arg=invoke.getArg(0);
		Variable inputs;
		Integer inputcnt=0;
		if(arg instanceof Constant)//all possible input
		{
			inputs=VarFromValue(arg);
			//System.out.println(inputs.StringValue().toDot());
		}
		else if(arg instanceof Local)
		{
			inputs=VarFromValue(arg);
			inputs.makeEmpty();
			for(Definition def:in_set)
			{
				if(def.sameVariable(inputs))
				{
					Variable curvalue=Valuetable.get(def);
					inputs.addvalue(curvalue);
					inputcnt++;
				}
			}
			
		}
		else
			throw new Error("wrong type");
		//System.out.println(inputs.StringValue().toDot());
		StringVariable r=new StringVariable();
		r.makeEmpty();
		Value b=((InstanceInvokeExpr)invoke).getBase();
		Variable base=VarFromValue(b);
		//System.out.println(base+" "+base.StringValue().getFiniteStrings());
		for(Definition def:in_set)
		{
			if(def.sameVariable(base))
			{
				StringVariable curvalue=(StringVariable)Valuetable.get(def);
				r.addvalue(curvalue);
				//curvalue.cleanvalue();
				//curvalue.Concatenate(inputs);
				
			}
		}
		//System.out.println(r.StringValue().toDot());
		//System.out.println(inputs+" a "+inputs.StringValue().getFiniteStrings());
		//System.out.println(r+" b "+r.StringValue().getFiniteStrings());
		//System.out.println(r.StringValue().getFiniteStrings());
		//System.out.println(inputs.StringValue().getFiniteStrings());
		r.Concatenate(inputs);
		//System.out.println(r.StringValue().toDot());
		/*StringVariable tmp=new StringVariable();
		tmp.makeEmpty();
		tmp.addvalue(inputs);
		StringVariable number=new StringVariable();
		number.setAuto(Automaton.makeString(inputcnt.toString()));
		tmp.Concatenate(number);*/
		//System.out.println(r+" c "+r.StringValue().getFiniteStrings());

		//System.out.println(var+" "+var.getvalue());
		return r;
	}
	private Variable String_replaceAll_Handler(InvokeExpr invoke, Set<Definition> in_set)
	{
		Value b=((InstanceInvokeExpr)invoke).getBase();
		Variable base=VarFromValue(b);
		base.makeEmpty();
		for(Definition def:in_set)
		{
			if(def.sameVariable(base))
			{

				Variable value=Valuetable.get(def);
				//System.out.println(value);

				base.addvalue(value);
			}

		}
		if(!base.StringValue().isFinite())//if the base is not finite, see the bug report index 1
		{
			StringVariable r=new StringVariable();
			r.makeUniverse();
			return r;
		}
		Value arg0=invoke.getArg(0);
		Variable pattern;
		if(arg0 instanceof Constant)//all possible input
		{
			//
			pattern=VarFromValue(arg0);
			if(!(pattern instanceof StringVariable))
				throw new Error("wrong type");
			String reg=((StringConstant)arg0).value;
			RegExp exp=new RegExp(reg);
			((StringVariable)pattern).setAuto(exp.toAutomaton());

		}
		else //see the bug report index 2
		{
			StringVariable r=new StringVariable();
			r.makeUniverse();
			return r;

		}

		//if(!pattern.StringValue().isFinite())
		//	throw new Error("patterns must be finite");
		Value arg1=invoke.getArg(1);
		Variable input;
		if(arg1 instanceof Constant)//all possible input
		{
			input=VarFromValue(arg1);
			//System.out.println(inputs.StringValue().toDot());
		}
		else if(arg1 instanceof Local)
		{
			input=VarFromValue(arg1);
			input.makeEmpty();
			for(Definition def:in_set)
			{
				if(def.sameVariable(input))
				{
					Variable curvalue=Valuetable.get(def);
					input.addvalue(curvalue);
				}
			}
			
		}
		else
			throw new Error("wrong type");
		StringVariable r=new StringVariable();
		System.out.println("replacement "+base.StringValue().getFiniteStrings());
		System.out.println("replacement by"+pattern.StringValue().getFiniteStrings());

		System.out.println("replacement with"+input.StringValue().getFiniteStrings());

		r.makeEmpty();
		r.addvalue(base);
		System.out.println("to"+r.StringValue().getFiniteStrings());

		r.Replace(pattern, input);
		System.out.println("to"+r.StringValue().getFiniteStrings());
		System.out.println(r.StringValue().toDot());
		

		return r;
	}
	private void Assign_Invoke_handler(AssignStmt ass)//handle invoke instructions
	{
		Value exp=ass.getRightOp();
		if(!(exp instanceof InvokeExpr))
		{
			throw new Error("Wrong type in Assign_Invoke_handler");
		}
		InvokeExpr invoke=(InvokeExpr)exp;
		String sig=invoke.getMethodRef().toString();
		Set<Definition> in_set=IN.get(ass);
		Set<Definition> out_set=OUT.get(ass);
		Variable target=VarFromValue(ass.getLeftOp());

		if(sig.equals("<java.lang.String: java.lang.String valueOf(java.lang.Object)>"))
		{
			Variable r=String_ValueofObj_Handler(invoke,in_set);
			Iterator<Definition> outir=out_set.iterator();
			//System.out.println(target);
			while(outir.hasNext())
			{
				Definition def=outir.next();
				//System.out.println("bb"+def);
				if(def.sameVariable(target))
				{
					def.leaf=false;
					//System.out.println(def);
					def.opcode=MyConstant.opcode_assign;

					Variable value=Valuetable.get(def);
					value.makeEmpty();
					value.addvalue(r);
					Valuetable.put(def, value);
					//System.out.println("aa"+Valuetable.get(def));
				}
			}
			//System.out.println(sig);
		}
		else if(sig.startsWith("<java.lang.StringBuilder: java.lang.StringBuilder append"))//all kinds of string append
		{
			//System.out.println(sig);
			Variable r=String_append_Handler(invoke,in_set);
			for(Definition def:out_set)
			{
				if(def.sameVariable(target))
				{
					def.opcode=MyConstant.opcode_assign;

					Variable value=Valuetable.get(def);
					value.makeEmpty();
					value.addvalue(r);
					Valuetable.put(def, r);
				}
			}
			
		}
		else if(sig.equals("<java.lang.StringBuilder: java.lang.String toString()>"))
		{
			//System.out.println(sig);
			//Variable r=String_append_Handler(invoke,in_set);
			Value b=((InstanceInvokeExpr)invoke).getBase();
			Variable base=VarFromValue(b);
			base.makeEmpty();
			for(Definition def:in_set)
			{
				if(def.sameVariable(base))
				{

					Variable value=Valuetable.get(def);
					//System.out.println(value);
					def.opcode=MyConstant.opcode_assign;

					base.addvalue(value);
				}

			}
			//System.out.println(base);
			for(Definition def:out_set)
			{
				if(def.sameVariable(target))
				{
					Variable value=Valuetable.get(def);
					value.makeEmpty();
					value.addvalue(base);
					def.opcode=MyConstant.opcode_assign;

					Valuetable.put(def, value);
				}
			}
			
		}
		else if(sig.equals("<java.lang.String: java.lang.String replaceAll(java.lang.String,java.lang.String)>"))
		{
			Variable r=String_replaceAll_Handler(invoke,in_set);
			for(Definition def:out_set)
			{
				if(def.sameVariable(target))
				{
					Variable value=Valuetable.get(def);
					value.makeEmpty();
					value.addvalue(r);
					//System.out.println("to "+r);
					//System.out.println("target "+def);
					def.opcode=MyConstant.opcode_assign;

					Valuetable.put(def, value);
				}
			}
		}
		else
		{
			for(Definition def:out_set)
			{
				if(def.sameVariable(target))
				{
					Variable value=Valuetable.get(def);
					value.makeUniverse();
					//System.out.println("to "+r);
					//System.out.println("target "+def);
					def.opcode=MyConstant.opcode_assign;

					Valuetable.put(def, value);
				}
			}
		}
	}
	private void singleIteration()
	{
		Iterator<Unit> ir=cfg.iterator();
		//display();

		while(ir.hasNext())
		{
			Unit u=ir.next();
			//System.out.println(u);
			if(u instanceof  InvokeStmt)//model new StringBuilder(String), new String()
			{
				//System.out.println("singleIteration_debug_invoke");
				InvokeStmt ismt=(InvokeStmt)u;
				InvokeExpr iexp=ismt.getInvokeExpr();
				String sig=iexp.getMethodRef().toString();
				//System.out.println(sig);
				if(sig.equals("<java.lang.String: void <init>()>"))
					String_init_handler(u);
				else if(sig.equals("<java.lang.StringBuilder: void <init>(java.lang.String)>"))
					StringBuilder_init_handler(u);
				else if(sig.startsWith("<java.lang.StringBuilder: void <init>"))
					StringBuilder_init_handler(u);
				else
				{
					if(iexp instanceof InstanceInvokeExpr)
					{
						Set<Definition> out=OUT.get(u);
						Value base=((InstanceInvokeExpr) iexp).getBase();
						Variable var=VarFromValue(base);
						Iterator<Definition> outir=out.iterator();
						while(outir.hasNext())
						{
							Definition def=outir.next();
							//System.out.println("bb"+def);
							/**
							 * A new definition, not from other previous definition
							 */
							if(def.sameVariable(var))
							{
								def.leaf=true;
								Variable value=Valuetable.get(def);
								value.makeUniverse();
								Valuetable.put(def, value);
								//System.out.println("aa"+Valuetable.get(def));
							}
						}
					}
				}
				//System.out.println(iexp.getMethodRef());

			}
			else if(u instanceof AssignStmt)
			{
				//System.out.println(u);
				//System.out.println("singleIteration_debug_assign");

				AssignStmt ass=(AssignStmt)u;
				Variable target=VarFromValue(ass.getLeftOp());
				Value exp=ass.getRightOp();
				/**
				 * Left Operand is not local variable
				 */
				if(!(ass.getLeftOp() instanceof Local))
				{
					Set<Definition> out=OUT.get(ass);
					Iterator<Definition> outir=out.iterator();
					while(outir.hasNext())
					{
						Definition def=outir.next();
						//System.out.println("bb"+def);
						if(def.sameVariable(target))
						{
							def.leaf=false;
							def.opcode=MyConstant.opcode_assign;
							//System.out.println(def);
							Variable value=Valuetable.get(def);
							value.makeUniverse();
							Valuetable.put(def, value);
							//System.out.println("aa"+Valuetable.get(def));
						}
					}

				}
				/**
				 * Left Operand is local, right operand is local
				 */
				else if(exp instanceof Local)//assign value from local variable to local variable
				{
					Variable source=VarFromValue(exp);
					Set<Definition> out=OUT.get(ass);
					Set<Definition> in=IN.get(ass);
					Iterator<Definition> outir=out.iterator();
					Iterator<Definition> inir=in.iterator();
					target.makeEmpty();

					while(inir.hasNext())
					{
						Definition def=inir.next();
						if(def.sameVariable(source))
						{
							Variable value=Valuetable.get(def);
							target.addvalue(value);

						}
					}
					while(outir.hasNext())
					{
						Definition def=outir.next();
						//System.out.println("bb"+def);
						if(def.sameVariable(target))
						{
							def.leaf=false;
							def.opcode=MyConstant.opcode_assign;
							//System.out.println(def);
							Variable value=Valuetable.get(def);
							value.makeEmpty();
							value.addvalue(target);
							Valuetable.put(def, value);
							//System.out.println("aa"+Valuetable.get(def));
						}
					}
					target.makeEmpty();
					target.addvalue(source);
					//System.out.println(exp);
				}
				else if(exp instanceof  NewExpr)//create a new object
				{
					Set<Definition> out=OUT.get(ass);
					Iterator<Definition> outir=out.iterator();
					while(outir.hasNext())
					{
						Definition def=outir.next();
						if(def.sameVariable(target))
						{
							def.leaf=false;
							def.opcode=MyConstant.opcode_assign;
							//System.out.println(def);
							Variable value=Valuetable.get(def);
							value.makeEmpty();
							Valuetable.put(def, value);
							//System.out.println("aa"+Valuetable.get(def));
						}
					}

				}
				//a group of constant values
				else if(exp instanceof soot.jimple.Constant)
				{
					//System.out.println("const");
					Assign_Conatant_Handler(ass);
				}
				else if(exp instanceof  InvokeExpr)
				{
					Assign_Invoke_handler(ass);
				}
				else if(exp instanceof FieldRef)
				{
					Set<Definition> out=OUT.get(ass);
					Iterator<Definition> outir=out.iterator();
					while(outir.hasNext())
					{
						Definition def=outir.next();
						if(def.sameVariable(target))
						{
							def.leaf=false;
							def.opcode=MyConstant.opcode_assign;
							//System.out.println(def);
							Variable value=Valuetable.get(def);
							value.makeUniverse();
							Valuetable.put(def, value);
							//System.out.println("aa"+Valuetable.get(def));
						}
					}

				}
				else if(exp instanceof ArrayRef)
				{
					Set<Definition> out=OUT.get(ass);
					Iterator<Definition> outir=out.iterator();
					while(outir.hasNext())
					{
						Definition def=outir.next();
						if(def.sameVariable(target))
						{
							def.leaf=false;
							def.opcode=MyConstant.opcode_assign;
							//System.out.println(def);
							Variable value=Valuetable.get(def);
							value.makeUniverse();
							Valuetable.put(def, value);
							//System.out.println("aa"+Valuetable.get(def));
						}
					}
				}
				else //for other expressions such as arithmetic operations
				{
					Set<Definition> out=OUT.get(ass);
					Iterator<Definition> outir=out.iterator();
					while(outir.hasNext())
					{
						Definition def=outir.next();
						if(def.sameVariable(target))
						{
							def.leaf=false;
							def.opcode=MyConstant.opcode_assign;
							//System.out.println(def);
							Variable value=Valuetable.get(def);
							value.makeUniverse();
							Valuetable.put(def, value);
							//System.out.println("aa"+Valuetable.get(def));
						}
					}
				}
				
			}
			else if(u instanceof  JIdentityStmt)
			{
				//parameters and this
				IdentityStmt idt=(IdentityStmt)u;
				//System.out.println("singleIteration_debug_parameter");
				Variable target=VarFromValue(idt.getLeftOp());
				Value right=idt.getRightOp();
				Set<Definition> out=OUT.get(idt);
				Iterator<Definition> outir=out.iterator();
				while(outir.hasNext())
				{
					Definition def=outir.next();
					//System.out.println("bb"+def);
					if(def.sameVariable(target))
					{
						def.leaf=false;
						def.opcode=MyConstant.opcode_assign;
						//System.out.println(def);
						Variable value=Valuetable.get(def);
						value.makeUniverse();
						Valuetable.put(def, value);
						if(right instanceof ParameterRef)
						{
							if(value instanceof StringVariable)
							{
								ParameterRef para=(ParameterRef)right;
								String seperator=""+ExtendedOperation.parameter_seperator;
								String key=seperator+"parameter"+para.getIndex()+seperator;
								value.makeEmpty();
								value.AddConst(key);
								Valuetable.put(def, value);

								//System.out.println(key);
							}
							//System.out.println(right.toString());
							parameters.add(def);
						}
						//System.out.println("aa"+Valuetable.get(def));
					}
				}

				//Variable target=VarFromValue(ass.getLeftOp());
			}
			
		}
	}
	/**
	 * Copy the HashTable, and return as a shadow
	 * @param table
	 * @return
	 */
	private Hashtable<Definition, Variable> GenerateShadow(Hashtable<Definition, Variable> table)
	{
		Hashtable<Definition, Variable> shadow=new Hashtable<Definition, Variable>();
		for(Definition key:table.keySet())
		{
			Variable v=table.get(key);
			shadow.put(key.clone(), v.clone());
		}
		return shadow;
	}
	private boolean Different(Hashtable<Definition, Variable> oldtable, Hashtable<Definition, Variable> newtable)
	{
		if(!oldtable.keySet().equals(newtable.keySet()))
			return true;
		Set<Definition> keyset=oldtable.keySet();
		for(Definition key:keyset)
		{
			Variable v1=oldtable.get(key);
			Variable v2=newtable.get(key);
			System.out.println(key);
			System.out.println();
			if(!v1.SameValue(v2))
			{
				return true;
			}
		}
		return false;
	}
	private void WidenAll(Hashtable<Definition, Variable> oldtable, Hashtable<Definition, Variable> newtable, int iteration)
	{
		if(iteration > 11)
			return;
		if(!oldtable.keySet().equals(newtable.keySet()))
			throw new Error("key set do not meet");
		Set<Definition> keyset=oldtable.keySet();
		if(iteration <=lowthreshold)
			return;
			
		for(Definition key:keyset)
		{
			Variable v1=oldtable.get(key);
			Variable v2=newtable.get(key);
			if(v1.SameValue(v2))
				continue;
			if((v2 instanceof BooleanVariable))
			{
				v2.AddConst(MyConstant.UNKNOWN_INT);
				newtable.put(key, v2);
			}
			else if((v2 instanceof IntegerVariable))
			{
				v2.AddConst(MyConstant.UNKNOWN_INT);
				newtable.put(key, v2);

			}
			else if((v2 instanceof DoubleVariable))
			{
				v2.AddConst(MyConstant.UNKNOWN_DOUBLE);
				newtable.put(key, v2);

			}
			else if((v2 instanceof StringVariable))
			{
				//System.out.println(v2);

				if(iteration <highthreshold)
				{
					
					((StringVariable) v2).WidenWith((StringVariable)v1);
					//System.out.println(v2);
					newtable.put(key, v2);
				}
				else if(iteration >=highthreshold)
				{
					((StringVariable) v2).MakeAllString();
					newtable.put(key, v2);
				}
			}
				
		}
	}
	public void depends()
	{
		Iterator<Unit> ir=cfg.iterator();
		while(ir.hasNext())
		{
			Unit u=ir.next();
			Set<Definition> in_set=IN.get(u);
			Iterator<ValueBox> def=u.getDefBoxes().iterator();
			Iterator<ValueBox> useir=u.getUseBoxes().iterator();
			Relience r=new Relience();
			r.u=u;
			while(useir.hasNext())
			{
				Value use=useir.next().getValue();
				Variable v=VarFromValue(use);
				//System.out.println("bb"+def);
				for(Definition d:in_set)
				{
					if(d.sameVariable(v))
					{
						r.rely.add(d);
					}
						
				}
			}
			//Set<Definition> out_set=new HashSet<Definition>();
		}
			
	}
	public MethodSummary(SootMethod method)
	{
		cfg=new BriefUnitGraph(method.retrieveActiveBody());
		dataflow();
		this.sootmethod=method;
		initvaluetable();
		//display();
		Hashtable<Definition, Variable> oldValue=GenerateShadow(Valuetable);
		boolean flag=true;
		int iteration =0;
		while(flag)
		{
			flag=false;
			singleIteration();
			//System.out.println("MethodSummary_second"+method.getName());

			//System.out.println(Different(oldValue,Valuetable));
			/*if(Different(oldValue,Valuetable))
			{
				System.out.println("different, widenning");
				flag=true;
				WidenAll(oldValue,Valuetable,iteration);
			}*/
			//System.out.println(iteration);
			iteration++;
			//System.out.println(method.getDeclaringClass().getName()+"."+method.getName()+" MethodSummary line 1197 iteration:"+iteration);
			if(iteration<1)//use for simple , pending on new implementation
				flag=true;
			// A random value that makes it terminate even if it don't converge
			if(iteration>15)
				throw new Error("not converge error");
			// A legacy code for the annotated part above
			oldValue=GenerateShadow(Valuetable);

		}
		
	}

			
}
