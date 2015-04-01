package testcases.standalone;

import LoggerLib.Logger;
public class IfNotContainsVarVar2 {
    private void foo(String x, String y) {
        if (!x.contains(y)) {
			Logger.reportString(y,"IfNotContainsVarVar2");
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
        new IfNotContainsVarVar2().bar("The quick brown fox jumped over the lazy dog");
        new IfNotContainsVarVar2().bar("I have a thick book"); // missing "ed"
        new IfNotContainsVarVar2().bar("I rented a thick book");
        new IfNotContainsVarVar2().bar("I mimicked what I read in a book");
        new IfNotContainsVarVar2().bar("I mimicked what I saw"); // missing "b"
    }
}
