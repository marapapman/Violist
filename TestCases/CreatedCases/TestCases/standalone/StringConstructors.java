package testcases.standalone;

import LoggerLib.Logger;
public class StringConstructors {
	public void foo(int x) {
		String s = new String("abcd").toUpperCase() + new String();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(s); 		// buffer = ABCD
		
		StringBuilder builder = new StringBuilder(buffer); 
								// builder = ABCD
		builder.append("w"); 	// builder = ABCDw
		
		s = new String(buffer) + new String(builder); 
								// s = ABCDABCDw
		
		// modifying these after the constructor calls will not mess up 's'
		buffer.append("X"); 
		builder.setLength(0);
		
			Logger.reportString(s,"StringConstructors");
	}
	
	public static void main(String[] args) {
		new StringConstructors().foo(5);
	}
}
