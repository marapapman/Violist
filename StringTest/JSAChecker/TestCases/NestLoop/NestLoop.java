import LoggerLib.Logger;
class NestLoop {
    public static void main(String[] args) {
	String a="";
	String b="";
	for(int i=0;i<3;i++)
	{
		a=a+b+"a";
		Logger.reportString(a,"NestLoop");

		for(int j=0;j<1;j++)
                {
			b=b+"b";
		}
	}

    }
}

