package dataset;

import java.util.*;

public class DefaultInstance implements Instance {

	protected Map<Attribute, Integer> attributeValues;
	protected int classValue;

	public DefaultInstance() {
		attributeValues = new HashMap<Attribute,Integer>();
		classValue = -1;
	}

	@Override
	public boolean setClassValue(int c) {
		if(c >= 0)
		{
			this.classValue = c;
			return true;
		}
		else
			return false;
		
	}

	@Override
	public int getClassValue() {
		return classValue;
	}

	@Override
	public void setAttValue(Attribute a, int v) {
		attributeValues.put(a, Integer.valueOf(v));
		
	}

	@Override
	public int getAttValue(Attribute a) {
		Integer i = attributeValues.get(a);
		if(i == null)
			return -1;
		else return i.intValue();
	}

	@Override
	public void removeAttribute(Attribute a) {
		attributeValues.remove(a);
		
	}

	@Override
	public Attribute[] getAttributes() {
		return attributeValues.keySet().toArray(new Attribute[0]);
	}

	@Override
	public boolean hasAttributes(Attribute[] attributes) {

		for(Attribute a:attributes)
		{
			if(!attributeValues.containsKey(a))
				return false;
		}
		return true;
	}

		

	

}