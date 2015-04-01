package TestCases.toLowerCase;
import LoggerLib.Logger;
class InterProcedural {
    public static String addA(String v)
	{
		return v.toLowerCase();
	}
    public static void main(String[] args) {
	String a="Aa";
	String b=addA(a);
	b=addA("Ab");
	Logger.reportString(b,"toLowerCase.InterProcedural");

    }
}

