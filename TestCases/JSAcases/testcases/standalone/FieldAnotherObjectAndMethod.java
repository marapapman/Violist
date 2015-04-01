package testcases.standalone;

import LoggerLib.Logger;
public class FieldAnotherObjectAndMethod {
    private String field;
    
    public void foo() {
        field = "A";
        new FieldAnotherObjectAndMethod().bar();
        new FieldAnotherObjectAndMethod().baz();
			Logger.reportString(field,"FieldAnotherObjectAndMethod");
    }
    
    private void bar() {
        field = "B";
    }
    private void baz() {
        field = "C";
    }
    
    public static void main(String[] args) {
        new FieldAnotherObjectAndMethod().foo();
    }
}
