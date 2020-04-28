package dataset;

public interface Dataset {

	/**
	 * Add an instance to the dataset is the instance has the same attributes as the dataset
	 * @param i - instance to add
	 * @return true if successful, false if unsuccessful
	 */
	boolean add(Instance i);

	/**
	 * remove an instance from the dataset
	 * @param i - instance to remove
	 */
	void remove(Instance i);

	/**
	 * return an instance in the dataset
	 * @param index - index of the instance to return
	 */
	Instance getInstance(int index);

	/**
	 * Return the maximum value the class has
	 * @return the maximum value the class has
	 */
	int getMaxClassValue();
	
	/**
	 * Get the number of instances in the dataset
	 * @return number of instances
	 */
	int getNumberOfInstances();

	/**
	 * Return the maximum value an attribute has
	 * @param a - attribute
	 */
	int getMaxAttributeValue(Attribute a);

	/**
	 * Get an array with all the attributes of the dataset
	 * @return array with all of the attributes of the dataset
	 */
	 Attribute[] getAttributes();
}