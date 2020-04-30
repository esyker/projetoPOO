package mai;
import graph.DenseUndirectedWeightedGraph;
import graph.SpanningTree;
import graph.PrimMaxSpanningTree;

public class Main {
	
	public static void main(String[] args){
		DenseUndirectedWeightedGraph<Integer> graph = new DenseUndirectedWeightedGraph<Integer>(5);
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.setEdgeWeight(0,1,2);
		graph.setEdgeWeight(0,3,6);
		graph.setEdgeWeight(1,2,3);
		graph.setEdgeWeight(1,3,8);
		graph.setEdgeWeight(1,4,5);
		graph.setEdgeWeight(2,4,7);
		graph.setEdgeWeight(3,4,9);
		
		/*
		float[][]graph_representation=graph.getWeightsMatrix();
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++)
			{
				System.out.print(graph_representation[i][j]+ " ");
			}
			System.out.println("\n");
		}*/

		System.out.println(graph);
		
		PrimMaxSpanningTree<Integer> tree =new PrimMaxSpanningTree<Integer>(graph);
		SpanningTree<Integer> spanningTree=tree.getSpanningTree();
		System.out.print(spanningTree.toString());
		System.out.println(spanningTree.findHeight());
		
	}
	
}
