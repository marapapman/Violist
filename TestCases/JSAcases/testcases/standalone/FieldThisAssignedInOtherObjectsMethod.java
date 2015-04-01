package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisAssignedInOtherObjectsMethod {
	private String field;
	private void foo(FieldThisAssignedInOtherObjectsMethod o) {
		bar();
		o.baz();
			Logger.reportString(field,"FieldThisAssignedInOtherObjectsMethod");
	}
	
	private void bar() {
		field = "bar";
	}
	private void baz() {
		field = "baz";
	}
	
	public static void main(String[] args) {
		FieldThisAssignedInOtherObjectsMethod o = new FieldThisAssignedInOtherObjectsMethod();
		o.foo(o);
		
		o = new FieldThisAssignedInOtherObjectsMethod();
		o.foo(new FieldThisAssignedInOtherObjectsMethod());
	}
	
}
