package TestCases.Trim;
import LoggerLib.Logger;
class CircleLoop_Interprocedural {
    public static String addA(String v)
	{
		return v.trim();
	}
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=addA(b);
		b=addA(c);
		c=addA(a);
		Logger.reportString(c,"Trim.CircleLoop_Interprocedural");
	}

    }
}
