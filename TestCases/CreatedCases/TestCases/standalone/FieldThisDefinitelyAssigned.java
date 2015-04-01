package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisDefinitelyAssigned {
	private String field = "bar";
	
	public void foo() {
		this.field = "abc";
		this.field = "xyz";
			Logger.reportString(this.field,"FieldThisDefinitelyAssigned");
	}
	
	public static void main(String[] args) {
		new FieldThisDefinitelyAssigned().foo();
	}
}
