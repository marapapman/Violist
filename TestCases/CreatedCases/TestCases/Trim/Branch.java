package TestCases.Trim;
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
		a=a.trim();
	}
	else{
		a=a.trim();
	}
	Logger.reportString(a,"Trim.Branch");
	

    }
}

