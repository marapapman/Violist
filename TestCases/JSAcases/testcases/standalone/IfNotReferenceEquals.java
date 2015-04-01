package testcases.standalone;

import LoggerLib.Logger;
public class IfNotReferenceEquals {
    private static void foo(String s) {
        String x = "b" + s;
        if (x != "bar") {
			Logger.reportString(x,"IfNotReferenceEquals");
        }
    }
    public static void main(String[] args) {
        foo("ar");
        foo("az");
        foo("oo");
    }
}
