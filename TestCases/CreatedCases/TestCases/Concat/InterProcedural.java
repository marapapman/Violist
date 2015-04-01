package TestCases.Concat;
import LoggerLib.Logger;
class InterProcedural {
    public static String addA(String v)
	{
		return v+"A";
	}
    public static void main(String[] args) {
	String a="a";
	String b=addA(a);
	b=addA("b");
	Logger.reportString(b,"Concat.InterProcedural");

    }
}

