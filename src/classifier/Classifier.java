package classifier;

import dataset.Dataset;
import dataset.Instance;

public interface Classifier {

	/**
	 * Build the classifier from the given training dataset
	 * @param data - training dataset
	 */
	void buildClassifier(Dataset data);

	/**
	 * Classify an instance with the classifier
	 * @param i - instance to classify
	 * @return classification 
	 */
	int classify(Instance i);

}