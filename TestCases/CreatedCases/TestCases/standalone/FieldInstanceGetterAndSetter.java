package testcases.standalone;

import LoggerLib.Logger;
public class FieldInstanceGetterAndSetter {
	private String field;
	
	// we declare the getters and setter private so they are not externally visible.
	// in practice they are public, but we don't want to test that aspect of the analysis here.
	private String getField() {
		return field;
	}
	private void setField(String field) {
		this.field = field;
	}
	
	public static void main(String[] args) {
		FieldInstanceGetterAndSetter a = new FieldInstanceGetterAndSetter();
		FieldInstanceGetterAndSetter b = new FieldInstanceGetterAndSetter();
		a.setField("abc");
		b.setField("xyz");
		String s = a.getField() + " " + b.getField();
			Logger.reportString(s,"FieldInstanceGetterAndSetter");
	}
}
