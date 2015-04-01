package testcases.standalone;

import LoggerLib.Logger;


public class ArrayAssignment {
	public static void foo(int i, int j) {
		String[] array = new String[5];
		
		array[i] = "foo";
		array[j] = "bar";
		
			Logger.reportString(array[i],"ArrayAssignment");
	}
	
	public static void main(String[] args) {
		foo(2, 2);
		foo(1, 2);
	}
}
