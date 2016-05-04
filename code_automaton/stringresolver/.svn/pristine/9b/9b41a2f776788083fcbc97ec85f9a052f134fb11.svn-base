package sql.usc.StringResolver;

import soot.Local;
import soot.Value;
import soot.jimple.AssignStmt;
import soot.jimple.Stmt;

public class StmtHandler {	
	public static boolean AssignToLocal(AssignStmt s)
	{
		Value left=s.getLeftOp();
		if(left instanceof Local)
			return true;
		return false;
		
	}
	public static Local getLocal(AssignStmt s)
	{
		Value left=s.getLeftOp();
		if(left instanceof Local)
			return (Local)left;
		return null;
	}
}
