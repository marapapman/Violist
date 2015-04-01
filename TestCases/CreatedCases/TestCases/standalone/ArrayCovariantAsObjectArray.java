package testcases.standalone;

import LoggerLib.Logger;
public class ArrayCovariantAsObjectArray {
	public void foo() {
		String[] array = new String[6];
		array[0] = "bar";
		bar(array);
			Logger.reportString(array[0],"ArrayCovariantAsObjectArray");
	}
	private void bar(Object[] array) {
		array[0] = "baz";
	}
	public static void main(String[] args) {
		new ArrayCovariantAsObjectArray().foo();
	}
}
