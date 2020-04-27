package graph;

import java.util.List;

public abstract class AbstractUndirectedWeightedGraph<V> implements Graph<V> {

	protected float[][] weight_matrix;
	protected List<V> vertices;

}