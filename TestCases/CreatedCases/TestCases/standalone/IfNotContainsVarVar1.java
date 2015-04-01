package testcases.standalone;

import LoggerLib.Logger;
public class IfNotContainsVarVar1 {
    private void foo(String x, String y) {
        if (!x.contains(y)) {
			Logger.reportString(x,"IfNotContainsVarVar1");
        }
    }
    private static String getSomething(int x) {
        switch (x) {
        case 0: return "a";
        case 1: return "b";
        case 2: return "ck";
        default: return "ed";
        }
    }
    private void bar(String s) {
        for (int i=0; i<4; i++) {
            foo(s, getSomething(i));
        }
    }
    public static void main(String[] args) {
        new IfNotContainsVarVar1().bar("The quick brown fox jumped over the lazy dog"); // has all = not a possibility
        new IfNotContainsVarVar1().bar("gah"); // missing "b", "ck" and "ed"
        new IfNotContainsVarVar1().bar("bog"); // missing "a", "ck" and "ed"
        new IfNotContainsVarVar1().bar("move"); // missing all
        new IfNotContainsVarVar1().bar("I have a thick book"); // missing "ed"
        new IfNotContainsVarVar1().bar("I rented a thick book"); // has all = not a possibility
    }
}
