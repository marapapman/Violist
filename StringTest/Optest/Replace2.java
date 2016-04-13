/**
 * replace(CharSequence target, CharSequence replacement)
 */
package usc.sql.strings.testcase.java;

/**
 * @author wan
 *
 */
public class Replace2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "Hello";
		CharSequence oldCs = "ll";
		CharSequence newCs = "mm";
	    String str = a.replace(oldCs, newCs);
	    
	    StringCaller.foo(str);
	}

}
