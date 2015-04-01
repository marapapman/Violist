package testcases.standalone;

import LoggerLib.Logger;
public class FieldThisSometimesDefined {
	private String field;
	public void foo() {
		field = "abc";
		bar(false);
		String s = field;
			Logger.reportString(s,"FieldThisSometimesDefined");
	}
	private void bar(boolean b) {
		if (b) {
			field = "bar";
		}
	}
	
	public static void main(String[] args) {
		new FieldThisSometimesDefined().foo();
	}
}
