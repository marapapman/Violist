package testcases.standalone;

import LoggerLib.Logger;
public class FieldStaticChangedByMethodCall {
	private static String field;
	
	public void foo() { 
		field = "abc";
		bar();
			Logger.reportString(field,"FieldStaticChangedByMethodCall");
	}
	
	private void bar() {
		field = "xyz";
	}
	
	public static void main(String[] args) {
		new FieldStaticChangedByMethodCall().foo();
	}
}
