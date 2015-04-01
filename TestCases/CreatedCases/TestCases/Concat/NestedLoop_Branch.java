package TestCases.Concat;
import LoggerLib.Logger;
class NestedLoop_Branch {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	for(int i=0;i<2;i++)
	{
		a+=a+b+"a";
		Logger.reportString(a,"Concat.NestedLoop_Branch");

		for(int j=0;j<2;j++)
                {
			long time=(int)(Math.random()*100); 
			if(time % 2 ==0)
			{
				b=b+"b";
			}
			else{
		   		b=b+"t";
			}
		}
	}

    }
}

