package testcases.standalone;

import LoggerLib.Logger;
public class MethodCall {
	public void foo() {
		new GetX(); new GetZ();
		Foo foo = new GetY();
		String s = foo.foo();
		
		// test if the analysis can see that we are definitely invoking GetY.foo
			Logger.reportString(s,"MethodCall");
	}
	
	public interface Foo {
		String foo();
	}
	public static class GetX implements Foo {	// implements interface directly
		public String foo() {
			return "x";
		}
	}
	public static class GetY implements Foo {	// implements, but has a subtype
		public String foo() {
			return "y";
		}
	}
	public static class GetZ extends GetY {		// subtype of GetY
		@Override
		public String foo() {
			return "z";
		}
	}
	
	public static void main(String[] args) {
		new MethodCall().foo();
	}
}

