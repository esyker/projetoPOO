package graph;
import java.util.List;

public interface WeightedGraph<V> {

	/**
	 * 
	 * @param vertex
	 */
	boolean addVertex(V vertex);

	/**
	 * 
	 * @param v1
	 * @param v2
	 */
	boolean addEdge(V v1, V v2);

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param weight
	 */
	boolean setEdgeWeight(V v1, V v2, double weight);

	/**
	 * 
	 * @param v1
	 * @param v2
	 */
	boolean removeEdge(V v1, V v2);

	/**
	 * 
	 * @param v
	 */
	//boolean removeVertex(V v);

	List<V> getVertices();

	/**
	 * 
	 * @param v1
	 * @param v2
	 */
	double getEdgeWeight(V v1, V v2);
	
	boolean isGraphCyclic();
	
	boolean isTree();
	
	int getNumbVertices();
	
}