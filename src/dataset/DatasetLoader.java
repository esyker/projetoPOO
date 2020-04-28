package dataset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class DatasetLoader {
	
	public static Dataset loadDatasetFromCsv(String filepath)
	{
		String line = "";
        String csvSplitBy = ",";
        Dataset d = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
        	
        	if((line = br.readLine()) != null)  //read the first line
			{	
	        	String[] header = line.split(csvSplitBy);
	        	
	        	Attribute[] atts = new Attribute[header.length-1];
	        	
	        	for(int i = 0; i < atts.length; i++)
	        	{
	        		atts[i] = new Attribute(header[i]);
	        	}
	        	d = new DefaultDataset(atts);
			}
        	else
        		return null;
        	
        	

            while ((line = br.readLine()) != null) {
            	
            	String[] values = line.split(csvSplitBy);
                //create an instance for each line and add to dataset
            	Instance inst = new DefaultInstance();
            	for(int i = 0; i < values.length - 1; i++)
            	{
            		inst.setAttValue(d.getAttributes()[i], Integer.parseInt(values[i]));
            		
            	}
            	inst.setClassValue(Integer.parseInt(values[values.length-1]));
            	d.add(inst);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
	}

}
