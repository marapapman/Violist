package TestCases.toLowerCase;
import LoggerLib.Logger;
class Mix {
    public static String addA(String v)
	{
		return v.toLowerCase();
	}
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String d="Ad";
	for(int i=0;i<2;i++)
	{
		d=addA(c);
		Logger.reportString(d,"toLowerCase.Mix");
		for(int j=0;j<2;j++)
                {
			a=addA(b);
			b=addA(c);
			long time=(int)(Math.random()*100); 
			if(time % 2 ==0)
			{
				c=c.toLowerCase();
			}
			else{
				c=c.toLowerCase();
			}

		}
	}

    }
}

