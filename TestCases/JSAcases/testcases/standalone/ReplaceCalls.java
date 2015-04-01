package testcases.standalone;

import LoggerLib.Logger;
public class ReplaceCalls {
	public void foo() {
		String s = "abcbc";
		s = s.replace("bc", "Xb") + " " + s.replace('b', 'Q');
			Logger.reportString(s,"ReplaceCalls");
	}
	public static void main(String[] args) {
		new ReplaceCalls().foo();
	}
}
