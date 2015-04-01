package testcases.standalone;

import LoggerLib.Logger;
public class IrrelevantArgs {
	private void foo(IrrelevantArgs arg1, String s, IrrelevantArgs arg2) {
		String x = s;
			Logger.reportString(x,"IrrelevantArgs");
	}
	
	public static void main(String[] args) {
		new IrrelevantArgs().foo(new IrrelevantArgs(), "abc", new IrrelevantArgs());
	}
}
