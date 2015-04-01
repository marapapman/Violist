package testcases.standalone;

import LoggerLib.Logger;
public class CharArgument {
    private void foo(char ch) {
        String s = "x";
        s += ch;
        s += ch;
			Logger.reportString(s,"CharArgument");
    }
    public static void main(String[] args) {
        new CharArgument().foo('A');
        new CharArgument().foo('B');
    }
}
