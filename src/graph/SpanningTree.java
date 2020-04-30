package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import directedTree.TreeNode;

public class SpanningTree<V> extends AbstractUndirectedWeightedGraph<V> {

	private int currSize;
	private int max_numb_vertices;
	
	public SpanningTree(int max_numb_vertices) {
		super();
		this.weight_matrix=new float[max_numb_vertices][max_numb_vertices];
		this.max_numb_vertices=max_numb_vertices;
		this.vertices= new LinkedList<V>();
		this.currSize=0;
	}

	@Override
	public boolean addVertex(V vertex) {

		if(currSize==this.max_numb_vertices||this.vertices.contains(vertex))
			return false;

		this.vertices.add(vertex);
		this.currSize++;
		return true;
	}
	
	@Override
	public boolean addEdge(V v1, V v2) {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1&&index1!=index2) {
			this.weight_matrix[index1][index2]=1;
			this.weight_matrix[index2][index1]=1;
			return true;
		}
		return false;
	}
	
	
	@Override
	public boolean setEdgeWeight(V v1, V v2, float weight) {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1 && index1!=index2) {
			this.weight_matrix[index1][index2]=weight;
			this.weight_matrix[index2][index1]=weight;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean removeEdge(V v1, V v2) {
		
		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1) {
			this.weight_matrix[index1][index2]=0;
			this.weight_matrix[index2][index1]=0;
			return true;
		}
		return false;
	}

	@Override
	public boolean removeVertex(Object v) {
		return false;
	}

	@Override
	public List<V> getVertices() {
		
		return this.vertices;
	}

	@Override
	public float getEdgeWeight(V v1, V v2) {
		
		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		
		if(index1!=-1 && index2!=-1)
			return (this.weight_matrix[index1][index2]);
		
		return -1;
	}
	
	@Override
	public String toString() {
		
		String to_str= new String();
		for(int i=0;i<this.currSize;i++) {
			for(int j=0;j<this.currSize;j++)
			{
				to_str+=(this.getWeightsMatrix()[i][j]+ " ");
			}
			to_str+="\n";
		}
		
		to_str+=("Vertices:"+this.getVertices()+"\n");
		return to_str;
	}
	
	public V getVertexFromIndex(int index) {
		return this.vertices.get(index);
	}
	
	public int getNumbVertices() {
		return this.currSize;
	}
	
	public float [][] getWeightsMatrix(){
		return this.weight_matrix;
	}
	
    private int findDepth(int i, int previus) {
    	int aux =i;
		int res=0;
		int res1=0;
		

		for (int j=0; j < currSize; j++)
		{
			if (weight_matrix[aux][j]!=0 && j!=previus)
			{
				res1=findDepth(j, aux);
				res1++;
				if (res1>res)res=res1;	
			}
		}
		
		return res;
	}

    public int findHeight() { 
    	int i;

        int depth[] = new int[currSize]; 
        for (i = 0; i < currSize; i++) { 
            depth[i] = 0; 
        } 
  
        for (i=0; i < currSize; i++)
        {
        	depth[i]=findDepth(i,0); 
        }
        int index=0;
        int ht = depth[0]; 
        for (i = 1; i < currSize; i++) { 
            if (ht < depth[i]) { 
                ht = depth[i]; 
                index=i;
            } 
        } 
        return index; 
    }
	
	

}