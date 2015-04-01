package testcases.standalone;

import LoggerLib.Logger;
public class FieldCallMethod2 {
    private String field;
    
    public void foo() {
        field = "foo";
        bar();
			Logger.reportString(field,"FieldCallMethod2");
    }
    
    private String bar() {
        baz();
        return field;
    }
    
    private void baz() {
        field = "baz";
    }
    
    public static void main(String[] args) {
        new FieldCallMethod2().foo();
    }
}
