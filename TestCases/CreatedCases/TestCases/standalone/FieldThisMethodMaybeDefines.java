package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisMethodMaybeDefines {
	private String field;
	
	public void foo() {
		field = "xyz";
		String s = bar(false) + bar(true) + bar(false);
		
			Logger.reportString(s,"FieldThisMethodMaybeDefines");
	}
	
	private String bar(boolean b) { // also requires context sensitivity to get perfect result
		if (b) {
			field = "bar";
		}
		return field;
	}
	
	public static void main(String[] args) {
		new FieldThisMethodMaybeDefines().foo();
	}
}
