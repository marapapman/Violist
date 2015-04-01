package testcases.standalone;

import LoggerLib.Logger;
public class IfCompound {
    private static void foo(String s, boolean x) {
        boolean z;
        if (x) {
            z = s.equals("Y");
        } else {
            z = s.contains("X");
        }
        if (z) {
			Logger.reportString(s,"IfCompound");
        }
    }
    private static void bar(String s) {
        foo(s,true);
        foo(s,false);
    }
    public static void main(String[] args) {
        bar("abcde");
        bar("X");
        bar("Y");
        bar("Hello bc Hello");
        bar("bc");
        bar("bcX");
        bar("Xbc");
    }
}
