package graph;

public interface Graph<V> {

	/**
	 * 
	 * @param vertex
	 */
	void addVertex(V vertex);

	/**
	 * 
	 * @param v1
	 * @param v2
	 */
	void addEdge(V v1, V v2);

	/**
	 * 
	 * @param v1
	 * @param v2
	 * @param weight
	 */
	void setEdgeWeight(V v1, V v2, float weight);

	/**
	 * 
	 * @param v1
	 * @param v2
	 */
	void removeEdge(V v1, V v2);

	/**
	 * 
	 * @param v
	 */
	void removeVertex(V v);

	V[] getVertices();

	/**
	 * 
	 * @param v1
	 * @param v2
	 */
	float getEdgeWeight(V v1, V v2);

}