package TestCases.ReplaceFirst;
import LoggerLib.Logger;
class CircleLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.replaceFirst("A","Ax");
		b=c.replaceFirst("A","Ay");
		long time=(int)(Math.random()*100); 
		if(time % 2 ==0)
		{
			c=a.replaceAll("A","Az");
		}
		else{
		   	c=a.replaceAll("A","Aw");
		}
		Logger.reportString(c,"ReplaceFirst.CircleLoop_Branch");
	}

    }
}

