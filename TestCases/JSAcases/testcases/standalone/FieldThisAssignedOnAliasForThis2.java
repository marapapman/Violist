package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisAssignedOnAliasForThis2 {
	private String field;
	
	public void foo(FieldThisAssignedOnAliasForThis2 obj) {
		obj.field = "baz";
		this.field = "abc";
		obj.field = "foo";
		bar(obj);
			Logger.reportString(field,"FieldThisAssignedOnAliasForThis2");
	}
	private void bar(FieldThisAssignedOnAliasForThis2 obj) {
		obj.field = "X";
	}
	
	public static void main(String[] args) {
		new FieldThisAssignedOnAliasForThis2().foo(new FieldThisAssignedOnAliasForThis2());
		
		FieldThisAssignedOnAliasForThis2 o = new FieldThisAssignedOnAliasForThis2();
		o.foo(o);
	}
}
