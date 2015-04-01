package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisNested1 {
	private String apples;
	private String oranges;
	
	public void foo() {
		apples = oranges = "foo";
		bar();
			Logger.reportString(apples + oranges,"FieldThisNested1");
	}
	private void bar() {
		baz();
		bong();
	}
	private void baz() {
		apples = "baz";
	}
	private void bong() {
		oranges = apples;
	}
	
	public static void main(String[] args) {
		new FieldThisNested1().foo();
	}
}
