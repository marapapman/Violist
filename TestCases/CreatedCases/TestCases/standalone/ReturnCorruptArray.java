package testcases.standalone;

import LoggerLib.Logger;
public class ReturnCorruptArray {
	private String[] field = new String[10];
	public void foo() {
		String[] buffer = bar();
		buffer[3] = "y";
		baz();
			Logger.reportString(buffer[3],"ReturnCorruptArray");
	}
	private String[] bar() {
		return field;
	}
	private void baz() {
		field[3] = "abc";
	}
	
	public static void main(String[] args) {
		new ReturnCorruptArray().foo();
	}
}
