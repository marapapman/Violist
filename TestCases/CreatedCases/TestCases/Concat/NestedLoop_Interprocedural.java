package TestCases.Concat;
import LoggerLib.Logger;
class NestedLoop_Interprocedural {
  public static String addA(String v)
	{
		return v+"A";
	}
    public static void main(String[] args) {
	String a="a";
	String b="b";
	for(int i=0;i<2;i++)
	{
		a=a+addA(b)+"a";
		Logger.reportString(a,"Concat.NestedLoop_Interprocedural");

		for(int j=0;j<2;j++)
        {
			b+=addA(b);
		}
	}

    }
}

