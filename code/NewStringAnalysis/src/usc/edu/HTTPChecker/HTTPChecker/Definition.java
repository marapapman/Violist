package usc.edu.HTTPChecker.HTTPChecker;

import soot.Unit;
import soot.ValueBox;

public class Definition {
	public ValueBox vb;
	public int offset;
	public  Unit stmt;

	public Definition(ValueBox v, int o, Unit t)
	{
		this.vb=v;
		this.offset=o;
		this.stmt=t;
	}
	public String toString(){
		return this.vb.toString()+"@"+this.offset;
	}
	public boolean equals(Object o)
	{
		if(!(o instanceof Definition))
			return false;
		Definition input = (Definition)o;
		if(input.vb.toString().equals(this.vb.toString())&&input.offset==this.offset)
			return true;
		else{
			return false;
		}
	}
	public int hashCode() {

			return vb.toString().hashCode()+this.offset;
	    }
}
