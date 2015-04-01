package TestCases.Mix;
import LoggerLib.Logger;
class CircleLoop_Interprocedural {
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
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=addA(b);
		b=addA(c);
		c=addA(a);
		Logger.reportString(c,"Mix.CircleLoop_Interprocedural");
	}

    }
}

