package TestCases.ReplaceFirst;
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
		a=a.replaceFirst("A","Ap");
	}
	else{
		a=a.replaceFirst("A","Aq");
	}
	Logger.reportString(a,"ReplaceFirst.Branch");
	

    }
}
