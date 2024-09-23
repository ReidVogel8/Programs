
import java.util.LinkedList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class DijkstrasAlgorithm {

	private UndirectedGraph graph;
	private String start;//starting actor
	private HashMap<String, Integer> distance; 
	private HashMap<String,Edge> previous;

	public DijkstrasAlgorithm(String start,UndirectedGraph graph) {
		this.graph = graph;
		this.start = start;
		this.distance = new HashMap<String, Integer>();
		this.previous = new HashMap<String, Edge>();
		
		for(String name : graph.getActors()) {
			Integer value = Integer.MAX_VALUE;
			distance.put(name, value);
		}
		//need a starting point can't all be Integer.Max_Value
		distance.put(start, 0);
		
		
	}
	
	//computes the shortestPath for all nodes
	public void shortestPath() {
		PriorityQueue<PriorityVertex> visitQueue = new PriorityQueue<PriorityVertex>();
		visitQueue.add(new PriorityVertex(start,0));
		while(!visitQueue.isEmpty()) {
			PriorityVertex vertex = visitQueue.poll();
			for(Edge edge : graph.getAdjacencyEdges(vertex.getVertex())) {
				String neighbor = edge.getDest();
				if(((distance.get(vertex.getVertex())) + edge.getWeight()) <  distance.get(neighbor)) {
					//found best estimate so need to update
					int new_dist = distance.get(vertex.getVertex()) + edge.getWeight();
					distance.put(neighbor, new_dist);
					previous.put(neighbor,edge);
					visitQueue.remove(new PriorityVertex(neighbor, 0));
					visitQueue.add(new PriorityVertex(neighbor,distance.get(neighbor)));
				}
			}
		}
	}

	
	//returns the shortest path from start to destination
	public LinkedList<Edge> getPathTo(String destination) {
		if(!hasPath(destination)) {
			//returning an empty LinkedList since there is no path
			return new LinkedList<Edge>();
		}
		else {
			//return LinkedList with shortest Path
			LinkedList<Edge> solution = new LinkedList<Edge>();
			for(Edge edge = previous.get(destination); edge != null; edge = previous.get(edge.getSource())) {
				solution.addFirst(edge);
			}
			return solution;
		}
	}
	
	//used to print the items in the shortestPath
	public void printPath(LinkedList<Edge> list, String destination) {
		if(!list.isEmpty()) {
			for(Edge item : list) {
				System.out.println(item.getVertices().get(0)+" acted with "+item.getVertices().get(1)+ " in "+ item.getMovie());
			}
			System.out.println("Number of hops: "+list.size());
		}
		else {
			System.out.println("Path does not exit to "+ destination);
		}
	}
	
	//returns if there is a path to a certain destination
	private boolean hasPath(String destination) {
		//if not an actor will not be found in the HashMap
		if((distance.get(destination) == null)) {
			return false;
		}
		else {
			return distance.get(destination) < Integer.MAX_VALUE;
		}
	}
	
}
