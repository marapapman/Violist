package testcases.standalone;

import LoggerLib.Logger;
/**
 * The German character "double S" is translated to "SS" by String.toUpperCase().
 * Test that the analysis agrees.
 * 
 * NOTE: CVS does not like wierd symbols in Java source files, so don't bother
 * to type the character here. Its unicode symbol is \u00df.
 * 
 * @author Asger
 */
public class UpperCaseWeirdSymbols {
	private static void foo(String x) {
		String y = x.toUpperCase();
			Logger.reportString(y,"UpperCaseWeirdSymbols");
	}
	public static void main(String[] args) {
		foo("\u00df");
	}
}
