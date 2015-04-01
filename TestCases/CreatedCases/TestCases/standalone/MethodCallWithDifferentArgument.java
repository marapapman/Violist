package testcases.standalone;

import LoggerLib.Logger;
/**
 * Test how the analysis handles the identity function.
 */
public class MethodCallWithDifferentArgument {
	public void foo() {
		String s = bar("abc") + bar("xyz");
			Logger.reportString(s,"MethodCallWithDifferentArgument");
	}
	private String bar(String s) {
		return s;
	}
	
	public static void main(String[] args) {
		new MethodCallWithDifferentArgument().foo();
	}
}
