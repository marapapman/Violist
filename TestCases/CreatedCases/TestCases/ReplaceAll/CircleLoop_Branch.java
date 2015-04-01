package TestCases.ReplaceAll;
import LoggerLib.Logger;
class CircleLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		a=b.replaceAll("A","Ax");
		b=c.replaceAll("A","Ay");
		long time=(int)(Math.random()*100); 
		if(time % 2 ==0)
		{
			c=a.replaceAll("A","Az");
		}
		else{
		   	c=a.replaceAll("A","Aw");
		}
		Logger.reportString(c,"ReplaceAll.CircleLoop_Branch");
	}

    }
}

