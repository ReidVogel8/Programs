import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
public class UndirectedGraph {
	private HashMap<String, LinkedList<String>> movies_to_actors;
	private HashMap<String, LinkedList<Edge>> adjList;
	private HashSet<String> movies;
	private HashSet<String> all_actors; //number of vertices
	
	public UndirectedGraph() {
		this.adjList = new HashMap<String, LinkedList<Edge>>();
		this.movies_to_actors = new HashMap<String, LinkedList<String>>();
		this.movies = new HashSet<String>();
		this.all_actors = new HashSet<String>();
	}
	
	//HashMap<String Movie, String Actors>. Add an actor to a movie key
	public void addActor(String movie, String actor) {
		//adding all movies into HashSet
		movies.add(movie);
		
		// key/movie exists
		if(movies_to_actors.containsKey(movie)) {
			LinkedList<String> list = movies_to_actors.get(movie);
			list.add(actor);
		}
		
		// key/movie doesn't already exist
		else {
			LinkedList<String> list = new LinkedList<String>();
			list.add(actor);
			movies_to_actors.put(movie, list);
		}
	}
	
	
	//used to print out movies_to_actors HashMap
	public void printHashMap() {
		for(String movie : movies) {
			System.out.println(movie+": " + movies_to_actors.get(movie));
		}
	}
	
	//printing AdjList with overwritten toString method
	public void printAdjList() {
		for(String actor : all_actors) {
			System.out.println(actor + ": " + adjList.get(actor).toString());
			System.out.println("\n");
		}
	}
	
	//used to fill adjacencyList
	public void formGraph() {
		//go through movies_to_actors and add create Edge(vertex1, vertex2, movieName)
		//going through each movie
		for(String movie : movies) {
			LinkedList<String> actors = movies_to_actors.get(movie);
			//iterating through each actor in linkedList and creating edges
			for(int i = 0; i < actors.size(); i++) {
				for(int j = 0; j < actors.size(); j++) {	
					//get two actors in the LinkedList
					String actor1 = actors.get(i);
					String actor2 = actors.get(j);
					//making sure actor1 != actor2 or else the actor would be vertex1 and vertex2
					if(!actor1.equals(actor2)) {
						//add actors to HashSet for printing purpose in printAdjList()
						all_actors.add(actor1);
						all_actors.add(actor2);
						if(adjList.containsKey(actor1)) {
							//get LinkedList in key and add Edge between actor1 and actor2
							LinkedList<Edge> value = adjList.get(actor1);
							Edge connection = new Edge(actor1, actor2, movie);
							value.add(connection);
							adjList.put(actor1, value);
						}
						else {
							//create new LinkedList and add Edge between actor1 and actor2
							LinkedList<Edge> list = new LinkedList<Edge>();
							Edge connection = new Edge(actor1, actor2, movie);
							list.add(connection);
							adjList.put(actor1, list);
						}
					}
				}
			}
		}
	}
	
	//gets and returns all actors in graph
	public HashSet<String> getActors(){
		return this.all_actors;
	}
	
	//gets number of vertices
	public int getNumVertices() {
		return all_actors.size();
	}

	//returns all the edges in a LinkedList
	public LinkedList<Edge> getEdges() {
		LinkedList<Edge> edgeList = new LinkedList<Edge>();
		for(String actor : all_actors) {
			LinkedList<Edge> edges = adjList.get(actor);
			for(Edge edge : edges) {
				edgeList.add(edge);
			}
		}
		return edgeList;
	}
	
	//returns edges adjacent to vertex
	public LinkedList<Edge> getAdjacencyEdges(String vertex){
		return adjList.get(vertex);
	}
	
}
