package testcases.standalone;

import LoggerLib.Logger;
public class IfAliasImpossibleTypes {
	private static void foo() {
		// the purpose of this test is to see that the analysis does not 
		// crash or do something stupid when incompatible types are compared
		Object a = new StringBuffer();
		Object b = new String[10];
		((String[])b)[0] = "A";
		if (a == b) {
			// supposedly, the analysis might think 'a' and 'b' are aliases now,
			// and let this operation affect 'b', even though they could never be aliases
			((StringBuffer)a).append("X");
		}
			Logger.reportString(((String[])b)[0],"IfAliasImpossibleTypes");
	}
	public static void main(String[] args) {
		foo();
	}
}
