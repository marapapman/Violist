package testcases.standalone;

import LoggerLib.Logger;
public class StringBufferNull {
	public void foo(boolean bool) {
		StringBuffer b;
		if (bool) {
			b = null;
		} else {
			b = new StringBuffer();
		}
		String s = "abc" + b;
			Logger.reportString(s,"StringBufferNull");
	}
	public static void main(String[] args) {
		new StringBufferNull().foo(true);
		new StringBufferNull().foo(false);
	}
}
