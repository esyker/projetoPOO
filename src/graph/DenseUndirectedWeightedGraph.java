package graph;

public class DenseUndirectedWeightedGraph<V> extends AbstractUndirectedWeightedGraph<V> {
	
	
	
	public DenseUndirectedWeightedGraph(int max_numb_vertices) {
		super(max_numb_vertices);
		
	}

	
	
	@Override
	public boolean addEdge(V v1, V v2) {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1) {
			this.weight_matrix[index1][index2]=1;
			this.weight_matrix[index2][index1]=1;
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean setEdgeWeight(V v1, V v2, double weight) {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1) {
			this.weight_matrix[index1][index2]=weight;
			this.weight_matrix[index2][index1]=weight;
			return true;
		}
		
		return false;
	}


	
}