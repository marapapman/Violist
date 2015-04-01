package TestCases.toUpperCase;
import LoggerLib.Logger;
class CircleLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.toUpperCase();
		b=c.toUpperCase();
		long time=(int)(Math.random()*100); 
		if(time % 2 ==0)
		{
			c=a.toUpperCase();
		}
		else{
		   	c=a.toUpperCase();
		}
		Logger.reportString(c,"toUpperCase.CircleLoop_Branch");
	}

    }
}
