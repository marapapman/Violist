package testcases.standalone;

import LoggerLib.Logger;
public class IfEqualsNotString {
    private static void foo(String x, Object y) {
        if (x.equals(y)) {
			Logger.reportString(x,"IfEqualsNotString");
        }
    }
    public static void main(String[] args) {
        foo("abc", "abc");
        foo("bar", new Object());
        foo("xyz", new StringBuffer("xyz"));
    }
}
