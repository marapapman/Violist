package TestCases.Mix;
import LoggerLib.Logger;
class Branch_Interprocedural {
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
	long time=(int)(Math.random()*100); 
	if(time % 2 ==0)
	{
		a=addA(a);
	}
	else{
	   	a=addA(b);
	}

	Logger.reportString(a,"Mix.Branch_Interprocedural");

    }
}

