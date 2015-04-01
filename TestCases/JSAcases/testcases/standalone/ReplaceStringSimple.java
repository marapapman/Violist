package testcases.standalone;

import LoggerLib.Logger;
public class ReplaceStringSimple {
	public void foo() {
		String s = "abc".replace("abc", "xyz");
			Logger.reportString(s,"ReplaceStringSimple");
	}
	public static void main(String[] args) {
		new ReplaceStringSimple().foo();
	}
}
