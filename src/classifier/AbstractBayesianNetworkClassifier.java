package classifier;

import dataset.*;

public abstract class AbstractBayesianNetworkClassifier implements Classifier {

	Dataset trainSet;
	protected final int N_prime;

	public AbstractBayesianNetworkClassifier() {
		// TODO - implement AbstractBayesianNetworkClassifier.AbstractBayesianNetworkClassifier
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param i
	 * @param j
	 */
	protected abstract float computeWeight(Attribute i, Attribute j);

	/**
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @param c
	 */
	protected float computeOFE(Attribute i, int j, int k, int c) {
		// TODO - implement AbstractBayesianNetworkClassifier.computeOFE
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param c
	 */
	protected float computeClassOFE(int c) {
		// TODO - implement AbstractBayesianNetworkClassifier.computeClassOFE
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param i
	 * @param c
	 */
	protected float computeJointProbabilityD(Instance i, int c) {
		// TODO - implement AbstractBayesianNetworkClassifier.computeJointProbabilityD
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param i
	 * @param j
	 * @param k
	 * @param c
	 */
	protected int computeNijkc(Attribute i, int j, int k, int c) {
		// TODO - implement AbstractBayesianNetworkClassifier.computeNijkc
		throw new UnsupportedOperationException();
	}

}