package classifier;

import dataset.*;
import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;

public class ClassifierMetrics {

	Classifier classifier;
	Dataset trainDataset,testDataset;
	
	int[] predicted_classes;
	int[] true_classes;
	ArrayList<Integer> classes;
	int numb_classes;
	int total;
	
	long timeToBuild;
	long timeToTest; 
	float accuracy;
	float[] f1score,specificity,sensitivity,precision; 
	
	

	void computeMetrics() {
		int true_negatives,false_negatives,true_positives,false_positives;
		int correct_classifications=0;
		
		for (Integer i : this.classes){
			true_negatives=0;
			false_negatives=0;
			true_positives=0;
			false_positives=0;
			
			for(int j=0;j<this.total;j++) {
				if(true_classes[j]!=i)//negative class
				{
					if(true_classes[j]==predicted_classes[j]) {
						true_negatives++;
					}
					else if(true_classes[j]!=predicted_classes[j]) {
						false_negatives++;
					}
				}
				else if(true_classes[j]==i)//positive class
				{
					if(true_classes[j]==predicted_classes[j]) {
						true_positives++;
					}
					else if(true_classes[j]!=predicted_classes[j]) {
						false_positives++;
					}
				}
			}
			this.specificity[i]=(true_negatives)/(true_negatives+false_positives);
			this.sensitivity[i]= (true_positives)/(true_positives+false_negatives);
			this.precision[i]= (true_positives)/(true_positives+false_positives);
			this.f1score[i]= 2*(this.precision[i]*this.sensitivity[i])/(this.precision[i]+this.sensitivity[i]);
			correct_classifications+=true_positives;
			}
		
		this.accuracy=correct_classifications/this.total;
		return;
	}
	
	public float[] getF1Score() {
		return this.f1score;
	}

	public double getAccuracy() {
		return this.accuracy;
	}

	public long getTimeToBuild() {
		return this.timeToBuild;
	}

	public long getTimeToTest() {
		return this.timeToTest;
	}

	public float[] getSpecifity() {
		return this.specificity;
	}

	public float[] getSensitivity() {
		return this.sensitivity;
	}
	
	public float[] getPrecision() {
		return this.precision;
	}

	/**
	 * 
	 * @param c
	 * @param trainData
	 * @param testData
	 */
	public ClassifierMetrics(Classifier c, Dataset trainData, Dataset testData) {
		this.classifier=c;
		this.testDataset=testData;
		this.trainDataset=trainData;
		this.total=this.testDataset.getNumberOfInstances();
		ArrayList<Integer> classes=new ArrayList<Integer>();
		this.numb_classes=0;
		
		//Build
		Instant build_start = Instant.now();
		this.classifier.buildClassifier(this.trainDataset);
		Instant build_finish = Instant.now();
		this.timeToBuild= Duration.between(build_start,build_finish).toMillis();
		
		//Test
		Instant test_start = Instant.now();
		this.predicted_classes=this.classifier.classify(this.testDataset);
		Instant test_finish = Instant.now();
		this.timeToTest= Duration.between(test_start,test_finish).toMillis();
		
		
		//Get available classes type and number
		for(int i=0; i<true_classes.length; i++){
	        if(!classes.contains(true_classes[i])){
	            classes.add(true_classes[i]);
	            numb_classes++;
	        }
	    }
		
		this.f1score=new float[numb_classes+1];
		this.specificity=new float[numb_classes+1];
		this.sensitivity=new float[numb_classes+1];
		this.precision=new float[numb_classes+1];
		
		computeMetrics();
		
	}

}