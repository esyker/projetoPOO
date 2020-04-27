package dataset;

public interface Instance {

	/**
	 * 
	 * @param c
	 */
	void setClass(int c);

	int getclass();

	/**
	 * 
	 * @param a
	 * @param v
	 */
	void setAttValue(Attribute a, int v);

	/**
	 * 
	 * @param a
	 */
	int getAttValue(Attribute a);

	/**
	 * 
	 * @param a
	 */
	void removeAttribute(Attribute a);

	int getNumAttributes();

}