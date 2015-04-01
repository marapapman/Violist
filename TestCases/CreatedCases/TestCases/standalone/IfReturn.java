package testcases.standalone;

import LoggerLib.Logger;
public class IfReturn {
	
	public String foo(boolean b) {
		String s = "x";
		if (b) {
			s = "y";
			return s;
		}
			Logger.reportString(s,"IfReturn");
		return s;
	}
	
}
