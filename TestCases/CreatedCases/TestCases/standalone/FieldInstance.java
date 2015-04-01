package testcases.standalone;

import LoggerLib.Logger;
public class FieldInstance {
	private String field;
	
	// an alias analysis at jimple-level would be required to get this green
	
	public static void foo() {
		FieldInstance a = new FieldInstance();
		FieldInstance b = new FieldInstance();
		a.field = "a";
		b.field = "b";
			Logger.reportString(a.field,"FieldInstance");
	}
	
	public static void main(String[] args) {
		foo();
	}
}
