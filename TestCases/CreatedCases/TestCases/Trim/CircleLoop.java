package TestCases.Trim;
import LoggerLib.Logger;
class CircleLoop {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.trim();
		b=c.trim();
		c=a.trim();
		Logger.reportString(c,"Trim.CircleLoop");
	}

    }
}
