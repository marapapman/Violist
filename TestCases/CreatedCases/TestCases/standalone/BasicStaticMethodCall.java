package testcases.standalone;

import LoggerLib.Logger;
public class BasicStaticMethodCall {
	public static String constant() {
		return "abc";
	}
	public void foo() {
		String s = constant();
			Logger.reportString(s,"BasicStaticMethodCall");
	}
	public static void main(String[] args) {
		new BasicStaticMethodCall().foo();
	}
}
