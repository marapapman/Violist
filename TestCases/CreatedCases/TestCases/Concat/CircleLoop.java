package TestCases.Concat;
import LoggerLib.Logger;
class CircleLoop {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="l";
	for(int i=0;i<3;i++)
	{
		a=a+b;
		b=b+c;
		c=c+a;
		Logger.reportString(c,"Concat.CircleLoop");
	}

    }
}

