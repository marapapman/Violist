package TestCases.Mix;
import LoggerLib.Logger;
class NestedLoop_Interprocedural {
    public static String addA(String v)
	{
		String a=v;
		a=a.replaceAll("A","Ax");
		a=a.replaceFirst("A","Ap");
		a=a+" ";
		a=a.trim();
		a=a.toUpperCase();
		a=a.toLowerCase();
		a="A"+a;
		return a;
	}
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	for(int i=0;i<2;i++)
	{
		a=addA(b);
		Logger.reportString(a,"Mix.NestedLoop_Interprocedural");

		for(int j=0;j<2;j++)
        {
			b+=addA(b);
		}
	}

    }
}

