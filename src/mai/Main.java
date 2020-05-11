package mai;
import directedTree.DefaultDirectedTree;
import graph.DenseUndirectedWeightedGraph;
import graph.SpanningTree;
import graph.PrimMaxSpanningTree;

public class Main {
	
	public static void main(String[] args){
		DenseUndirectedWeightedGraph<Integer> graph = new DenseUndirectedWeightedGraph<Integer>(8);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addVertex(4);
		graph.addVertex(5);
		graph.addVertex(6);
		graph.addVertex(7);
		graph.addVertex(8);
		graph.setEdgeWeight(3,7,1);
		graph.setEdgeWeight(7,4,1);
		graph.setEdgeWeight(7,6,1);
		graph.setEdgeWeight(4,5,1);
		graph.setEdgeWeight(5,8,1);
		graph.setEdgeWeight(8,2,1);
		graph.setEdgeWeight(2,1,1);
		
		/*
		float[][]graph_representation=graph.getWeightsMatrix();
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++)
			{
				System.out.print(graph_representation[i][j]+ " ");
			}
			System.out.println("\n");
		}*/

		//System.out.println(graph);
		
		PrimMaxSpanningTree<Integer> tree =new PrimMaxSpanningTree<Integer>(graph);
		SpanningTree<Integer> spanningTree=tree.getSpanningTree();
		System.out.print(spanningTree);
		/*
		System.out.print(spanningTree.toString());
		System.out.println(spanningTree.findHeight());

		System.out.print(spanningTree);
		System.out.println(spanningTree.isTree());
		System.out.println(graph.isTree());
		*/
		DefaultDirectedTree<Integer> tree0 =  new DefaultDirectedTree<Integer>();
	    
		tree0.loadFromSpanningTree(spanningTree);
		/*
		System.out.println(tree0.getParent(1));
		System.out.println(tree0.getParent(3));
		System.out.println(tree0.getParent(0));
		System.out.println(tree0.getParent(4));
		System.out.println(tree0.getParent(2)+"\n");
		
		System.out.println(tree0.getChildren(1));
		System.out.println(tree0.getChildren(3));
		System.out.println(tree0.getChildren(0));
		System.out.println(tree0.getChildren(4));
		System.out.println(tree0.getChildren(2));
		*/
		
		System.out.println(tree0);
		
		
	}
	
}
