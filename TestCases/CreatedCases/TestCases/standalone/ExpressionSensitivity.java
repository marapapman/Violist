package testcases.standalone;

import LoggerLib.Logger;
public class ExpressionSensitivity {
	public void foo(final boolean b) {
		String s = "x";
		if (b) {
			if (!b) {
				s = "y";
			}
		}
		// the usefulness of this test is arguable, since real code is almost always reachable
			Logger.reportString(s,"ExpressionSensitivity");
	}
}
