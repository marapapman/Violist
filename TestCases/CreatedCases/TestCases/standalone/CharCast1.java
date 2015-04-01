package testcases.standalone;

import LoggerLib.Logger;
public class CharCast1 {
    private void foo(String s, int x, int y) {
        char a = (char)x;
        char b = (char)(x + y);
			Logger.reportString(s + a + b,"CharCast1");
    }
    public static void main(String[] args) {
        new CharCast1().foo("abcd", 65, 3);
        new CharCast1().foo("xyz", 65, 8);
    }
}
