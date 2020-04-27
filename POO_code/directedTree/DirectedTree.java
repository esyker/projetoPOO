package directedTree;

import graph.*;

public interface DirectedTree<V> {

	/**
	 * 
	 * @param vertex
	 */
	void addRoot(V vertex);

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
	V[] getChildren(V parent);

	V getRoot();

	/**
	 * 
	 * @param child
	 */
	void getParent(V child);

	/**
	 * 
	 * @param vertex
	 */
	void removeNode(V vertex);

	/**
	 * 
	 * @param s
	 */
	void loadFromSpanningTree(SpanningTree s);

}