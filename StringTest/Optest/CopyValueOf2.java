/**
 * This class is to call copyValueOf(char[] data, int offset, int count)
 */
package usc.sql.strings.testcase.java;

/**
 * @author wan
 *
 */
public class CopyValueOf2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] chars = new char[]{'a', 'r', 'r', 'a', 'y'};
	    String str = String.copyValueOf(chars, 2, 3);
	    
	    StringCaller.foo(str);
	}

}
