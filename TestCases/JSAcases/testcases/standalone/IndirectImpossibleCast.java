package testcases.standalone;

import LoggerLib.Logger;
/**
 * Perform some cast operation that will fail always, but clever
 * enough to avoid javac's check.
 * 
 * Ideally, the analysis should conclude that it must be 'null', or
 * simply that it can't be an alias of the array argument.
 */
public class IndirectImpossibleCast implements Cloneable {
	private static void foo(String[] array, IndirectImpossibleCast obj) {
		Cloneable cloneable = obj;
		Object[] otherArray = (Object[])cloneable;
		
		if (otherArray != null) {
			otherArray[0] = "foo";			
		}
		
			Logger.reportString(array[0],"IndirectImpossibleCast");
	}
	public static void main(String[] args) {
		foo(new String[] {"abc"}, null);
	}
}
