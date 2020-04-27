package classifier;

import dataset.Dataset;
import dataset.Instance;

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