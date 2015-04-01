package testcases.standalone;

import LoggerLib.Logger;
public class FieldUninitialized {
	private String foo;
	
	public void foo() {
		String s = "" + foo;
			Logger.reportString(s,"FieldUninitialized");
	}
	
	public static void main(String[] args) {
		new FieldUninitialized().foo();
	}
}
