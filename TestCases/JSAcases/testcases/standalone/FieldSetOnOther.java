package testcases.standalone;

import LoggerLib.Logger;
public class FieldSetOnOther {
    private String field;
    public void foo(FieldSetOnOther o1, FieldSetOnOther o2) {
        o1.field = "foo";
        bar(o1, o2);
			Logger.reportString(o1.field,"FieldSetOnOther");
    }
    
    private void bar(FieldSetOnOther o1, FieldSetOnOther o2) {
        o2.field = "bar";
    }
    
    public static void main(String[] args) {
        new FieldSetOnOther().foo(new FieldSetOnOther(), new FieldSetOnOther());
        
        FieldSetOnOther o = new FieldSetOnOther();
        o.foo(o, o);
        
        o = new FieldSetOnOther();
        new FieldSetOnOther().foo(o, o);
    }
}
