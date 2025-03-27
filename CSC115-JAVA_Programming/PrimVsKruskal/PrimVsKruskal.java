/* PrimVsKruskal.java
   CSC 226 - Fall 2023
   Assignment 3 - Prim MST versus Kruskal MST Template
   
   The file includes the "import edu.princeton.cs.algs4.*;" so that you can use
   any of the code in the algs4.jar file. You should be able to compile your program
   with the command
   
	javac -cp .;algs4.jar PrimVsKruskal.java
	
   To conveniently test the algorithm with a large input, create a text file
   containing a test graphs (in the format described below) and run
   the program with
   
	java -cp .;algs4.jar PrimVsKruskal file.txt
	
   where file.txt is replaced by the name of the text file. Note: different operating systems have different commands.
   You should all know how to run the algs4.jar file from lab work.
   
   The input consists of a graph (as an adjacency matrix) in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   Entry G[i][j] >= 0.0 of the adjacency matrix gives the weight (as type double) of the edge from 
   vertex i to vertex j (if G[i][j] is 0.0, then the edge does not exist).
   Note that since the graph is undirected, it is assumed that G[i][j]
   is always equal to G[j][i].
*/

 import edu.princeton.cs.algs4.*;
 import java.util.Scanner;
 import java.io.File;
 import java.util.Set;
 import java.util.HashSet;
 import java.util.List;
 import java.util.ArrayList;
 import java.util.Set;
 import java.util.HashSet; 
 import java.util.Comparator;
 import java.util.Arrays;
 import java.util.Collections;
 

 import edu.princeton.cs.algs4.Edge;
 import edu.princeton.cs.algs4.EdgeWeightedGraph;
 import edu.princeton.cs.algs4.PrimMST;
 import edu.princeton.cs.algs4.KruskalMST;
 import edu.princeton.cs.algs4.Queue;

//Do not change the name of the PrimVsKruskal class
public class PrimVsKruskal{

	/* PrimVsKruskal(G)
		Given an adjacency matrix for connected graph G, with no self-loops or parallel edges,
		determine if the minimum spanning tree of G found by Prim's algorithm is equal to 
		the minimum spanning tree of G found by Kruskal's algorithm.
		
		If G[i][j] == 0.0, there is no edge between vertex i and vertex j
		If G[i][j] > 0.0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
	
	private static boolean queueContains(Queue<Edge> queue, Edge edge) {
		// Iterate over all edges in the queue
		for (Edge e : queue) {
			// Check if the current edge is equal to the edge we're looking for
			if (e.equals(edge)) {
				return true; 
			}
		}
		return false; 
	}
	
	
	static boolean PrimVsKruskal(double[][] G) {
        
		

		int n = G.length;


		// Convert the adjacency matrix to an EdgeWeightedGraph
		EdgeWeightedGraph graph = new EdgeWeightedGraph(n);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) { // Avoid adding the same edge twice
				if (G[i][j] != 0.0) {
					Edge e = new Edge(i, j, G[i][j]);
					graph.addEdge(e);
				}
			}
		}

		// Run Prim's algorithm
		PrimMST prim = new PrimMST(graph);
		// Run Kruskal's algorithm
		KruskalMST kruskal = new KruskalMST(graph);
		
		
		
		
		
		

		// Gather all the edges from each method into queues
		Queue<Edge> primEdges = new Queue<>();
		for (Edge e : prim.edges()) {
			primEdges.enqueue(e);
		}

		Queue<Edge> kruskalEdges = new Queue<>();
		for (Edge e : kruskal.edges()) {
			kruskalEdges.enqueue(e);
		}

		// If the number of edges in each MST differs, they're not the same
		if (primEdges.size() != kruskalEdges.size()) {
			return false;
		}


	
		boolean pvk = true;
		
		
		for (Edge e : primEdges) {
			if (!queueContains(kruskalEdges, e)) { // Use the helper method here
				pvk = false;
				break;
			}
		}

		return pvk;
		
		
		
		
	
	
    }
	

		
	/* main()
	   Contains code to test the PrimVsKruskal function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below. 
	*/
   public static void main(String[] args) {
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int n = s.nextInt();
		double[][] G = new double[n][n];
		int valuesRead = 0;
		for (int i = 0; i < n && s.hasNextDouble(); i++){
			for (int j = 0; j < n && s.hasNextDouble(); j++){
				G[i][j] = s.nextDouble();
				if (i == j && G[i][j] != 0.0) {
					System.out.printf("Adjacency matrix contains self-loops.\n");
					return;
				}
				if (G[i][j] < 0.0) {
					System.out.printf("Adjacency matrix contains negative values.\n");
					return;
				}
				if (j < i && G[i][j] != G[j][i]) {
					System.out.printf("Adjacency matrix is not symmetric.\n");
					return;
				}
				valuesRead++;
			}
		}
		
		if (valuesRead < n*n){
			System.out.printf("Adjacency matrix for the graph contains too few values.\n");
			return;
		}	
		
        boolean pvk = PrimVsKruskal(G);
        System.out.printf("Does Prim MST = Kruskal MST? %b\n", pvk);
    }
}
