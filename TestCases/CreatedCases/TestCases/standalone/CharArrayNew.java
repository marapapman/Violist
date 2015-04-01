package testcases.standalone;

import LoggerLib.Logger;
public class CharArrayNew {
    private static void foo() {
        char[] array = new char[10];
        array[1] = 'a';
        String s = "" + array[1] + array[2];
			Logger.reportString(s,"CharArrayNew");
    }
    public static void main(String[] args) {
        foo();
    }
}
