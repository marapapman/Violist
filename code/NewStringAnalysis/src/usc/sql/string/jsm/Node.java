package usc.sql.string.jsm;


public class Node {
	private String asmLn;
	private int srcLn;
	public Node(String asmLn, int srcLine){
		setASMLine(asmLn);
		setSrcLine(srcLine);
	}
	public Node(String asmLn){
		setASMLine(asmLn);
	}
	public void setSrcLine(int srcLine){
		srcLn = srcLine;
	}
	public void setASMLine(String asmLine){
		asmLn = asmLine;
	}
	public int getSrcLine(){
		return srcLn;
	}
	public String getASMLine(){
		return asmLn;
	}
}
