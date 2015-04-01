package testcases.standalone;

import LoggerLib.Logger;
public class Scap {
    public void test() {
			Logger.reportString("a","Scap");
    }
    
    public String foo(int x) {
        StringBuffer b = new StringBuffer("I ate ");
        if (x > 0) {
            b.append(x);
        } else {
            b.append("no");
        }
        b.append(" apples today");
        return b.toString();
    }
}
