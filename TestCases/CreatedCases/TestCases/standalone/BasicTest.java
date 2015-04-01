package testcases.standalone;

import LoggerLib.Logger;
public class BasicTest {
	
	public void foo() {
		String s = "abc";
			Logger.reportString(s,"BasicTest");
	}
	
}