package testcases.standalone;

import LoggerLib.Logger;
public class IfContains {
    private static void foo(String s) {
        String y = "bc";
        String z;
        if (s.contains(y)) {
            z = s;
        } else {
            z = "X" + s;
        }
			Logger.reportString(z,"IfContains");
    }
    public static void main(String[] args) {
        foo("abcde");
        foo("X");
        foo("");
        foo("Hello bc Hello");
        foo("bc");
        foo("bcX");
        foo("Xbc");
    }
}
