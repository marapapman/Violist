package TestCases.Trim;
import LoggerLib.Logger;
class NestedLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	for(int i=0;i<2;i++)
	{
		a=b.trim();
		Logger.reportString(a,"Trim.NestedLoop_Branch");

		for(int j=0;j<2;j++)
        {
			long time=(int)(Math.random()*100); 
			if(time % 2 ==0)
			{
				b=b.trim();
			}
			else{
				b=b.trim();
			}
		}
	}

    }
}

