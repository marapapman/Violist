package TestCases.toUpperCase;
import LoggerLib.Logger;
class CircleLoop {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.toUpperCase();
		b=c.toUpperCase();
		c=a.toUpperCase();
		Logger.reportString(c,"toUpperCase.CircleLoop");
	}

    }
}


