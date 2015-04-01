package testcases.standalone;

import LoggerLib.Logger;
public class IfLengthEqualsConst {
    private static void foo(String s) {
        if (s.length() == 6) {
			Logger.reportString(s,"IfLengthEqualsConst");
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
