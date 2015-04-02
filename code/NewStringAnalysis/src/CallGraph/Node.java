package CallGraph;

import java.util.HashSet;
import java.util.Set;

import soot.SootMethod;

public class Node {
	private SootMethod method;
	private Set<Node> children;
	private Set<Node> parent;
	private int order=-1;
	public Node(SootMethod m)
	{
		this.method=m;
		this.children=new HashSet<Node>();
		this.parent=new HashSet<Node>();

	}
	public boolean  OrderAssigned(){
		return order!=-1;
	}
	public void SetOrder(int o){
		this.order=o;
	}
	public int GetOrder(){
		return this.order;
	}
	public SootMethod getMethod()
	{
		return this.method;
	}
	public Set<Node> getChildren()
	{
		return this.children;
	}
	public Set<Node> getParent()
	{
		return this.parent;
	}
	public void addChild(Node c ){
		this.children.add(c);
	}
	public void addParent(Node p ){
		this.parent.add(p);
	}
	public int getIndgree(){
		return this.parent.size();
	}
	public int getOurdgree(){
		return this.children.size();
	}
	public String toString(){
		return method.getSignature();
	}
}
