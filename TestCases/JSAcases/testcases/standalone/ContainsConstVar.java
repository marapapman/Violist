package testcases.standalone;

import LoggerLib.Logger;
public class ContainsConstVar {
    private void foo(String y) {
        boolean b = "Hello world!".contains(y);
        String t = "" + b;
			Logger.reportString(t,"ContainsConstVar");
    }
    public static void main(String[] args) {
        new ContainsConstVar().foo("world");
        new ContainsConstVar().foo("orld");
        new ContainsConstVar().foo("wor");
    }
}
