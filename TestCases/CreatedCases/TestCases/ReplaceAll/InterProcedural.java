package TestCases.ReplaceAll;
import LoggerLib.Logger;
class InterProcedural {
    public static String addA(String v)
	{
		return v.replaceAll("A","Ax");
	}
    public static void main(String[] args) {
	String a="Aa";
	String b=addA(a);
	b=addA("Ab");
	Logger.reportString(b,"ReplaceAll.InterProcedural");

    }
}

