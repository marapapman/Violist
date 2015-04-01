package testcases.standalone;

import LoggerLib.Logger;
public class StringOperations {
	public static void foo() {
		String s = "\tAbc ";
		s = s.toUpperCase() + ";" + s.toLowerCase() + ";" + s.trim();
			Logger.reportString(s,"StringOperations");
	}
	public static void main(String[] args) {
		foo();
	}
}
