package usc.sql.ir;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

abstract public class Variable implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Variable parent = null;
	//private List<Variable> children = new ArrayList<>();
	
	public void setParent(Variable parent) {this.parent = parent;}
	public Variable getParent(){return parent;}
	//public List<Variable> getChildren(){return children;}
	public abstract String getValue();
	public abstract String toString();
}
