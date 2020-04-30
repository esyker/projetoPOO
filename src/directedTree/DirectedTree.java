package directedTree;

import java.util.List;

import graph.*;

public interface DirectedTree<V> {

	
	/**
	 * 
	 * @param parent
	 * @param child
	 */
	void addChild(V parent, V child);

	/**
	 * 
	 * @param parent
	 */
	List<V> getChildren(V parent);

	V getRoot();

	/**
	 * 
	 * @param child
	 */
	List<V> getParent(V child);

	/**
	 * 
	 * @param vertex
	 */
	void removeNode(V vertex);

	/**
	 * 
	 * @param s
	 */
	void loadFromSpanningTree(SpanningTree<V> s);

}