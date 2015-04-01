package testcases.standalone;

import LoggerLib.Logger;
public class FieldToFieldAssignment {
	private String a;
	private String b;
	
	public void foo() {
		String s = "abc";
		a = "x";
		b = "y";
		a = b;
		s = a;
			Logger.reportString(s,"FieldToFieldAssignment");
	}
	
	public static void main(String[] args) {
		new FieldToFieldAssignment().foo();
	}
}
