package TestCases.Mix;
import LoggerLib.Logger;
class InterProcedural {
    public static String addA(String v)
	{
		String a=v;
		a=a.replaceAll("A","Ax");
		a=a.replaceFirst("A","Ap");
		a=a+" ";
		a=a.trim();
		a=a.toUpperCase();
		a=a.toLowerCase();
		a="A"+a;
		return a;
	}
    public static void main(String[] args) {
	String a="Aa";
	String b=addA(a);
	b=addA("Ab");
	Logger.reportString(b,"Mix.InterProcedural");

    }
}

