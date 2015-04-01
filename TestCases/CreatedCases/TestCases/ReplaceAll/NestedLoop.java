package TestCases.ReplaceAll;
import LoggerLib.Logger;
class NestedLoop {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	for(int i=0;i<2;i++)
	{
		a=b.replaceAll("A","Ax");
		Logger.reportString(a,"ReplaceAll.NestedLoop");

		for(int j=0;j<2;j++)
        {
			b=b.replaceAll("A","Ay");
		}
	}

    }
}

