package dataset;

import java.util.Iterator;

public class DatasetIterator implements Iterator<Instance> {
	
	private int current;
	private Dataset d;
	
	public DatasetIterator(Dataset d) {
		super();
		this.d = d;
		current = 0;
	}

	@Override
	public boolean hasNext() {
		return d.getInstance(current) != null; 
	}

	@Override
	public Instance next() {
		Instance i = d.getInstance(current);
		current++;
		return i;
	}

}
