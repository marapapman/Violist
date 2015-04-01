package testcases.standalone;

import LoggerLib.Logger;
/**
 * Attempt to defeat a possible solution to the CannotThrowException test.
 * Array assignments may throw ArrayStoreException.
 */
public class ControlFlowCovariantArraysThrowsException {
	public void foo() {
		String[] array = new String[5];
		Object[] array2 = array;
		String s = "xyz";
		try {
			array2[0] = new StringBuilder("abc");
			s = "abc";
		} catch (ArrayStoreException ex) {
		}
			Logger.reportString(s,"ControlFlowCovariantArraysThrowsException");
	}
	public static void main(String[] args) {
		new ControlFlowCovariantArraysThrowsException().foo();
	}
}
