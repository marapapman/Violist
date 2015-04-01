package testcases.standalone;

import LoggerLib.Logger;
/**
 * Replace char sequences, where the search string occurs in the output.
 * This can happen because the replacement is greedy, and the characters
 * created by the replacement string cannot be used to match the search string.
 */
public class ReplaceSearchOccursInOutput {
	public void foo() {
		String s = "ababc";
		s = s.replace("abc", "c");
			Logger.reportString(s,"ReplaceSearchOccursInOutput");
	}
	public static void main(String[] args) {
		new ReplaceSearchOccursInOutput().foo();
	}
}
