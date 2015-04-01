package testcases.standalone;

import LoggerLib.Logger;
public class IfCharacterIsDigit {
	private static void foo(char ch) {
		if (Character.isDigit(ch)) {
			Logger.reportString(""+ch,"IfCharacterIsDigit");
		}
	}
	public static void main(String[] args) {
		foo('0');
		foo('1');
		foo('2');
		foo('3');
		foo('4');
		foo('5');
		foo('6');
		foo('7');
		foo('8');
		foo('9');
		foo('e');
		foo('D');
		foo('F');
		foo('a');
		foo('b');
		foo('K');
		foo('A');
		foo('M');
	}
}
