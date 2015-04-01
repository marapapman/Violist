package testcases.standalone;

import LoggerLib.Logger;
public class IfEqualsLater2 {
    private static void foo(String s, String y) {
        boolean b = s.equals(y);
        s += "x";
        for (int i=0; i<4; i++) {
            s += "x";
        }
        if (b) {
            // 's' has changed since the time it was tested
			Logger.reportString(y,"IfEqualsLater2");
        }
    }
    public static void main(String[] args) {
        foo("B", "B");
        foo("B", "C");
        foo("C", "B");
        foo("C", "C");
    }
}
