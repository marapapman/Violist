/**
 * toLowerCase(Locale locale)
 */
package usc.sql.strings.testcase.java;

import java.util.Locale;

/**
 * @author wan
 *
 */
public class ToLowerCase2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "Hey";
	    String str = a.toLowerCase(Locale.US);
	    
	    StringCaller.foo(str);
	}

}
