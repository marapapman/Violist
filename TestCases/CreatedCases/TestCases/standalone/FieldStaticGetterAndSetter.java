package testcases.standalone;

import LoggerLib.Logger;
public class FieldStaticGetterAndSetter {
	private static String field;
	
	// the getters and setters have been copied to simulate optimal context sensitivity,
	// since we want to set fields isolated from the other aspects of the analysis
	private static String getField1() {
		return field;
	}
	private static String getField2() {
		return field;
	}
	private static void setField1(String x) {
		field = x;
	}
	private static void setField2(String x) {
		field = x;
	}
	
	public static void main(String[] args) {
		setField1("abc");
		String s = getField1();
		setField2("xyz");
		s += " " + getField2();
			Logger.reportString(s,"FieldStaticGetterAndSetter");
	}
}
