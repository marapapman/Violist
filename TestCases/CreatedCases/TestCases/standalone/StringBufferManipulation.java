package testcases.standalone;

import LoggerLib.Logger;
public class StringBufferManipulation {
	public void foo() {
		StringBuffer b = new StringBuffer("Abc");
		b.append(false);	// Abcfalse
		b.insert(0, "x");	// xAbcfalse
		b.insert(1, "y");	// xyAbcfalse
		b.deleteCharAt(2);  // xybcfalse
		String s = b.substring(3) + b.substring(0, 1) + b.substring(3, 3);	// cfalsex
			Logger.reportString(s,"StringBufferManipulation");
	}
	
	public static void main(String[] args) {
		new StringBufferManipulation().foo();
	}
}
