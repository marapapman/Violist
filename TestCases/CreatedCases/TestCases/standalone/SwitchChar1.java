package testcases.standalone;

import LoggerLib.Logger;
public class SwitchChar1 {
	private static void foo(char c) {
		String s = "";
		switch (c) {
		case '<':
		case '>':
		case '&':
			s += '?';
			break;
		default:
			s += c;
		}
			Logger.reportString(s,"SwitchChar1");
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
