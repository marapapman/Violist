package TestCases.Mix;
import LoggerLib.Logger;
class SingleLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	String c="Ac";
	String e="Ae";
	for(int i=0;i<3;i++)
	{
		long time=(int)(Math.random()*100); 
		if(time % 2 ==0)
		{
			a=a.replaceAll("A","Ax");
			a=a.replaceFirst("A","Ap");
			a=a+" ";
			a=a.trim();
			a=a.toUpperCase();
			a=a.toLowerCase();
			a="A"+a;
		}
		else{
	   		a=a.replaceAll("A","Ay");
			a=a.replaceFirst("A","Ap");
			a=a+" ";
			a=a.trim();
			a=a.toUpperCase();
			a=a.toLowerCase();
			a="A"+a;
		}
		Logger.reportString(a,"Mix.SingleLoop_Branch");
	}

	

    }
}

