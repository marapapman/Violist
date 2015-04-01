package testcases.standalone;

import LoggerLib.Logger;
public class SwitchInt {
	private static void foo(int i) {
		String s = "";
		switch (i) {
		case '<':
		case '>':
		case '&':
			s += (char)i;
			break;
		case Character.MAX_VALUE+1:
			s += (char)i; // i gets truncated to char 0
			break;
		default:
			s += "?";
		}
			Logger.reportString(s,"SwitchInt");
	}
	public static void main(String[] args) {
		foo('a');
		foo('b');
		foo('c');
		foo('x');
		foo('y');
		foo('z');
		foo('_');
		foo('<');
		foo('>');
		foo('&');
	}
}
