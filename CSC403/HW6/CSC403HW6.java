package HW6;  // change this if you want
import stdlib.*;
import algs13.Queue;
import algs41.BreadthFirstPaths;
import algs41.CC;
/*
 * Class: CSC403
 * Author: Harry Chen
 * HW6
 */

//  Version 1.0 
//
//This is basically exercise 4.1.16 from the text
//   see the exercise for definitions and hints
//
//  The provided structure follows the design pattern illustrated
//  by the examples in 4.1
//
// you're free to add instance variables and other methods
// you'll probably want to add in code to support bfs or dfs; 
//     feel free to grab and adapt these from the text and/or algs41
//  you might find queue or stack to be useful, if so I'd suggest you use
//  the versions from algs13
//
//  you shouldn't need (or use) anything else, ask me if not sure

// please document your code to explain your approach
// If I can't follow what you're doing, you will get reduced (or no) credit


import algs41.Graph;
import algs41.GraphGenerator;
public class CSC403HW6 {
	int[] eccentricity;  // the eccentricity of each vertex
	int diameter;        // the diameter of the graph
	int radius;	         // the radius of the graph
	boolean [] isAcenter;       // the set of all 

	// Constructor can be linear in G.V() * G.E()
	public CSC403HW6(Graph G) {

		this.eccentricity = new int[G.V()];
		this.isAcenter = new boolean[G.V()];
		int diameter = Integer.MIN_VALUE;
		int radius = Integer.MAX_VALUE;

		// If G.V()==0, then these are the correct values.
		// If G is not connected, you should throw a new IllegalArgumentException()
		// I suggest that you first get it to work for a connected graph
		// This will require that you traverse the graph starting from some node (say 0)
		// You can then adjust your code so that it throws an exception in the case that all nodes are not visited

		// TODO
		for(int v = 0; v < G.V(); v++) {
			// Initiate BFP object to count everynode's shortest path
			BreadthFirstPaths shortestpath = new BreadthFirstPaths(G,v);
			
			//Initiate CC class to see whether the graph is connected
			CC component = new CC(G);
			
			//If the graph has more than 1 component which mean it's not a connected graph
			if(component.count() != 1) {
				throw new IllegalArgumentException("The graph is not connected");
			}
			int maxshortestpath = 0;
			
			// for each node, compute the distance between it and every other node in the graph
			for(int j = 0; j<G.V(); j++) {
				int tmp = shortestpath.distTo(j);
				//find the max shortest path which is the eccentricity of the node
				if(tmp> maxshortestpath) {
					maxshortestpath = tmp;
				}
			}
			this.eccentricity[v] = maxshortestpath;
		}
		
		// put each node's eccentricity into a queue, trying to find the largest and smallest value as the diameter and radius of the graph
		Queue<Integer> q = new Queue<>();
		for(int v : eccentricity) {
			q.enqueue(v);
		}
		while(!q.isEmpty()) {
			int tmp = q.dequeue();
			if(tmp < radius) {
				radius = tmp;
			}
			else if(tmp > diameter){
				diameter = tmp;
			}
		}
		
		this.diameter = diameter;
		this.radius   = radius;
		
		//if a node's eccentricity equals to the radius of the graph, than it is the center node.
		for(int v = 0; v < G.V(); v++) {
			this.isAcenter[v] = (this.eccentricity[v] == this.radius);
		}
		
		// compute the eccentricity of each vertex, the diameter & radius 

	}

	// Do not change the following constant time methods
	public int eccentricity(int v) { return eccentricity[v]; }
	public int diameter()          { return diameter; }
	public int radius()            { return radius; }
	public boolean isCenter(int v) { return eccentricity[v] == radius; }

	public static void main(String[] args) {
		// comment in/out for testing
//		Graph G = new Graph(new In("data/tinyG.txt")); // this is non-connected -- should throw an exception
//		Graph G = GraphGenerator.connected (10, 20, 2); // Randoms non-connected graph -- should throw an exception

//		Graph G = new Graph(new In("data/tinyCG.txt")); // diameter=2, radius=2, every node is a center
//		Graph G = GraphGenerator.binaryTree (10); // A complete binary tree
		Graph G = GraphGenerator.path (10); // A path -- diameter=V-1
		//Graph G = GraphGenerator.connected (20, 400); // Random connected graph

		StdOut.println(G);
		G.toGraphviz ("g.png");  // comment out if you don't have graphViz installed

		CSC403HW6 edrc = new CSC403HW6(G);
		for (int v = 0; v < G.V(); v++)
			StdOut.format ("eccentricity of %d: %d\n", v, edrc.eccentricity (v));
		StdOut.format ("diameter=%d, radius=%d\n", edrc.diameter(), edrc.radius() );
		for (int i = 0; i < G.V(); i++) {
			if ( edrc.isCenter(i))
			StdOut.format ("center=%d\n", i);
		}
	}
}
