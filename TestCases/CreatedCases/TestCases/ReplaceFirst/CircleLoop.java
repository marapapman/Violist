package TestCases.ReplaceFirst;
import LoggerLib.Logger;
class CircleLoop {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.replaceFirst("A","Ax");
		b=c.replaceFirst("A","Ay");
		c=a.replaceFirst("A","Az");
		Logger.reportString(c,"ReplaceFirst.CircleLoop");
	}

    }
}
