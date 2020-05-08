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
		TreeNode<V> res=iter;
		int nChilds = iter.children.size();
		
		//if find==iter.vertex
		if (find.equals(iter.vertex))
		{
			return iter;
		}
		//else
		for (int j=0; j < nChilds; j++)
		{
			//if child(j)==find
			if (find.equals(iter.children.get(j).vertex))
			{
				return iter.children.get(j);
			}
			else
			{
				if (find.equals(res.vertex))
				{
					return res;
				}
				res=findTreeNode(iter.children.get(j), find);
			}
		}
		
		return res;
	}
	
	
	@Override
	public List<V> getChildren(V parent) {
		TreeNode<V> parentNode=null;
		List<V> vertix = new ArrayList<V>();
		
		parentNode=findTreeNode(root, parent);
		int i = parentNode.children.size();
		if (i==0) return null;
		
		
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
	public V getParent(V child) {
		
		if (child.equals(root.vertex))
		{
			return null;
		}
		
		TreeNode<V> childNode = findTreeNode(root, child);

		
		return childNode.parent.vertex;

	}

	
	
	private void fillnode(TreeNode<V> node, SpanningTree<V> s, int index, int previous) {
			
		int size=s.getNumbVertices();
		double[][] matrix=s.getWeightsMatrix();
		
		for (int j=0; j < size; j++)
		{
			if (matrix[index][j]!=-Double.MAX_VALUE && j!=previous)
			{
				TreeNode<V> childnode=node.addChild(s.getVertexFromIndex(j));
				fillnode(childnode,s, j, index);	
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

