package stringlist;

public class Node {

	protected String s;
	protected Node Nextstring;
	
	public Node(String s){
		this.s = s;
		this.Nextstring = null;
	}
	
	@Override
	public String toString() {
		return "Node [s=" + s + ", Nextstring=" + Nextstring + "]";
	}
	
}
