/**
 * This class is to call copyValueOf(char[] data)
 */
package usc.sql.strings.testcase.java;

/**
 * @author wan
 *
 */
public class CopyValueOf1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] chars = new char[]{'a', 'r', 'r', 'a', 'y'};
	    String str = String.copyValueOf(chars);
	    
	    StringCaller.foo(str);
	}

}
