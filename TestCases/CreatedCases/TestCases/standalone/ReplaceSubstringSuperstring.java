package testcases.standalone;

import LoggerLib.Logger;
/**
 * Replace char sequences, where the search string is a substring of the replacement.
 */
public class ReplaceSubstringSuperstring {
	public void foo() {
		String s = "abcbc";
		s = s.replace("bc", "XbcY");
			Logger.reportString(s,"ReplaceSubstringSuperstring");
	}
	public static void main(String[] args) {
		new ReplaceSubstringSuperstring().foo();
	}
}
