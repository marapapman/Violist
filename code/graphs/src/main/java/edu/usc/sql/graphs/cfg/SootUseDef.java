package edu.usc.sql.graphs.cfg;

import java.util.ArrayList;
import java.util.List;

import edu.usc.sql.graphs.Node;
import soot.Unit;
import soot.Value;
import soot.ValueBox;

public class SootUseDef {
	
	//This method returns a List of Soot objects ValueBox which have the definitions of different 'r' Jimple statements present in node n
	//Extract and use
	public ArrayList<Value> getDefinition(Node n)
	{
		Unit u;
		u = (Unit) n.getActualNode();
		 
		
		 ArrayList<Value> ValList = new ArrayList<Value>();
		 
		 if(u!=null)
		 {
			// System.out.println("\n Node Details: "+u.toString());
			 
			 //System.out.println("Defintion Box");
			 List<ValueBox> DBList = u.getDefBoxes();
			 
			 
			 
			 for (ValueBox DBListIter : DBList) {
					
				// System.out.println("Def Value Box:"+DBListIter.toString());
				// System.out.println("Value:"+DBListIter.getValue());
				 
				 ValList.add(DBListIter.getValue());
				 
				 
			}
			 
			 
		 }

		 return ValList;
		 
	}
	
	//This method returns a List of Soot objects ValueBox which have the usages of different 'r' Jimple statements present in node n
	//Extract and use
	public ArrayList<Value> getUsages(Node n)
	{
		Unit u = (Unit) n.getActualNode();
		ArrayList<Value> ValList = new ArrayList<Value>();
		
		if(u!=null)
		{
			// System.out.println("\nNode Details:"+u.toString());
				
				//System.out.println("Use Box");
				 List<ValueBox> UBList = u.getUseBoxes();
				
				for (ValueBox UBListIter : UBList) {
						
					 ValList.add(UBListIter.getValue());
					 
					 
				}
		}
		
		
		 
		 
		 return ValList;
		 
	}
	
	
	
}
