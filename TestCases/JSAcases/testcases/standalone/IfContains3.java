package testcases.standalone;

import LoggerLib.Logger;
public class IfContains3 {
    public static void foo(String s) {
        if ("abcd".contains(s)) {
			Logger.reportString(s,"IfContains3");
        }
    }
    public static void main(String[] args) {
        foo("abcde");
        foo("X");
        foo("");
        foo("Hello bc Hello");
        foo("bc");
        foo("bcX");
        foo("Xbc");
        foo("bcd");
    }
}
