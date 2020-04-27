package graph;

public abstract class AbstractUndirectedWeightedGraph<V> implements Graph {

	protected float[][] weight_matrix;
	protected List<V> vertices;

}