package sql.usc.StringResolver;

import java.util.Hashtable;

public class LocalDefinitionTable { 
	private Hashtable<Variable,Hashtable<Integer,Definition>> table;
	public void put(Variable v, int offset, Definition d)
	{
		Hashtable<Integer,Definition> tmp=table.get(v);
		if(tmp==null)
			tmp=new Hashtable<Integer,Definition>();
		tmp.put(offset, d);
		table.put(v, tmp);
	}
	public Definition get(String name, int offset)
	{
		Hashtable<Integer,Definition> tmp=table.get(name);
		if(tmp==null)
			return null;
		return tmp.get(offset);
	}
}
