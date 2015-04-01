package TestCases.Mix;
import LoggerLib.Logger;
class SingleLoop {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=a.replaceAll("A","Ax");
		a=a.replaceFirst("A","Ap");
		a=a+" ";
		a=a.trim();
		a=a.toUpperCase();
		a=a.toLowerCase();
		a="A"+a;
		Logger.reportString(a,"Mix.SingleLoop");
	}

    }
}

