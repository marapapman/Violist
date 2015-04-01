package testcases.standalone;

import LoggerLib.Logger;
public class IfNotContainsVarConst {
    private void foo(String s) {
        if (!"Hello world!".contains(s)) {
			Logger.reportString(s,"IfNotContainsVarConst");
        }
    }
    public static void main(String[] args) {
        new IfNotContainsVarConst().foo("Hello");
        new IfNotContainsVarConst().foo("world");
        new IfNotContainsVarConst().foo("o world");
        new IfNotContainsVarConst().foo("!");
        new IfNotContainsVarConst().foo("");
        new IfNotContainsVarConst().foo("baz");
        new IfNotContainsVarConst().foo("boing");
    }
}
