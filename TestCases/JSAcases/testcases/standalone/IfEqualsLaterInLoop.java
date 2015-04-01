package testcases.standalone;

import LoggerLib.Logger;
public class IfEqualsLaterInLoop {
    private static void foo(String s, String y, boolean x) {
        do {
            boolean b = s.equals(y);
            s += "x";
            for (int i=0; i<4; i++) {
                s += "x";
            }
            s += "x";
            if (b) {
                // 's' has changed since the time it was tested
			Logger.reportString(y,"IfEqualsLaterInLoop");
            }
        } while (x);
    }
    public static void main(String[] args) {
        foo("B", "B", false);
        foo("B", "C", false);
        foo("C", "B", false);
        foo("C", "C", false);
    }
}
