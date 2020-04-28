package dataset;

import java.util.*;

public class DefaultDataset implements Dataset {

	protected List<Instance> instacesList;
	
	protected Attribute[] attributes;

	public DefaultDataset(Attribute[] attributes) {
		instacesList = new LinkedList<Instance>();
		this.attributes = attributes;
	}



	@Override
	public boolean add(Instance i) {
		if(i.hasAttributes(this.attributes)) //the instance has all the attributes the dataset has
			return instacesList.add(i);  //TODO: CLONE????
		else
			return false;
		
	}

	@Override
	public void remove(Instance i) {
		instacesList.remove(i);   
		
	}

	@Override
	public Instance getInstance(int index) {
		return instacesList.get(index);   //TODO: CLONE????
	}

	@Override
	public int getMaxClassValue() {
		
		int max_value = -1;
		for(Instance i:instacesList)
		{
			if(i.getClassValue() > max_value)
				max_value = i.getClassValue();
		}
		return max_value;
	}

	@Override
	public int getMaxAttributeValue(Attribute a) {
		int max_value = -1;
		for(Instance i:instacesList)
		{
			if(i.getAttValue(a) > max_value)
				max_value = i.getAttValue(a);
		}
		return max_value;
	}
	
	@Override
	public Attribute[] getAttributes() {
		return attributes;
	}
	
	
	@Override
	public int getNumberOfInstances() {
		return instacesList.size();
	}
	
	@Override
	public String toString() {
		Attribute[] atts = getAttributes();
		String s = "";
		for(Attribute a:atts)
		{
			s+= a.toString() + "\t";
		}
		s += "\tClass\n";
		for(int i = 0; i < getNumberOfInstances(); i++)
		{
			Instance inst = getInstance(i);
			for(Attribute a:atts)
			{
				s += inst.getAttValue(a) + "\t";
			}
			s+="\t"+ inst.getClassValue()+"\n";
		}
		return s;
	}


}