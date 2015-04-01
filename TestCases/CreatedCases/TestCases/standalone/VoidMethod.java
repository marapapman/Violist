package testcases.standalone;

import LoggerLib.Logger;
public class VoidMethod {
	public void foo() {
		String s = "abc";
		baz();
			Logger.reportString(s,"VoidMethod");
	}
	public void baz() {
		
	}
}
