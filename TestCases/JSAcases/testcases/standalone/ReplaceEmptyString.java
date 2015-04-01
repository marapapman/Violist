package testcases.standalone;

import LoggerLib.Logger;
public class ReplaceEmptyString {
	public void foo() {
		String s = "abc";
		s = s.replace("", "X");
			Logger.reportString(s,"ReplaceEmptyString");
	}
	public static void main(String[] args) {
		new ReplaceEmptyString().foo();
	}
}
