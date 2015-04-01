package TestCases.ReplaceFirst;
import LoggerLib.Logger;
class NestedLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	for(int i=0;i<2;i++)
	{
		a=b.replaceFirst("A","Ax");
		Logger.reportString(a,"ReplaceFirst.NestedLoop_Branch");

		for(int j=0;j<2;j++)
        {
			long time=(int)(Math.random()*100); 
			if(time % 2 ==0)
			{
				b=b.replaceFirst("A","Ay");
			}
			else{
				b=b.replaceFirst("A","Az");
			}
		}
	}

    }
}

