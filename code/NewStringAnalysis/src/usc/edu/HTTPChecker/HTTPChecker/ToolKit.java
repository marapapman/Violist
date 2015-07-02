package usc.edu.HTTPChecker.HTTPChecker;

import soot.Unit;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.Stmt;

public class ToolKit {
	public static boolean isReturn(Unit stmt)
	{
		return (stmt instanceof ReturnStmt) || (stmt instanceof ReturnVoidStmt);
	}
public static boolean isInvocation(Unit stmt)
{
	if(stmt instanceof InvokeStmt)
	{
		return true;
	}
	else if(stmt instanceof AssignStmt )
	{
		AssignStmt assign=(AssignStmt)stmt;
		if(assign.containsInvokeExpr())
		{
			return true;
		}
		else{
			return false;
		}
	}
	return false;
}
public static String getInvokeSignature(Unit stmt)
{
	String signature="";
	if(stmt instanceof InvokeStmt)
	{
		InvokeStmt invoke=(InvokeStmt)stmt;
		InvokeExpr exp=invoke.getInvokeExpr();
		 signature=exp.getMethod().getSignature();
		//System.out.println(signature);
		 return signature;


	}
	else if(stmt instanceof AssignStmt )
	{
		AssignStmt assign=(AssignStmt)stmt;
		if(assign.containsInvokeExpr())
		{
			InvokeExpr exp=assign.getInvokeExpr();
			 signature=exp.getMethod().getSignature();
			 return signature;
		}
	}
	return null;
}
public static boolean isHttpOpen(Unit stmt){

	// there are many kinds of statements, here we are only
	// interested in statements containing InvokeStatic
	// NOTE: there are two kinds of statements may contain
	// invoke expression: InvokeStmt, and AssignStmt
	String signature="";
	if(stmt instanceof InvokeStmt)
	{
		InvokeStmt invoke=(InvokeStmt)stmt;
		InvokeExpr exp=invoke.getInvokeExpr();
		 signature=exp.getMethod().getSignature();
		//System.out.println(signature);


	}
	else if(stmt instanceof AssignStmt )
	{
		AssignStmt assign=(AssignStmt)stmt;
		if(assign.containsInvokeExpr())
		{
			InvokeExpr exp=assign.getInvokeExpr();
			 signature=exp.getMethod().getSignature();
		}
		else{
			return false;
		}
	}
	else{
		return false;
	}
	if(signature.startsWith("<org.apache.http.client.HttpClient: org.apache.http.HttpResponse execute") || signature.startsWith("<java.net.URL: java.net.URLConnection openConnection"))
	{
		return true;
	}

	return false;
}
}
