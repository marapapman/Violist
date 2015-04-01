package testcases.standalone;

import LoggerLib.Logger;
public class IfNotEquals1 {
    private static void foo(String s) {
        String x = "b" + s;
        if (!x.equals("bar")) {
			Logger.reportString(x,"IfNotEquals1");
        }
    }
    public static void main(String[] args) {
        foo("ar");
        foo("az");
        foo("oo");
    }
}
