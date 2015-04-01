package testcases.standalone;

import LoggerLib.Logger;
public class ContainsConstConst {
    public void foo() {
        String s = "abcdef";
        boolean b = s.contains("cde");
        String t = "" + b;
			Logger.reportString(t,"ContainsConstConst");
    }
    public static void main(String[] args) {
        new ContainsConstConst().foo();
    }
}
