import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;

public class kruskalsAlgorithm {

	private UndirectedGraph graph;
	private HashSet<Edge> mst = new HashSet<Edge>();


	public kruskalsAlgorithm(UndirectedGraph graph) {
		this.graph = graph;
	}

	
	public void getInformation() {
		int counter = 0;
		//ccm == connectedComponentMarker
		HashMap<String,Integer> ccm = new HashMap<String,Integer>();
		for(String actor : graph.getActors()) {
			ccm.put(actor, counter);
			counter++;
		}
		//get the set of edges
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>();
		for(Edge edge : graph.getEdges()) {
			edgeQueue.add(edge);
		}
		while(!edgeQueue.isEmpty()) {
			Edge edge = edgeQueue.poll();
			//checking to make sure adding edge doesn't create loop
			if(ccm.get(edge.getVertices().get(0)) != ccm.get(edge.getVertices().get(1))) {
				mst.add(edge);
				int newMarker = ccm.get(edge.getVertices().get(0));
				int oldMarker = ccm.get(edge.getVertices().get(1));
				//updating marker
				for(String checkEdge : graph.getActors()) {
					if(ccm.get(checkEdge) == oldMarker) {
						ccm.put(checkEdge, newMarker);
					}
				}
			}
		}
	}
	
	//will be run after getInformation and print out results of MST
	public void printInformation() {
		HashSet<String> movies = new HashSet<String>();
		System.out.println("Edges in MST:");
		System.out.println("---------------------");
		int counter = 1;
		int movieCounter = 1;
		//print all the edges
		for(Edge edge : mst) {
			System.out.println(counter+". "+ edge);
			movies.add(edge.getMovie());
			counter++;
		}
		System.out.print("\n");
		System.out.println("List of movies to watch that covers all 30 Actors:");
		System.out.println("---------------------");
		//printing the all the movies needed to watch
		for(String movie : movies) {
			System.out.println(movieCounter + ". " + movie);
			movieCounter++;
		}
		System.out.print("\n");
	}
}
