package testcases.standalone;

import LoggerLib.Logger;
public class NullString {
	public void foo() {
		String n = null;
		String s = "x" + n;
			Logger.reportString(s,"NullString");
	}
	public static void foo(String[] args) {
		new NullString().foo();
	}
}
