package TestCases.ReplaceAll;
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
		Logger.reportString(a,"ReplaceAll.SingleLoop");
	}

    }
}

