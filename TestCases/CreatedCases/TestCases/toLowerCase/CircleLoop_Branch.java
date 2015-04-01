package TestCases.toLowerCase;
import LoggerLib.Logger;
class CircleLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.toLowerCase();
		b=c.toLowerCase();
		long time=(int)(Math.random()*100); 
		if(time % 2 ==0)
		{
			c=a.toLowerCase();
		}
		else{
		   	c=a.toLowerCase();
		}
		Logger.reportString(c,"toLowerCase.CircleLoop_Branch");
	}

    }
}
