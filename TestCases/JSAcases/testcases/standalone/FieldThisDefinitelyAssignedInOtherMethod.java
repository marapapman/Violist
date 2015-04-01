package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisDefinitelyAssignedInOtherMethod {
	private String field;
	
	public void foo() {
		bar();
			Logger.reportString(field,"FieldThisDefinitelyAssignedInOtherMethod");
	}
	private void bar() {
		field = "bar";
	}
	
	public static void main(String[] args) {
		new FieldThisDefinitelyAssignedInOtherMethod().foo();
	}
}
