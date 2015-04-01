package testcases.standalone;

import LoggerLib.Logger;
public class FieldInConstructor {
	private String s;
	
	public FieldInConstructor() {
		// in the constructor we know that nobody else has a reference to 'this'.
		// (at least when Object is the supertype, and there are no instance initializers)
		s = "abc";
			Logger.reportString(s,"FieldInConstructor");
	}
}
