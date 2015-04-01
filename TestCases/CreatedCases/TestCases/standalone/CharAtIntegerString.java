package testcases.standalone;

import LoggerLib.Logger;
public class CharAtIntegerString {
    private String secondDigit(int x) {
        String s = "" + x;
        char ch;
        if (s.length() < 2)
            ch = '?';
        else
            ch = s.charAt(1);
        return "" + ch;
    }
    private void bar(int x) {
        String s = secondDigit(x);
			Logger.reportString(s,"CharAtIntegerString");
    }
    public static void main(String[] args) {
        new CharAtIntegerString().bar(-10);
        new CharAtIntegerString().bar(5);
        new CharAtIntegerString().bar(10);
        new CharAtIntegerString().bar(11);
        new CharAtIntegerString().bar(12);
        new CharAtIntegerString().bar(13);
        new CharAtIntegerString().bar(14);
        new CharAtIntegerString().bar(15);
        new CharAtIntegerString().bar(16);
        new CharAtIntegerString().bar(17);
        new CharAtIntegerString().bar(18);
        new CharAtIntegerString().bar(19);
    }
}
