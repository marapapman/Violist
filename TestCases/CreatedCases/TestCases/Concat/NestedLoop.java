package TestCases.Concat;
import LoggerLib.Logger;
class NestedLoop {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	for(int i=0;i<10;i++)
	{
		a+=a+b+"a";
		Logger.reportString(a,"Concat.NestedLoop");

		for(int j=0;j<2;j++)
        	{
			b=b+"b";
		}
	}

    }
}

