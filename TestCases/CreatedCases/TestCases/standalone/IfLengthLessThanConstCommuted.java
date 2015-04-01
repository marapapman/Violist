package testcases.standalone;

import LoggerLib.Logger;
public class IfLengthLessThanConstCommuted {
    private static void foo(String s) {
        if (6 > s.length()) {
			Logger.reportString(s,"IfLengthLessThanConstCommuted");
        }
    }
    public static void main(String[] args) {
        foo("abc");
        foo("abcdef");
        foo("abcdefabc");
        String s = "x";
        for (int i=0; i<10; i++) {
            s += "y";
            foo(s);
        }
    }
}
