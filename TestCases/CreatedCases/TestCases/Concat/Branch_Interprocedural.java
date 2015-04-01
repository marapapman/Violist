package TestCases.Concat;
import LoggerLib.Logger;
class Branch_Interprocedural {
    public static String addA(String v)
	{
		return v+"A";
	}
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="e";
	long time=(int)(Math.random()*100);
	if(time % 2 ==0)
	{
		a+=addA(a);
	}
	else{
	   	a+=addA(b);
	}

	Logger.reportString(a,"Concat.Branch_Interprocedural");

    }
}

