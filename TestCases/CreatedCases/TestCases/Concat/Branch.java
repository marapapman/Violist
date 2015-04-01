package TestCases.Concat;
import LoggerLib.Logger;
class Branch {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="e";
	long time=(int)(Math.random()*100); 
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
	Logger.reportString(c,"Concat.Branch");
	

    }
}

