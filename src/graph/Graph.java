package graph;
import java.util.List;

public interface Graph<V> {

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
	boolean setEdgeWeight(V v1, V v2, float weight);

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
	boolean removeVertex(V v);

	List<V> getVertices();

	/**
	 * 
	 * @param v1
	 * @param v2
	 */
	float getEdgeWeight(V v1, V v2);

}