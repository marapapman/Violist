package testcases.standalone;

import LoggerLib.Logger;
/**
 * Replace char sequences, where the search replacement is a substring of the search string.
 */
public class ReplaceSuperstringSubstring {
	public void foo() {
		String s = "abcbcabcb";
		s = s.replace("abc", "b");
			Logger.reportString(s,"ReplaceSuperstringSubstring");
	}
	public static void main(String[] args) {
		new ReplaceSuperstringSubstring().foo();
	}
}
