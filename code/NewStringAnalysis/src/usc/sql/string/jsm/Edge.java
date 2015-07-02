package usc.sql.string.jsm;


public class Edge {
	private String from;
	private String to;
	public Edge(String from, String to){
		this.from = from;
		this.to = to;
	}
	public String getSrcNode(){
		return from;
	}
	public String getDestNode(){
		return to;
	}
}
