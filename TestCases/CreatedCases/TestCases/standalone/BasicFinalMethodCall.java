package testcases.standalone;

import LoggerLib.Logger;
public class BasicFinalMethodCall {
	public final String constant() {
		return "abc";
	}
	public void foo() {
		String s = constant();
			Logger.reportString(s,"BasicFinalMethodCall");
	}
}
