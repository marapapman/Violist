package TestCases.Mix;
import LoggerLib.Logger;
class NestedLoop_Branch {
    public static void main(String[] args) {
	String a="Aa";
	String b="Ab";
	for(int i=0;i<2;i++)
	{
		a=b.replaceAll("A","Ax");
		Logger.reportString(a,"Mix.NestedLoop_Branch");

		for(int j=0;j<2;j++)
                {
			long time=(int)(Math.random()*100); 
			if(time % 2 ==0)
			{
				b=b.replaceAll("A","Ay");
				b=b.replaceFirst("A","Ap");
				b=b+" ";
				b=b.trim();
				b=b.toUpperCase();
				b=b.toLowerCase();
				b="A"+b;
			}
			else{
				b=b.replaceAll("A","Az");
				b=b.replaceFirst("A","Ap");
				b=b+" ";
				b=b.trim();
				b=b.toUpperCase();
				b=b.toLowerCase();
				b="A"+b;
			}
		}
	}

    }
}

