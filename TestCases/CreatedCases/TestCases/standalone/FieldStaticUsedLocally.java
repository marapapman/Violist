package testcases.standalone;

import LoggerLib.Logger;
public class FieldStaticUsedLocally {
	private static String field;
	
	private static String bar() {
		field = "abc";
		return field;
	}
	private static String baz() {
		field = "xyz";
		return field;
	}
	
	public static void foo() {
		String s = bar() + baz();
			Logger.reportString(s,"FieldStaticUsedLocally");
	}
	
	public static void main(String[] args) {
		foo();
	}
}
