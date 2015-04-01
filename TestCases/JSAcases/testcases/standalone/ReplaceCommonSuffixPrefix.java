package testcases.standalone;

import LoggerLib.Logger;
/**
 * Replace char sequences, where the search string has a suffix equal to a prefix of the replacement.
 */
public class ReplaceCommonSuffixPrefix {
	public void foo() {
		String s = "abcbc";
		s = s.replace("bc", "cX");
			Logger.reportString(s,"ReplaceCommonSuffixPrefix");
	}
	public static void main(String[] args) {
		new ReplaceCommonSuffixPrefix().foo();
	}
}
