package directedTree;

import java.util.LinkedList;
import java.util.List;

public class TreeNode<V> {

	TreeNode<V> parent;
	List<TreeNode<V>> children;
	protected V vertex;
	
	/**
	 * Creates a node
	 * @param vertex value of the vertex of the node
	 */
	public TreeNode(V vertex) {
		this.vertex = vertex;
		this.children = new LinkedList<TreeNode<V>>();
	}

	/**
	 * Adds a child to node 
	 * @param vertex value of the vertex child
	 * @return node of the new child
	 */
	public TreeNode<V> addChild(V vertex) {
		TreeNode<V> childNode = new TreeNode<V>(vertex);
		childNode.parent = this;
		this.children.add(childNode);
		
		return childNode;
	}
	

	@Override
	public String toString() {
		return "(parent=" + parent + ", vertex=" + vertex +")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vertex == null) ? 0 : vertex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode<?> other = (TreeNode<?>) obj;
		if (vertex == null) {
			if (other.vertex != null)
				return false;
		} else if (!vertex.equals(other.vertex))
			return false;
		return true;
	}


/*
public static void main (String [] args) {
		
	    TreeNode<String> node0 =  new TreeNode<String>("parent");
	    //node0.addChild("child1");
	    //node0.addChild("child2");
	    TreeNode<String> teste =  new TreeNode<String>("teste");
	    
	    //teste = node0.children.get(0);
	    
	    System.out.println("node" + node0.toString());
	    //System.out.println("node" + teste.vertex.equals(node0.children.get(1).vertex));
	    
    	int i = node0.children.size();
    	System.out.println("node" + i);
	       
	}       
*/
}