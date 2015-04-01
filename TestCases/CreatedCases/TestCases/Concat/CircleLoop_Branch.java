package TestCases.Concat;
import LoggerLib.Logger;
class CircleLoop_Branch {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="l";
	for(int i=0;i<3;i++)
	{
		a=a+b;
		b=b+c;
		long time=(int)(Math.random()*100);
		if(time % 2 ==0)
		{
			c=c+a+"c";
		}
		else{
		   	c=c+a+"d";
		}
		Logger.reportString(c,"Concat.CircleLoop_Branch");
	}

    }
}

