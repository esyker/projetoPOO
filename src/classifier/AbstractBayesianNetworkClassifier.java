package classifier;

import dataset.*;

import directedTree.DefaultDirectedTree;
import directedTree.DirectedTree;
import graph.AbstractUndirectedWeightedGraph;
import graph.DenseUndirectedWeightedGraph;
import graph.PrimMaxSpanningTree;
import graph.SpanningTree;
import graph.SpanningTreeAlgorithm;

public abstract class AbstractBayesianNetworkClassifier implements Classifier {

	protected Dataset trainSet;
	protected DirectedTree<Attribute> directedTree;
	protected final float N_prime = (float) 0.5;

	public AbstractBayesianNetworkClassifier() {
		
	}

	/**
	 * Compute the weight between two attributes
	 * @param i - first attribute
	 * @param j - second attribute
	 */
	protected abstract float computeWeight(Attribute i, Attribute j);

	/**
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @param c
	 */
	protected float computeOFE(Attribute i, Attribute i_parent, int j, int k, int c) {
		int r_i = trainSet.getMaxAttributeValue(i) + 1;
		float OFE = (computeNijkc(i,i_parent,j,k,c)+N_prime)/(computeNikc(i_parent,j,c) + r_i * N_prime);
		return OFE;
	}

	/**
	 * 
	 * @param c
	 */
	protected float computeClassOFE(int c) {
		int s = trainSet.getMaxClassValue() + 1;
		
		float class_OFE = (computeNc(c) + N_prime)/(computeN()+s*N_prime);
		return class_OFE;
	}

	/**
	 * 
	 * @param i
	 * @param c
	 */
	protected float computeJointProbabilityD(Instance i, int c) {
		float joint_prob = computeClassOFE(c);
		Attribute[] atts = trainSet.getAttributes();
		for(Attribute a:atts)
		{
			Attribute a_parent = directedTree.getParent(a);

			joint_prob*=computeOFE(a,a_parent,i.getAttValue(a_parent),i.getAttValue(a),c);
			
		}
		return joint_prob;
	}

	/**
	 * Compute number of instances in the train set where the attribute i takes the value k, the attribute i_prime
	 * takes the value j and the class takes the value c. If i or i_prime are null, those attributes are ignored
	 * @param i - first attribute
	 * @param i_prime - second attribute
	 * @param j - value of i_prime
	 * @param k - value of i
	 * @param c - value of the class
	 */
	protected int computeNijkc(Attribute i, Attribute i_prime, int j, int k, int c) {
		int numInstances = trainSet.getNumberOfInstances();
		Instance inst;
		int count = 0;
		if(i == null && i_prime == null)
			return computeNc(c);
		if(i == null)
			return computeNikc(i,k,c);
		if(i_prime == null)
			return computeNikc(i_prime,j,c);
			
			
		//TODO make dataset iterable???
		for(int a = 0; a < numInstances; a++)
		{
			inst = trainSet.getInstance(a);
			if(inst.getAttValue(i) == k && inst.getAttValue(i_prime) == j && inst.getClassValue() == c)
			{
				count++;	
			}
		}
		
		return count;
	}
	
		
	/**
	 * Compute number of instances in the train set where the attribute i takes the value k 
	 * and the class takes the value c. If i is null, the attribute is ignored
	 * @param i - attribute
	 * @param j - value of parent of i
	 * @param c - value of the class
	 */
	protected int computeNikc(Attribute i, int k, int c) {
		int numInstances = trainSet.getNumberOfInstances();
		Instance inst;
		int count = 0;
		if(i == null)
			return computeNikc(i,k,c);
		for(int a = 0; a < numInstances; a++)
		{
			inst = trainSet.getInstance(a);
			if(inst.getAttValue(i) == k && inst.getClassValue() == c)
			{
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Compute number of instances in the train set where the class takes the value c 
	 * @param c - value of the class
	 */
	protected int computeNc(int c) {
		int numInstances = trainSet.getNumberOfInstances();
		Instance inst;
		int count = 0;
		//TODO make dataset iterable???
		for(int a = 0; a < numInstances; a++)
		{
			inst = trainSet.getInstance(a);
			
			if(inst.getClassValue() == c)
			{
				count++;
			}
					
		}
		return count;
	}
	
	/**
	 * Compute number of instances in the train set
	 */
	protected int computeN() {
		return trainSet.getNumberOfInstances();
	}
	
	
	@Override
	public void buildClassifier(Dataset data) {
		trainSet = data;
		Attribute[] atts = data.getAttributes();
		AbstractUndirectedWeightedGraph<Attribute> g = new DenseUndirectedWeightedGraph<Attribute>(atts.length);
		//add all vertices to graph
		for(int i = 0; i < atts.length; i++)
		{
			g.addVertex(atts[i]);
		}
		//set all edge weights
		for(int i = 0; i < atts.length; i++)
		{
			for(int j = i+1; j < atts.length; j++)
			{
				float weight = computeWeight(atts[i], atts[j]);
				g.setEdgeWeight(atts[i], atts[j], weight);
				g.setEdgeWeight(atts[j], atts[i], weight);
				System.out.println(atts[i] + " " + atts[j] + " " + weight);
				
			}
		}
		
		//get the spanning tree from the graph
		SpanningTreeAlgorithm<Attribute> sta = new PrimMaxSpanningTree<Attribute>(g); 
		SpanningTree<Attribute> st = sta.getSpanningTree();
		//get the directed tree from the spanning tree
		DirectedTree<Attribute> dt = new DefaultDirectedTree<Attribute>();
		dt.loadFromSpanningTree(st);
		
		
		directedTree = dt;
		
		
		
	}

	@Override
	public int classify(Instance i) {
		int class_max = 0;
		float maximum_prob = 0;
		for(int j = 0; j <= trainSet.getMaxClassValue(); j++)
		{
			float prob = computeJointProbabilityD(i,j);
			if(prob > maximum_prob)
			{
				maximum_prob = prob;
				class_max = j;
			}
		}
		return class_max;
	}
	
	@Override
	public int[] classify(Dataset d) {
		int num_instances = d.getNumberOfInstances();
		int[] classification = new int[d.getNumberOfInstances()];
		for(int i = 0; i < num_instances; i++)
		{
			Instance inst = d.getInstance(i);
			classification[i] = classify(inst);
		}
		return classification;
	}

}