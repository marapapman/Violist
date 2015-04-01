package testcases.standalone;

import LoggerLib.Logger;
public class BasicMethodCall {
	public String constant() {
		return "abc";
	}
	public void foo() {
		String s = constant();
			Logger.reportString(s,"BasicMethodCall");
	}
	public static void main(String[] args) {
		new BasicMethodCall().foo();
	}
}
