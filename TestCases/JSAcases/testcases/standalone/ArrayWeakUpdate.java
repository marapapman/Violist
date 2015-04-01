package testcases.standalone;

import LoggerLib.Logger;
public class ArrayWeakUpdate {
	public void foo() {
		String[] array = new String[10];
		array[1] = "foo";
		array[2] = "bar";
			Logger.reportString(array[1],"ArrayWeakUpdate");
	}
	public static void main(String[] args) {
		new ArrayWeakUpdate().foo();
	}
}
