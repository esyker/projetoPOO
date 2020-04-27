package dataset;

import java.util.*;

public class DefaultDataset implements Dataset, CsvLoadable {

	Collection<Instance> instacesList;

	public DefaultDataset() {
		// TODO - implement DefaultDataset.DefaultDataset
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param fileName
	 */
	public DefaultDataset(String fileName) {
		// TODO - implement DefaultDataset.DefaultDataset
		throw new UnsupportedOperationException();
	}

	@Override
	public void loadFromCsvFile(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(Instance i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Instance i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Instance getInstance(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxClassValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxAttributeValue(Attribute a) {
		// TODO Auto-generated method stub
		return 0;
	}

}