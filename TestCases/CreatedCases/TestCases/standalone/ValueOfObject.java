package testcases.standalone;

import LoggerLib.Logger;
public class ValueOfObject {
	public void foo(boolean b){ 
		String x = "abc";
		String y = "xyz";
		Object o;
		if (b) {
			o = x;
		} else {
			o = y;
		}
		String z = String.valueOf(o);
			Logger.reportString(z,"ValueOfObject");
	}
	public static void main(String[] args) {
		new ValueOfObject().foo(true);
		new ValueOfObject().foo(false);
	}
}
