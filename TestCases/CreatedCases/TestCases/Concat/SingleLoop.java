package TestCases.Concat;
import LoggerLib.Logger;
class SingleLoop {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="e";
	for(int i=0;i<3;i++)
	{
		a=a+"a";
		b=a+b;
		c=b+c;
		Logger.reportString(c,"Concat.SingleLoop");
	}

    }
}

