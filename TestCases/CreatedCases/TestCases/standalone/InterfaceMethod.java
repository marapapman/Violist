package testcases.standalone;

import LoggerLib.Logger;
public class InterfaceMethod {
	private interface Inner {
		String foo();
	}
	private static class InnerClass implements Inner {
		public String foo() {
			return "abc";
		}
	}
	public void foo(Inner inner) {
		String s = inner.foo();
			Logger.reportString(s,"InterfaceMethod");
	}
	
	public static void main(String[] args) {
		new InterfaceMethod().foo(new InnerClass());
	}
}
