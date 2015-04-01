package TestCases.ReplaceFirst;
import LoggerLib.Logger;
class SingleLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		long time=(int)(Math.random()*100); 
		if(time % 2 ==0)
		{
			a=a.replaceFirst("A","Ax");
		}
		else{
	   		a=a.replaceFirst("A","Ay");
		}
		Logger.reportString(a,"ReplaceFirst.SingleLoop_Branch");
	}

	

    }
}

