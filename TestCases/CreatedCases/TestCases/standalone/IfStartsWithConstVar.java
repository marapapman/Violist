package testcases.standalone;

import LoggerLib.Logger;
public class IfStartsWithConstVar {
    private static void foo(String y) {
        if ("sirsneezealot".startsWith(y)) {
			Logger.reportString(y,"IfStartsWithConstVar");
        }
    }
    public static void main(String[] args) {
        foo("sir");
        foo("sneeze");
        foo("alot");
        foo("sirlaughalot");
        foo("siren");
        foo("fooze");
        foo("f");
        foo("ffoo");
        String s = "fooB";
        String t = "sirB";
        for (int i=0; i<3; i++) {
            s += "z";
            t += "z";
        }
        foo(s);
        foo("x" + s);
        foo(t);
    }
}
