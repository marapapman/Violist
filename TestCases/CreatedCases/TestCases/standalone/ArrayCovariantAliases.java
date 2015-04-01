package testcases.standalone;

import LoggerLib.Logger;
public class ArrayCovariantAliases {
	// make foo private so it is not externally visible
	private static void foo(String[] strings, CharSequence[] sequences) {
		strings[0] = "foo";
		sequences[0] = "bar";
			Logger.reportString(strings[0],"ArrayCovariantAliases");
	}
	public static void main(String[] args) {
		String[] array = new String[5];
		array[0] = "baz";
		CharSequence[] seq = array;
		foo(array, seq);
		
		foo(new String[5], new String[5]);
		foo(new String[5], new CharSequence[5]);
	}
}
