package testcases.standalone;

import LoggerLib.Logger;
public class IfLengthLessThanConst {
    private static void foo(String s) {
        if (s.length() < 6) {
			Logger.reportString(s,"IfLengthLessThanConst");
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
