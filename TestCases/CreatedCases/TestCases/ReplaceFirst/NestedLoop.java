package TestCases.ReplaceFirst;
import LoggerLib.Logger;
class NestedLoop {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	for(int i=0;i<2;i++)
	{
		a=b.replaceFirst("A","Ax");
		Logger.reportString(a,"ReplaceFirst.NestedLoop");

		for(int j=0;j<2;j++)
        {
			b=b.replaceFirst("A","Ay");
		}
	}

    }
}
