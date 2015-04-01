package testcases.standalone;

import LoggerLib.Logger;
public class IfNotContainsConstVar {
    private void foo(String s) {
        if (!s.contains("world")) {
			Logger.reportString(s,"IfNotContainsConstVar");
        }
    }
    public static void main(String[] args) {
        new IfNotContainsConstVar().foo("Hello world!");
        new IfNotContainsConstVar().foo("Goodbye world!");
        new IfNotContainsConstVar().foo("The world is round!");
        new IfNotContainsConstVar().foo("Hello universe!");
        new IfNotContainsConstVar().foo("world says Hello!");
    }
}
