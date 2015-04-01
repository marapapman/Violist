package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisAssignedOnAliasForThis {
	private String field;
	
	public void foo(FieldThisAssignedOnAliasForThis obj) {
		obj.field = "baz";
		this.field = "abc";
		obj.field = "foo";
		String s = bar();
			Logger.reportString(s,"FieldThisAssignedOnAliasForThis");
	}
	private String bar() {
		return "Q" + field;
	}
	
	public static void main(String[] args) {
		new FieldThisAssignedOnAliasForThis().foo(new FieldThisAssignedOnAliasForThis());
		
		FieldThisAssignedOnAliasForThis o = new FieldThisAssignedOnAliasForThis();
		o.foo(o);
	}
}
