package TestCases.Concat;
import LoggerLib.Logger;
class CircleLoop_Interprocedural {
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
		a=a+b;
		b=b+c;
		c=c+addA(a);
		Logger.reportString(c,"Concat.CircleLoop_Interprocedural");
	}

    }
}

