package testcases.standalone;

import LoggerLib.Logger;
public class IfStartsWithVarVar2 {
    private static void foo(String s, String y) {
        if (s.startsWith(y)) {
			Logger.reportString(y,"IfStartsWithVarVar2");
        }
    }
    private static void bar(String s) {
        foo(s, "foo");
        foo(s, "Y");
    }
    public static void main(String[] args) {
        bar("boz");
        bar("bfooX");
        bar("boofoo");
        bar("foofoo");
        bar("foo");
        bar("fo");
        bar("f");
        bar("ffoo");
        String s = "fooB";
        for (int i=0; i<3; i++) {
            s += "z";
        }
        bar(s);
        bar("x" + s);
    }
}
