package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisNested3 {
	private String apples;
	private String oranges;
	private boolean maybe;
	
	public void foo(boolean b) {
		this.maybe = b;
		apples = oranges = "foo";
		bar();
			Logger.reportString(apples + oranges,"FieldThisNested3");
	}
	private void bar() {
		baz();
		bong();
	}
	private void baz() {
		if (maybe) {
			apples = oranges + "bar";
		}
	}
	private void bong() {
		oranges = "bong";
	}
	
	public static void main(String[] args) {
		new FieldThisNested3().foo(true);
		new FieldThisNested3().foo(false);
	}
}
