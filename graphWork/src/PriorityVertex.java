
public class PriorityVertex implements Comparable<PriorityVertex> {
	private String vertex;
	private int cost;
	
	public PriorityVertex(String vertex, int d) {
		this.vertex = vertex;
		this.cost = d;
	}
	
	public String getVertex() {
		return this.vertex;
	}
	
	public int getDistance() {
		return this.cost;
	}

	@Override
	public int compareTo(PriorityVertex other) {
		int num = Integer.valueOf(cost).compareTo(other.cost);
		return num;
	}
	
	public boolean equals(Object obj) {
		if(!(obj instanceof PriorityVertex)) {
			return false;
		}
		PriorityVertex other = (PriorityVertex) obj;
		if(vertex == other.vertex) {
			return true;
		}
		else {
			return false;
		}
	}
		
}
