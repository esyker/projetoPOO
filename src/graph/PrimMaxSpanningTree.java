package graph;

public class PrimMaxSpanningTree<V> implements SpanningTreeAlgorithm<V> {
	
	private int numb_vertices;
	protected DenseUndirectedWeightedGraph<V> graph;
	/**
	 * 
	 * @param g
	 */
	public PrimMaxSpanningTree(DenseUndirectedWeightedGraph<V> g) {
		this.graph=g;
		this.numb_vertices=this.graph.getNumbVertecis();
	}
	
	 // A utility function to find the vertex with minimum key 
	 // value, from the set of vertices not yet included in MST 
	 int maxKey(float key[], Boolean mstSet[]) 
	 { 
	     // Initialize max value 
	     float max = -Float.MAX_VALUE;
	     int max_index = -1; 
	     
	     System.out.println(max);
	     
	     for (int v = 0; v <this.numb_vertices; v++) 
	         if (mstSet[v] == false && key[v] > max) { 
	             max = key[v]; 
	             max_index = v; 
	         } 

	     return max_index; 
	 } 
	 
	 void printMST(int parent[], float graph[][]) 
     { 
         System.out.println("Edge \tWeight"); 
         for (int i = 1; i < this.numb_vertices; i++) 
             System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]); 
     } 
	 
	 // Function to construct and print MST for a graph represented 
	 // using adjacency matrix representation 
	 public void primMST() 
	 { 		
		 float [][] g=graph.getWeightsMatrix();
	     // Array to store constructed MST 
	     int parent[] = new int[graph.getNumbVertecis()]; 

	     // Key values used to pick minimum weight edge in cut 
	     float key[] = new float[graph.getNumbVertecis()]; 

	     // To represent set of vertices not yet included in MST 
	     Boolean mstSet[] = new Boolean[graph.getNumbVertecis()]; 

	     // Initialize all keys as -INFINITE 
	     for (int i = 0; i < graph.getNumbVertecis(); i++) { 
	         key[i] = -Float.MAX_VALUE; 
	         mstSet[i] = false; 
	     } 

	     // Always include first 1st vertex in MST. 
	     key[0] = 0; // Make key 0 so that this vertex is 
	     // picked as first vertex 
	     parent[0] = -1; // First node is always root of MST 

	     // The MST will have getNumbVertecis vertices 
	     for (int count = 0; count < graph.getNumbVertecis() - 1; count++) { 
	         // Pick thd minimum key vertex from the set of vertices 
	         // not yet included in MST 
	         int u = maxKey(key, mstSet); 
	         
	         System.out.println(u);
	         // Add the picked vertex to the MST Set 
	         mstSet[u] = true; 

	         // Update key value and parent index of the adjacent 
	         // vertices of the picked vertex. Consider only those 
	         // vertices which are not yet included in MST 
	         for (int v = 0; v < graph.getNumbVertecis(); v++) 

	             // graph[u][v] is non zero only for adjacent vertices of m 
	             // mstSet[v] is false for vertices not yet included in MST 
	             // Update the key only if graph[u][v] is bigger than key[v] 
	             if (g[u][v] > 0 && mstSet[v] == false && g[u][v] > key[v]) { 
	                 parent[v] = u; 
	                 key[v] = g[u][v]; 
	             } 
	     }
	     
	     // print the constructed MST 
	     printMST(parent, g); 
	 } 
	 
	@Override
	public SpanningTree<V> getSpanningTree() {
		// TODO Auto-generated method stub
		return null;
	}

}


/*
 
//A Java program for Prim's Minimum Spanning Tree (MST) algorithm. 
//The program is for adjacency matrix representation of the graph 

import java.util.*; 
import java.lang.*; 
import java.io.*; 

class MST { 
 // Number of vertices in the graph 
 private static final int V = 5; 


 // A utility function to print the constructed MST stored in 
 // parent[] 
 void printMST(int parent[], int graph[][]) 
 { 
     System.out.println("Edge \tWeight"); 
     for (int i = 1; i < V; i++) 
         System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]); 
 } 

 // Function to construct and print MST for a graph represented 
 // using adjacency matrix representation 
 void primMST(int graph[][]) 
 { 
     // Array to store constructed MST 
     int parent[] = new int[V]; 

     // Key values used to pick minimum weight edge in cut 
     int key[] = new int[V]; 

     // To represent set of vertices not yet included in MST 
     Boolean mstSet[] = new Boolean[V]; 

     // Initialize all keys as INFINITE 
     for (int i = 0; i < V; i++) { 
         key[i] = Integer.MAX_VALUE; 
         mstSet[i] = false; 
     } 

     // Always include first 1st vertex in MST. 
     key[0] = 0; // Make key 0 so that this vertex is 
     // picked as first vertex 
     parent[0] = -1; // First node is always root of MST 

     // The MST will have V vertices 
     for (int count = 0; count < V - 1; count++) { 
         // Pick thd minimum key vertex from the set of vertices 
         // not yet included in MST 
         int u = minKey(key, mstSet); 

         // Add the picked vertex to the MST Set 
         mstSet[u] = true; 

         // Update key value and parent index of the adjacent 
         // vertices of the picked vertex. Consider only those 
         // vertices which are not yet included in MST 
         for (int v = 0; v < V; v++) 

             // graph[u][v] is non zero only for adjacent vertices of m 
             // mstSet[v] is false for vertices not yet included in MST 
             // Update the key only if graph[u][v] is smaller than key[v] 
             if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
                 parent[v] = u; 
                 key[v] = graph[u][v]; 
             } 
     } 

     // print the constructed MST 
     printMST(parent, graph); 
 } 

 public static void main(String[] args) 
 { 

     MST t = new MST(); 
     int graph[][] = new int[][] { { 0, 2, 0, 6, 0 }, 
                                   { 2, 0, 3, 8, 5 }, 
                                   { 0, 3, 0, 0, 7 }, 
                                   { 6, 8, 0, 0, 9 }, 
                                   { 0, 5, 7, 9, 0 } }; 

     // Print the solution 
     t.primMST(graph); 
 } 
} 
//This code is contributed by Aakash Hasija 

*/