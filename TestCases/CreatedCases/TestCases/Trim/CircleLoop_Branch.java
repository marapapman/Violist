package TestCases.Trim;
import LoggerLib.Logger;
class CircleLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.trim();
		b=c.trim();
		long time=(int)(Math.random()*100); 
		if(time % 2 ==0)
		{
			c=a.trim();
		}
		else{
		   	c=a.trim();
		}
		Logger.reportString(c,"Trim.CircleLoop_Branch");
	}

    }
}
