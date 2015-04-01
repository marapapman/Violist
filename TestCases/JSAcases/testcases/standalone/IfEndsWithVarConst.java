package testcases.standalone;

import LoggerLib.Logger;
public class IfEndsWithVarConst {
    private static void foo(String s) {
        if (s.endsWith("oo")) {
			Logger.reportString(s,"IfEndsWithVarConst");
        }
    }
    public static void main(String[] args) {
        foo("boz");
        foo("bfooX");
        foo("boofoo");
        foo("foofoo");
        foo("foo");
        foo("fo");
        foo("f");
        foo("ffoo");
        String s = "fooB";
        for (int i=0; i<3; i++) {
            s += "z";
        }
        foo(s);
        foo("x" + s);
    }
}
