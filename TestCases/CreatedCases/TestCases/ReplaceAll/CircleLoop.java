package TestCases.ReplaceAll;
import LoggerLib.Logger;
class CircleLoop {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.replaceAll("A","Ax");
		b=c.replaceAll("A","Ay");
		c=a.replaceAll("A","Az");
		Logger.reportString(c,"ReplaceAll.CircleLoop");
	}

    }
}

