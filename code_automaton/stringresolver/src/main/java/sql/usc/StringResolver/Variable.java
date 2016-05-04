package sql.usc.StringResolver;

import java.util.Set;

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
import soot.jimple.ArrayRef;
import sql.usc.StringResolver.Variables.ArrayVariable;
import sql.usc.StringResolver.Variables.BooleanVariable;
import sql.usc.StringResolver.Variables.DoubleVariable;
import sql.usc.StringResolver.Variables.IntegerVariable;
import sql.usc.StringResolver.Variables.ObjectVariable;
import sql.usc.StringResolver.Variables.StringVariable;

public interface  Variable {
	public void setName(String name);
	public String getName();
	public void addvalue(Variable a);
	public int hashCode();
	public String toString();
	public boolean equals(Object obj);
	public Automaton StringValue();
	public Set getvalue();
	public void cleanvalue();
	public void AddConst(Object o);
	public Variable clone();
	public boolean SameValue(Variable v);
	public void makeEmpty();
	public void makeUniverse();
	
}