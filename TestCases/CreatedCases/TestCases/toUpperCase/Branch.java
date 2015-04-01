package TestCases.toUpperCase;
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
		a=a.toUpperCase();
	}
	else{
		a=a.toUpperCase();
	}
	Logger.reportString(a,"toUpperCase.Branch");
	

    }
}

