package testcases.standalone;

import LoggerLib.Logger;
public class FieldMethodUsesAndDefines {
	private String field;
	
	public void foo(boolean b, boolean b2) {
		this.field = "A";
		String s = bar(b) + bar2(b2) + bar2(false);
			Logger.reportString(s,"FieldMethodUsesAndDefines");
	}
	
	// bar has been copied to simulate the optimal context sensitivity, because we want to 
	// test the field implementation here, independant of other aspects of the analysis.
	private String bar(boolean b) {
		String s = field;
		if (b) {
			this.field = "B";
		} else {
			this.field = "C";
		}
		return s;
	}
	private String bar2(boolean b) {
		String s = field;
		if (b) {
			this.field = "B";
		} else {
			this.field = "C";
		}
		return s;
	}
	
	public static void main(String[] args) {
		new FieldMethodUsesAndDefines().foo(true, true);
		new FieldMethodUsesAndDefines().foo(true, false);
		new FieldMethodUsesAndDefines().foo(false, true);
		new FieldMethodUsesAndDefines().foo(false, false);
	}
}
