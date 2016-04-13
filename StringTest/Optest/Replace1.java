/**
 * replace(char oldChar, char newChar)
 */
package usc.sql.strings.testcase.java;

/**
 * @author wan
 *
 */
public class Replace1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "Hello";
		char oldCh = 'l';
		char newCh = 'm';
	    String str = a.replace(oldCh, newCh);
	    
	    StringCaller.foo(str);
	}

}
