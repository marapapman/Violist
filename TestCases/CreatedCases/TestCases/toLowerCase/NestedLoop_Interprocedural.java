package TestCases.toLowerCase;
import LoggerLib.Logger;
class NestedLoop_Interprocedural {
    public static String addA(String v)
	{
		return v.toLowerCase();
	}
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	for(int i=0;i<2;i++)
	{
		a=addA(b);
		Logger.reportString(a,"toLowerCase.NestedLoop_Interprocedural");

		for(int j=0;j<2;j++)
        {
			b+=addA(b);
		}
	}

    }
}

