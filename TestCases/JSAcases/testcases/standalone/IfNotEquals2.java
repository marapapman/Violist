package testcases.standalone;

import LoggerLib.Logger;
public class IfNotEquals2 {
    private static void foo(String s, String y) {
        String x = "b" + s;
        if (!x.equals(y)) {
			Logger.reportString(x,"IfNotEquals2");
        }
    }
    private static String getSomething(int x) {
        switch (x) {
        case 0: return "foo";
        default: return "bar";
        }
    }
    private static void bar(String s) {
        for (int i=0; i<5; i++) {
            foo(s, getSomething(i));
        }
    }
    public static void main(String[] args) {
        bar("ar");
        bar("az");
        bar("oo");
    }
}
