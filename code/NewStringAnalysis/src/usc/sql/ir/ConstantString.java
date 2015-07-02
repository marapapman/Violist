package usc.sql.ir;

public class ConstantString extends Variable{

	String value;
	public ConstantString(String value)
	{
		this.value = value;
	}
	@Override
	public String getValue() {
		return value.replaceAll("\"", "");
	}
	@Override
	public String toString()
	{
		return "\"\\\""+getValue()+"\\\"\"";
	}
}
