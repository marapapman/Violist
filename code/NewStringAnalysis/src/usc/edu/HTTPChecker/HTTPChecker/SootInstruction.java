package usc.edu.HTTPChecker.HTTPChecker;

import soot.SootMethod;
import soot.Unit;

public class SootInstruction {
SootMethod sm;
Unit u;
int offset=-1;
	public SootInstruction(SootMethod m, Unit iu, int s){
		this.sm=m;
		this.u=iu;
		this.offset=s;
	
	}
	public String toString(){
		String r=u.toString()+"@"+offset+"@"+sm.getSignature();
		return r;
	}
	public int hashCode(){
		return sm.hashCode()*10+u.hashCode();
	}
	public boolean equals(Object o){
		if(!(o instanceof SootInstruction))
			return false;
		SootInstruction come=(SootInstruction)o;
		return this.sm.equals(come.sm) && this.u.equals(come.u) && this.offset==come.offset;
		
	}
}
