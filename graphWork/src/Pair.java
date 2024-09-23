
public class Pair {
	
	private String first;
	private String parent;
	
	// used for adding into Queue in cycles  
	public Pair(String first, String parent) {
		this.first = first;
		this.parent = parent;
	}
	
	// returns the adjacent Actor
	public String getFirst() {
		return this.first;
	}
	
	// returns previous actor
	public String getParent() {
		return this.parent;
	}
}
