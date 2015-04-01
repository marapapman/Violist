package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisDefinitelyAssignedOnOtherObject {
	private String field;
	
	// it would not be hard to expand the this-pointer analysis to
	// find variables that are definitely not 'this', but the small precision
	// gain would probably not be worth it.
	
	public void foo() {
		this.field = "abc";
		new FieldThisDefinitelyAssignedOnOtherObject().field = "bar";
		String s = bar();
		this.field = "xyz";
			Logger.reportString(s,"FieldThisDefinitelyAssignedOnOtherObject");
	}
	private String bar() {
		return "Q" + this.field;
	}
	
	public static void main(String[] args) {
		new FieldThisDefinitelyAssignedOnOtherObject().foo();
	}
}
