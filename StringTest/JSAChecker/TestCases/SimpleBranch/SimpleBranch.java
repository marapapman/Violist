import LoggerLib.Logger;
class SimpleBranch {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="e";
	long time=System.currentTimeMillis();
	if(time % 2 ==0)
	{
		a+=a+"a";
		b+=a+b;
	}
	else{
	   	a+=a+"t";
		b+=a+b;
	}
	c+=b+c;
	Logger.reportString(c,"SimpleBranch");
	

    }
}

