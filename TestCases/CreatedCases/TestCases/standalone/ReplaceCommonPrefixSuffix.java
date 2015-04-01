package testcases.standalone;

import LoggerLib.Logger;
/**
 * Replace char sequences, where the search string has a prefix equal to a suffix of the replacement.
 */
public class ReplaceCommonPrefixSuffix {
	public void foo() {
		String s = "abcbc";
		s = s.replace("bc", "Xb");
			Logger.reportString(s,"ReplaceCommonPrefixSuffix");
	}
	public static void main(String[] args) {
		new ReplaceCommonPrefixSuffix().foo();
	}
}
