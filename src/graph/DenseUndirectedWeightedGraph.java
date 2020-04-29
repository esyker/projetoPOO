package graph;
import java.util.LinkedList;
import java.util.List;

public class DenseUndirectedWeightedGraph<V> extends AbstractUndirectedWeightedGraph<V> {
	
	private int currSize;
	private int max_numb_vertices;
	
	public DenseUndirectedWeightedGraph(int max_numb_vertices) {
		super();
		this.weight_matrix=new float[max_numb_vertices][max_numb_vertices];
		this.vertices= new LinkedList<V>();
		this.max_numb_vertices=max_numb_vertices;
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
		
		if(index1!=-1 && index2!=-1) {
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
		
		if(index1!=-1 && index2!=-1) {
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

	public int getNumbVertecis() {
		return this.currSize;
	}
	
	public float [][] getWeightsMatrix(){
		return this.weight_matrix;
	}
	
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
        for (int u = 0; u <this.max_numb_vertices; u++) 
            if (!visited[u]) 
                return false; 
  
        return true; 
    } 
}