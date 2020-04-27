package classifier;

public interface Classifier {

	/**
	 * 
	 * @param data
	 */
	void buildClassifier(Dataset data);

	/**
	 * 
	 * @param i
	 */
	int classify(Instance i);

}