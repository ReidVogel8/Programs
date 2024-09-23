
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Program3Demo {

	public static void main(String[] args) {
		
		UndirectedGraph graph = new UndirectedGraph();
		
		//reading actors.txt file
		File file = new File("actors.txt");
		try {
			//adding actors to their movies in HashMap<String,LinkedList<String>>
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String[] parse = sc.nextLine().split("\\|");
				//adding movie to key and actor as value
				graph.addActor(parse[1], parse[0]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//creating and printing the graph
		graph.formGraph();
		//graph.printAdjList();
		
		//creating class to find minimum spanning tree
		kruskalsAlgorithm mst = new kruskalsAlgorithm(graph);
		try (Scanner sc = new Scanner(System.in)) {
			boolean repeat = true;
			//menu
			while(repeat == true) {
				System.out.println("Enter your choice:");
				System.out.println("1. Print out MST Information");
				System.out.println("2. Find Shortest Path from one Actor to another");
				System.out.println("3. Check for a cycle in the graph");
				System.out.println("4. Exit");
				String input = sc.nextLine();
				if(input.equals("1")) {
					//minimum spanning tree
					mst.getInformation();
					mst.printInformation();
				}
				else if(input.equals("2")) {
					//shortest path
					System.out.println("Enter starting actor: ");
					String actor1 = sc.nextLine();
					System.out.println("Enter destination actor: ");
					String actor2 = sc.nextLine();
					if(graph.getActors().contains(actor1) && graph.getActors().contains(actor2)) {
						DijkstrasAlgorithm shortest = new DijkstrasAlgorithm(actor1, graph);
						shortest.shortestPath();
						LinkedList<Edge> output = shortest.getPathTo(actor2);
						shortest.printPath(output, actor2);
					}
					else {
						System.out.println("Enter valid actor names.");
					}
				}
				else if(input.equals("3")) {
					//detects if there is a cycle in the graph
					boolean next = false;
					while(next != true) {
						System.out.println("Enter an actor: ");
						String answer = sc.nextLine();
						//make sure input is a valid actor in graph
						if(graph.getActors().contains(answer)) {
							Cycles cycle = new Cycles(graph, answer);
							System.out.println("Is there a cycle: " + cycle.checkCycles()+ "\n");
							next = true;
							
						}
						else {
							System.out.println("Enter a valid actor.");
						}
					}
				}
				else if(input.equals("4")){
					//ends program
					repeat = false;
				}
				else {
					System.out.println("Enter valid option.");
				}
			}
			sc.close();
		}
	}

}
