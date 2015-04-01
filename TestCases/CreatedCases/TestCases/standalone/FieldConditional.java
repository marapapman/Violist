package testcases.standalone;

import LoggerLib.Logger;
public class FieldConditional {
	private boolean b;
	
	public void foo() {
		b = false;
		String s = "ab";
		if (b) {
			s += "c";
		} else {
			s += "d";
		}
			Logger.reportString(s,"FieldConditional");
	}
}