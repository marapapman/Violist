package testcases.standalone;

import LoggerLib.Logger;
public class SwitchChar2 {
	private static void foo(char c) {
		String s = "";
		switch (c) {
		case '<':
		case '>':
		case '&':
			s += c;
			break;
		default:
			s += "?";
		}
			Logger.reportString(s,"SwitchChar2");
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
