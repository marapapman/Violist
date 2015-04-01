package TestCases.toUpperCase;
import LoggerLib.Logger;
class InterProcedural {
    public static String addA(String v)
	{
		return v.toUpperCase();
	}
    public static void main(String[] args) {
	String a="Aa";
	String b=addA(a);
	b=addA("Ab");
	Logger.reportString(b,"toUpperCase.InterProcedural");

    }
}

