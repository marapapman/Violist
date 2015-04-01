package testcases.standalone;

import LoggerLib.Logger;
public class OverriddenToString {
	
	private static void foo(Foo foo) {
		String s = String.valueOf(foo);
			Logger.reportString(s,"OverriddenToString");
	}
	
	public static void main(String[] args) {
		foo(new Foo());
		foo(new Bar());
		foo(null);
	}
	
	private static class Foo {
		@Override
		public String toString() {
			return "foo";
		}
	}
	private static class Bar extends Foo {
		@Override
		public String toString() {
			return "bar";
		}
	}
}
