import LoggerLib.Logger;
class SwitchLoop {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="e";
	for(int i=0;i<3;i++)
	{
		long time=System.currentTimeMillis();
		int s=(int)(time%4);

		switch(s){
			case 0: a+=a+"0";break;
			case 1: a+=a+"1";break;
			case 2: a+=a+"2";break;
			case 3: a+=a+"3";break;
		}
		c+=a+c;
		Logger.reportString(c,"SwitchLoop");
	}
	

    }
}

