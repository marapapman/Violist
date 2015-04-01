package TestCases.Concat;
import LoggerLib.Logger;
class Mix {
  public static String addA(String v)
	{
		return v+"A";
	}
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String d="d";
	for(int i=0;i<2;i++)
	{
		d=d+addA(c)+"a";
		Logger.reportString(d,"Concat.Mix");
		for(int j=0;j<2;j++)
                {
			a=a+b;
			b=b+c;
			long time=System.currentTimeMillis();
			if(time % 2 ==0)
			{
				c=c+addA(a)+"c";
			}
			else{
				c=c+addA(a)+"d";
			}

		}
	}

    }
}

