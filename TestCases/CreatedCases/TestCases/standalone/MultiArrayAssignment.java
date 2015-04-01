package testcases.standalone;

import LoggerLib.Logger;
public class MultiArrayAssignment {
	public void foo() {
		String[][] str = new String[4][4];
		
		str[0][1] = "foo ";
		str[0][2] = "bar ";
		str[1][3] = "baz ";
		
		String s = str[0][1] + str[1][3] + str[0][2] + str[1][2];
		
			Logger.reportString(s,"MultiArrayAssignment");
	}
}
