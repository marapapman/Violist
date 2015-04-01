package testcases.standalone;

import LoggerLib.Logger;
public class ContainsVarConst {
    private void foo(String s, int x) {
        s += x;
        boolean b = s.contains("world");
        String t = "" + b;
			Logger.reportString(t,"ContainsVarConst");
    }
    public static void main(String[] args) {
        new ContainsVarConst().foo("Hello world!", 15);
        new ContainsVarConst().foo("Goodbye world!", -50);
        new ContainsVarConst().foo("The world is round!", 0);
    }
}
