package testcases.standalone;

import LoggerLib.Logger;
public class IfEqualsNotStringWithToString {
    private static final class Hest {
        @Override
        public String toString() {
            return "Rasmus";
        }
    }
    private static void foo(String x, Object y) {
        if (x.equals(y)) {
            // the analysis cannot distinguish an object with toString()=S from the actual string S
			Logger.reportString(x,"IfEqualsNotStringWithToString");
        }
    }
    public static void main(String[] args) {
        foo("abc", "abc");
        foo("Rasmus", new Hest());
    }
}
