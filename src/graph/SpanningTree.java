package graph;


public class SpanningTree<V> extends AbstractUndirectedWeightedGraph<V> {

	
	public SpanningTree(int max_numb_vertices) {
		super(max_numb_vertices);
	}


    
	
    @Override
	public boolean addEdge(V v1, V v2) {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		float temp=this.weight_matrix[index1][index2];
		
		if(index1!=-1 && index2!=-1&&index1!=index2) {//vertices exist
			this.weight_matrix[index1][index2]=1;
			this.weight_matrix[index2][index1]=1;
			if(!this.isGraphCyclic()&&this.currSize>2)//revert changes
			{	
				this.weight_matrix[index1][index2]=temp;
				this.weight_matrix[index2][index1]=temp;
				return false;
			}
			return true;
		}
		else 
			return false;
	}
    
	@Override
	public boolean setEdgeWeight(V v1, V v2, float weight) {

		int index1=this.vertices.indexOf(v1);
		int index2=this.vertices.indexOf(v2);
		float temp=this.weight_matrix[index1][index2];
		
		if(index1!=-1 && index2!=-1&&index1!=index2) {//vertices exist
			this.weight_matrix[index1][index2]=weight;
			this.weight_matrix[index2][index1]=weight;
			if(!this.isGraphCyclic()&&this.currSize>2)//revert changes
			{	
				this.weight_matrix[index1][index2]=temp;
				this.weight_matrix[index2][index1]=temp;
				return false;
			}
			return true;
		}
		else 
			return false;
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

/*


// A recursive function that uses visited[] and parent 
// to detect cycle in subgraph reachable from vertex v. 
Boolean isCyclic(int v, Boolean visited[], int parent) 
{ 
    // Mark the current node as visited 
    visited[v] = true; 
    Integer i; 

    // Recur for all the vertices adjacent to this vertex 
    for(i=0;i<this.max_numb_vertices;i++) {
    	if(v!=i && this.weight_matrix[v][i]>0) {
    		// If an adjacent is not visited, then recur for that adjacent 
    		if(!visited[i]) {
    			if(isCyclic(i,visited,v))
    				return true;
        	}
    		// If an adjacent is visited and not parent of  
            // current vertex, then there is a cycle. 
            else if (i != parent) 
               return true; 
    	}
    }
    return false; 
}

public boolean isTree() // Returns true if the graph is a tree, else false. 
{ 
    // Mark all the vertices as not visited and not part 
    // of recursion stack 
    Boolean visited[] = new Boolean[this.max_numb_vertices]; 
    for (int i = 0; i <this.max_numb_vertices; i++) 
        visited[i] = false; 

    // The call to isCyclicUtil serves multiple purposes 
    // It returns true if graph reachable from vertex 0 
    // is cyclic. It also marks all vertices reachable 
    // from 0. 
    if (isCyclic(0, visited, -1))
        return false; 

    // If we find a vertex which is not reachable from 0 
    // (not marked by isCyclicUtil(), then we return false 
    //for (int u = 0; u <this.max_numb_vertices; u++) 
    //    if (!visited[u]) 
    //        return false; 

    return true; 
} 


*/