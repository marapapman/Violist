package testcases.standalone;

import LoggerLib.Logger;
/**
 * A method that sometimes mutates its parameter should affect mutable arguments,
 * but not arguments that are definitely immutable (bad precision).
 */
public class ArgumentStringImmutable {
	public void foo() {
		String s = "A";
		// although the argument might be mutated, it can't be the case
		// when the actual argument is known to be immutable
		bar(s);
			Logger.reportString(s,"ArgumentStringImmutable");
	}
	private void bar(CharSequence seq) {
		if (seq instanceof StringBuffer) {
			((StringBuffer)seq).append("xyz");
		}
	}
	public static void main(String[] args) {
		new ArgumentStringImmutable().foo();
	}
}
