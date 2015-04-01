package testcases.standalone;

import LoggerLib.Logger;
/**
 * Taking a substring of a string that might be null.
 * This test attempts to expose the disadvantage of treating the pointer null 
 * and the string "null" as the same.
 */
public class SubstringOfNull {
	public void foo(boolean b, boolean c) {
		String s = "abc";
		if (b) {
			s = null;
		}
		if (c) {
			s = s.substring(1);
		}
			Logger.reportString(String.valueOf(s),"SubstringOfNull");
	}
	public static void main(String[] args) {
		new SubstringOfNull().foo(false, true);
		new SubstringOfNull().foo(false, false);
		new SubstringOfNull().foo(true, false);
	}
}
