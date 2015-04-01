package testcases.standalone;

import LoggerLib.Logger;
public class CharConstant {
    public void foo() {
        String s = "";
        char ch = 'x';
        s += ch;
			Logger.reportString(s,"CharConstant");
    }
}
