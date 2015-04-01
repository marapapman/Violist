package testcases.standalone;

import LoggerLib.Logger;
public class StringToCharArray {
    private static void foo(String s, int x, int y) {
        char[] chars = s.toCharArray();
        String t = "" + chars[x] + chars[y];
			Logger.reportString(t,"StringToCharArray");
    }
    
    public static void main(String[] args) {
        foo("abcdabc", 1, 2);
        foo("abcdabc", 4, 5);
        foo("abcdabc", 2, 0);
        foo("abcdabc", 3, 3);
        foo("xy", 0, 1);
    }
}
