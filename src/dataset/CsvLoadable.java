package dataset;

public interface CsvLoadable {

	/**
	 * loads the object from a csv file
	 * @param fileName - name of csv file from which the object will be loaded
	 */ 
	void loadFromCsvFile(String fileName);

}