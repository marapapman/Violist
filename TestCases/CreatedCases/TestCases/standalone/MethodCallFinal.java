package testcases.standalone;

import LoggerLib.Logger;
public class MethodCallFinal {
	public void foo() {
		new GetX();
		Foo foo = new GetY();
		String s = foo.foo();
		
		// test if the analysis can see that we are definitely invoking GetY.foo
		// this test is made easier than 'MethodCall' because GetY.foo is now final.
			Logger.reportString(s,"MethodCallFinal");
	}
	
	public interface Foo {
		String foo();
	}
	public static class GetX implements Foo {
		final public String foo() {
			return "x";
		}
	}
	public static class GetY implements Foo {
		final public String foo() {
			return "y";
		}
	}
	public static class Subtype extends GetY {
	}
	
}

