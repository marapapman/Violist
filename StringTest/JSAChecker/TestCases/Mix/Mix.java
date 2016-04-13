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
	String e="e";
	for(int i=0;i<3;i++)
	{
		long time=System.currentTimeMillis();
		int s=(int)(time%4);
		c+=addA("c1");
		switch(s){
			case 0: a+="0";b+=a+b;break;
			case 1: a+="1";b+=a+b;break;
			case 2: a+="2";b+=a+b;break;
			case 3: a+="3";b+=a+b;break;
		}
		if(s==0)
		{
			c+="s0";	
		}
		else{
			c+="s1";
		}
		c+=b+c;
		c+=addA("c");
		c+=addA("c2");
		Logger.reportString(c,"Mix");
	}
	

    }
}

