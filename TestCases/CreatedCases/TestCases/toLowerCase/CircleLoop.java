package TestCases.toLowerCase;
import LoggerLib.Logger;
class CircleLoop {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.toLowerCase();
		b=c.toLowerCase();
		c=a.toLowerCase();
		Logger.reportString(c,"toLowerCase.CircleLoop");
	}

    }
}


