package TestCases.Mix;
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
		c=c.replaceFirst("A","Ap");
		c=c+" ";
		c=c.trim();
		c=c.toUpperCase();
		c=c.toLowerCase();
		c="A"+c;
		Logger.reportString(c,"Mix.CircleLoop_Branch");
	}

    }
}

