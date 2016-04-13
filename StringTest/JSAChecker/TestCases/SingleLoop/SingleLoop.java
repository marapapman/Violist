import LoggerLib.Logger;
class SingleLoop {
    public static void main(String[] args) {
	String a="";
	String b="";
	String c="";
	String e="";
	for(int i=0;i<3;i++)
	{
		a+=a+"a";
		b+=a+b;
		c+=b+c;
		Logger.reportString(c,"SingleLoop");
	}

    }
}

