/**
 *  	toUpperCase(Locale locale)
 */
package usc.sql.strings.testcase.java;

import java.util.Locale;

/**
 * @author wan
 *
 */
public class ToUpperCase2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "Hey";
	    String str = a.toUpperCase(Locale.US);
	    
	    StringCaller.foo(str);
	}

}
