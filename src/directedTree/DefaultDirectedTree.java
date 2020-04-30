package directedTree;

import java.util.ArrayList;
import java.util.List;

import graph.*;

public class DefaultDirectedTree<V> implements DirectedTree<V> {

	TreeNode<V> root;

	public DefaultDirectedTree(V vertex) {
		root = new TreeNode<V>(vertex);
	}
	
	public DefaultDirectedTree() {
		
	}
	

	@Override
	public void addChild(V parent, V child) {
		
		TreeNode<V> parentNode=findTreeNode(root, parent);
		parentNode.addChild(child);
		
	}
	
	TreeNode<V> findTreeNode(TreeNode<V> iter, V find) {
		TreeNode<V> aux=iter;
		TreeNode<V> res=null;
		List<V> vertix = new ArrayList<V>();
		int i = aux.children.size();
		
		if (find.equals(aux.vertex))
		{
			return aux;
		}
		//else
		for (int j=0; j < i; j++)
		{
			vertix.add(aux.children.get(j).vertex);
			if (find.equals(vertix.get(j)))
			{
				res=aux.children.get(j);
				return res;
			}
			else
			{
				res=findTreeNode(aux, find);
			}
		}
		
		return res;
	}
	
	
	@Override
	public List<V> getChildren(V parent) {
		TreeNode<V> parentNode=null;
		List<V> vertix = new ArrayList<V>();
		
		parentNode=findTreeNode(root, parent);
		if (parentNode ==null) return null;
		int i = parentNode.children.size();
		
		for (int j=0; j < i; j++)
		{
			vertix.add(parentNode.children.get(j).vertex);
		}
		
		return vertix;
	}

	@Override
	public V getRoot() {

		return root.vertex;
		
	}

	@Override
	public List<V> getParent(V child) {
		
		List<V> parent = new ArrayList<V>();
		TreeNode<V> childNode =findTreeNode(root, child);
		parent.add(childNode.parent.vertex);
		return parent;

	}

	@Override
	public void removeNode(V vertex) {
		
		
	}
	
	private void fillnode(TreeNode<V> node, SpanningTree<V> s, int index, int previus) {
			
		int size=s.getNumbVertices();
		float[][] matrix=s.getWeightsMatrix();
		
		for (int j=0; j < size; j++)
		{
			if (matrix[index][j]!=0 && j!=previus)
			{
				node.addChild(s.getVertexFromIndex(j));
				fillnode(node,s, j, index);	
			}
		}
	}

	@Override
	public void loadFromSpanningTree(SpanningTree<V> s) {
		
		int index=s.findHeight();
		V vertex= s.getVertexFromIndex(index);
		
		root=new TreeNode<V>(vertex);
		
		fillnode(root, s, index, index);
		
	}
		
/*
public static void main (String [] args) {
		
		DefaultDirectedTree<String> tree0 =  new DefaultDirectedTree<String>("root");
	    
		tree0.addChild("root","child1");
		tree0.addChild("root","child2");
		tree0.addChild("child1","child3");

		//TreeNode<String> child1 = tree0.findTreeNode(tree0.root, "child1");
		
	    System.out.println("node: " + tree0.getChildren("child1"));
	    
	
	}       
*/
}

