import LoggerLib.Logger;
class LoopInterpro {
    public static String addA(String v)
	{
		return v+"A";
	}
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="";
	for(int i=0;i<3;i++)
	{
		a+=addA(a);
		b+=a+b;
		c+=b+c;
		c+=addA("c");
		Logger.reportString(c,"LoopInterpro");
	}

    }
}

