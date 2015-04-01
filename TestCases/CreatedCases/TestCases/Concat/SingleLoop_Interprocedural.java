package TestCases.Concat;
import LoggerLib.Logger;
class SingleLoop_Interprocedural {
 public static String addA(String v)
	{
		return v+"A";
	}
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="e";
	for(int i=0;i<3;i++)
	{
		a+=addA(a);
		Logger.reportString(a,"Concat.SingleLoop_Interprocedural");
	}

	

    }
}

