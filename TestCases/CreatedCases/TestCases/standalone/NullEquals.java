package testcases.standalone;

import LoggerLib.Logger;
public class NullEquals {
	private static void foo() {
		String s = null;
		if (s.equals("1")) { // intentional null pointer exceptions
			Logger.reportString(s,"NullEquals");
		}
	}
	public static void main(String[] args) {
		foo();
	}
}
