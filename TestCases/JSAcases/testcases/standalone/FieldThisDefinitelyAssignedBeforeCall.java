package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisDefinitelyAssignedBeforeCall {
	private String field;
	
	public void foo() {
		this.field = "abc";
		String s = bar();
		this.field = "xyz";
			Logger.reportString(s,"FieldThisDefinitelyAssignedBeforeCall");
	}
	private String bar() {
		return "Q" + this.field;
	}
	
	public static void main(String[] args) {
		new FieldThisDefinitelyAssignedBeforeCall().foo();
	}
}
