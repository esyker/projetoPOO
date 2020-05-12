package graph;
import java.util.List;

public interface WeightedGraph<V> {

	
	/**
	 * @param vertex New vertex to add to the graph
	 * @return true if vertex was added , false otherwise
	 */
	boolean addVertex(V vertex);

	/**
	 * @param v1 Vertex 1 to connect
	 * @param v2 Vertex 2 to connect
	 * @return true if edge was added, false otherwise
	 */
	boolean addEdge(V v1, V v2);

	
	/**
	 * @param v1 Vertex1 to connect
	 * @param v2 Vertex2 to connect
	 * @param weight weight of the edge
	 * @return true if edge was added, false otherwise
	 */
	boolean setEdgeWeight(V v1, V v2, double weight);
	
	
	/**
	 * @param v1 Vertex 1 to disconnect
	 * @param v2 Vertex 2 to disconnect
	 * @return true if vertex was removed, false otherwise
	 */
	boolean removeEdge(V v1, V v2);
	
	
	/**
	 * @return a list with the vertices from the graph
	 */
	List<V> getVertices();

	/**
	 * @param v1 vertex 1 
	 * @param v2 vertex 2
	 * @return the value of the edge between the two vertices
	 */
	double getEdgeWeight(V v1, V v2);
	
	
	/**
	 * @return true if the graph is cyclic, false if acyclic
	 */
	boolean isGraphCyclic();
	
	/**
	 * @return true if the graph is acyclic and all the vertices have a connection,
	 * false otherwise
	 */
	boolean isTree();
	
	
	/**
	 * @return number of vertices in the graph
	 */
	int getNumbVertices();
	
}