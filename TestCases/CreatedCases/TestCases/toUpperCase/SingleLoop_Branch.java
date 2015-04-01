package TestCases.toUpperCase;
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
			a=a.toUpperCase();
		}
		else{
	   		a=a.toUpperCase();
		}
		Logger.reportString(a,"toUpperCase.SingleLoop_Branch");
	}

	

    }
}

