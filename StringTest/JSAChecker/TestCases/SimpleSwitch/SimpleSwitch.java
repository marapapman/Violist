import LoggerLib.Logger;
class SimpleSwitch {
    public static void main(String[] args) {
	String a="a";
	String b="b";
	String c="c";
	String e="e";
	long time=System.currentTimeMillis();
	int s=(int)(time%4);

	switch(s){
		case 0: a+=a+"0";b+=a+b;break;
		case 1: a+=a+"1";b+=a+b;break;
		case 2: a+=a+"2";b+=a+b;break;
		case 3: a+=a+"3";b+=a+b;break;
	}
	c+=b+c;
	Logger.reportString(c,"SimpleSwitch");
	

    }
}

