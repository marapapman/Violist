package testcases.standalone;

import LoggerLib.Logger;
public class FieldStaticDefinedInOtherFunction {
	private static String field;
	
	public static void foo() {
		String s = "xyz";
		bar();
		s += field;
			Logger.reportString(s,"FieldStaticDefinedInOtherFunction");
	}
	
	private static void bar() {
		field = "abc";
	}
	
	public static void main(String[] args) {
		foo();
	}
}
