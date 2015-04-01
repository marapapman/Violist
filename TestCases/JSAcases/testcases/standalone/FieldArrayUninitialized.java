package testcases.standalone;

import LoggerLib.Logger;
public class FieldArrayUninitialized {
	private String[] array;
	
	public void foo() {
		String s = "x";
		if (array != null) {
			s = array[0];
		}
			Logger.reportString(s,"FieldArrayUninitialized");
	}
	
	public static void main(String[] args) {
		new FieldArrayUninitialized().foo();
	}
}
