import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
public class Cycles {
	
	private UndirectedGraph graph;
	//actors and true/false if visited
	private HashMap<String, Boolean> visited;
	private Queue<Pair> q = new LinkedList<>();
	private String vertex;
	
	public Cycles(UndirectedGraph graph, String vertex) {
		this.graph = graph;
		this.visited = new HashMap<String, Boolean>();
		this.vertex = vertex;
		
		//fills visited HashMap with false
		for(String actor : graph.getActors()) {
			visited.put(actor, false);
		}
		visited.put(vertex, true);
		
	}
	
	
	//will use BFS to check for a cycle in the graph
	//will return true if a cycle occurs and false if no cycle occurs
	
	//This should always return the same result no matter the starting vertex ->
	//since you are always able to find a cycle in the actors.txt graph
	public boolean checkCycles() {
		//create a new pair object, pair[0] = adjacent actor and pair[1] = previous actor 
		q.add(new Pair(vertex,"-1"));
		while(!q.isEmpty()) {
			String node = q.peek().getFirst();
			String parent = q.peek().getParent();
			//pop off queue
			q.remove();
			for(Edge edge : graph.getAdjacencyEdges(node)) {
				//gets adjacent actor
				String adjActor = edge.getVertices().get(1);
				if(visited.get(adjActor) == false) {
					//updates visited HashMap
					visited.put(adjActor, true);
					q.add(new Pair(adjActor, node));
				}
				//make sure you are comparing two different actors since undirected graph
				else if(!(parent.equals(adjActor))) {
					//cycle found
					return true;
				}
			}
		}
		// no cycle found
		return false;
	}
	
}
