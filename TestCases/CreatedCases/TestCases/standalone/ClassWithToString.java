package testcases.standalone;

import LoggerLib.Logger;
public class ClassWithToString {
	public static void foo(ClassWithToString str) {
		String s = "a" + str;
			Logger.reportString(s,"ClassWithToString");
	}
	
	@Override
	public final String toString() {
		return "xx";
	}
}
