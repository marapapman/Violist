package testcases.standalone;

import LoggerLib.Logger;
public class ArrayClone {
	public void foo() {
		String[] array = new String[] {"foo"};
		String[] clone = array.clone();
		array[0] = "bar";
		
			Logger.reportString(clone[0],"ArrayClone");
	}
}
