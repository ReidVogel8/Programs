import java.util.LinkedList;

public class Edge implements Comparable<Edge>{
	
	private int weight;
	private String movie;
	private String v1;
	private String v2;

	public Edge(String vertex1, String vertex2, String movieName) {
		this.weight = 1;
		this.movie = movieName;
		this.v1 = vertex1;
		this.v2 = vertex2;
	}

	//returns edge weight
	public int getWeight() {
		return this.weight;
	}
	
	public String toString() {
		return(v1 +" - " + v2 + " (" + movie + ")");
	}

	//sorting priority queue from least to greatest off edge weights
	@Override
	public int compareTo(Edge otherEdge) {
		if(weight < otherEdge.weight) {
			return -1;
		}
		else if(weight > otherEdge.weight) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public LinkedList<String> getVertices(){
		//first vertex will be first item in linked list at index 0
		LinkedList<String> list = new LinkedList<String>();
		list.add(v1);
		list.add(v2);
		return list;
	}
	
	// returns string movie
	public String getMovie() {
		return this.movie;
	}
	
	//return sourceVertex
	public String getSource() {
		return v1;
	}
	
	//returns destination vertex
	public String getDest() {
		return v2;
	}
	
}

