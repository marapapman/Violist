package sql.usc.StringResolver;

import java.util.Hashtable;

import soot.SootMethod;

public class StringDatabase {
	private Hashtable<String ,MethodSummary > stringdatabase=new  Hashtable<String ,MethodSummary >();
	private static String GetKey(SootMethod m)
	{
		return m.getSignature();
	}
	public void put(SootMethod sm, MethodSummary sum)
	{
		stringdatabase.put(GetKey(sm), sum);
	}
	public MethodSummary query(SootMethod sm)
	{
		return stringdatabase.get(GetKey(sm));
	}
}
