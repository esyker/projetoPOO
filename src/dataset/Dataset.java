package dataset;

public interface Dataset {

	/**
	 * 
	 * @param i
	 */
	void add(Instance i);

	/**
	 * 
	 * @param i
	 */
	void remove(Instance i);

	/**
	 * 
	 * @param index
	 */
	Instance getInstance(int index);

	int getMaxClassValue();

	/**
	 * 
	 * @param a
	 */
	int getMaxAttributeValue(Attribute a);

}