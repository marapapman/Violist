package TestCases.ReplaceFirst;
import LoggerLib.Logger;
class SingleLoop_Interprocedural {
    public static String addA(String v)
	{
		return v.replaceFirst("A","Ax");
	}
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=addA(a);
		Logger.reportString(a,"ReplaceFirst.SingleLoop_Interprocedural");
	}

	

    }
}

