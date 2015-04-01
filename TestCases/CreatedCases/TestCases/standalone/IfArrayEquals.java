package testcases.standalone;

import LoggerLib.Logger;
public class IfArrayEquals {
	private static void foo(String[] a, String[] x) {
		if (x.equals(a)) {
			// arrays are only equal by reference
			Logger.reportString(x[0],"IfArrayEquals");
		}
	}
	public static void main(String[] args){ 
		String[] a = new String[] {"a", "b"};
		foo(a, new String[] {"a", "c"}); 
		foo(a, new String[] {"a", "b"});
		foo(a, a);
	}
}
