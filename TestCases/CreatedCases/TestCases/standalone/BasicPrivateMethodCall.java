package testcases.standalone;

import LoggerLib.Logger;
public class BasicPrivateMethodCall {
	private String constant() {
		return "abc";
	}
	public void foo() {
		// this test is less silly than it seems
		// private methods are invoked with "invokespecial"
		String s = constant();
			Logger.reportString(s,"BasicPrivateMethodCall");
	}
}
