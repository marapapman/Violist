package testcases.standalone;

import LoggerLib.Logger;
public class StringArrayAliasArguments {
	private void foo(String[] array1, CharSequence[] array2) {
		array2[0] = "abc";
			Logger.reportString(array1[0],"StringArrayAliasArguments");
	}
	public static void main(String[] args) {
		String[] array = {"x", "y", "z"};
		String[] array2 = {"w"};
		new StringArrayAliasArguments().foo(array, array);
		new StringArrayAliasArguments().foo(array, array2);
	}
}
