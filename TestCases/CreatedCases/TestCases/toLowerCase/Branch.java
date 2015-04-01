package TestCases.toLowerCase;
import LoggerLib.Logger;
class Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	long time=(int)(Math.random()*100); 
	if(time % 2 ==0)
	{
		a=a.toLowerCase();
	}
	else{
		a=a.toLowerCase();
	}
	Logger.reportString(a,"toLowerCase.Branch");
	

    }
}

