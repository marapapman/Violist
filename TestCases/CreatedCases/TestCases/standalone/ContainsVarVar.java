package testcases.standalone;

import LoggerLib.Logger;
public class ContainsVarVar {
    private void foo(String s, String y) {
        boolean b = s.contains(y);
        String t = "" + b;
			Logger.reportString(t,"ContainsVarVar");
    }
    public static void main(String[] args) {
        new ContainsVarVar().foo("Hello world!", "world");
        new ContainsVarVar().foo("Goodbye world!", "orld");
        new ContainsVarVar().foo("The world is round!", "wor");
    }
}
