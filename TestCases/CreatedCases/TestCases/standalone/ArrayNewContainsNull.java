package testcases.standalone;

import LoggerLib.Logger;
public class ArrayNewContainsNull {
	public void foo() {
		String[] array = new String[10];
		String s = String.valueOf(array[0]);
			Logger.reportString(s,"ArrayNewContainsNull");
	}
	
	public static void main(String[] args) {
		new ArrayNewContainsNull().foo();
	}
}
