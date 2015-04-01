package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisSomeDefined {
	private String apples, oranges;
	
	private void foo(FieldThisSomeDefined o) {
		oranges = "zoink";
		o.bong();
		bar();
		o.baz();
			Logger.reportString(apples + oranges,"FieldThisSomeDefined");
	}
	private void bar() {
		apples = "bar";
	}
	private void bong() {
		apples = "bong";
	}
	private void baz() {
		oranges = "baz";
	}
	
	public static void main(String[] args) {
		new FieldThisSomeDefined().foo(new FieldThisSomeDefined());
		
		FieldThisSomeDefined o = new FieldThisSomeDefined();
		o.foo(o);
	}
}
