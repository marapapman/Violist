package testcases.standalone;

import LoggerLib.Logger;
public class CharCast2 {
    private void foo(String s, int x, int y) {
        char a = (char)x;
        char b = (char)y;
			Logger.reportString(s + a + b,"CharCast2");
    }
    public static void main(String[] args) {
        new CharCast2().foo("abcd", 65, 68);
        new CharCast2().foo("xyz", 65, 73);
    }
}
