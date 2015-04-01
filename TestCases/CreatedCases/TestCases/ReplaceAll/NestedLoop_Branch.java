package TestCases.ReplaceAll;
import LoggerLib.Logger;
class NestedLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	for(int i=0;i<2;i++)
	{
		a=b.replaceAll("A","Ax");
		Logger.reportString(a,"ReplaceAll.NestedLoop_Branch");

		for(int j=0;j<2;j++)
        {
			long time=(int)(Math.random()*100); 
			if(time % 2 ==0)
			{
				b=b.replaceAll("A","Ay");
			}
			else{
				b=b.replaceAll("A","Az");
			}
		}
	}

    }
}

