package testcases.standalone;

import LoggerLib.Logger;
public class CharArrayMultiNew {
    private static void foo() {
        char[][] array = new char[10][5];
        array[1][0] = 'a';
        String s = "" + array[1][0] + array[2][1];
			Logger.reportString(s,"CharArrayMultiNew");
    }
    public static void main(String[] args) {
        foo();
    }
}
