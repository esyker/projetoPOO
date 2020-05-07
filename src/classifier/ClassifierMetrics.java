package classifier;

import dataset.*;
import java.time.Instant;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class ClassifierMetrics {

	Classifier classifier;
	Dataset trainDataset,testDataset;
	
	int[] predicted_classes;
	int[] true_classes;
	ArrayList<Integer> classes;
	float [] weight_classes;
	int numb_classes;
	int total;
	
	long timeToBuild;
	long timeToTest; 
	float accuracy;
	float[] f1score,specificity,sensitivity,precision; 
	
	

	/**
	 * Function used by the class constructor to compute the Metrics on the test dataset
	 */
	void computeMetrics() {
		int true_negatives,false_negatives,true_positives,false_positives;
		int correct_classifications=0;
		//weighted metrics
		this.specificity[this.numb_classes]=0;
		this.sensitivity[this.numb_classes]=0;
		this.precision[this.numb_classes]=0;
		this.f1score[this.numb_classes]= 0;
		
		for (Integer i : this.classes){
			true_negatives=0;
			false_negatives=0;
			true_positives=0;
			false_positives=0;
			
			for(int j=0;j<this.total;j++) {
				if(true_classes[j]!=i)//negative class
				{
					if(true_classes[j]==predicted_classes[j]) {//negative class predicted
						true_negatives++;
					}
					else if(true_classes[j]!=predicted_classes[j]) {//positive class predicted
						false_positives++;
					}
				}
				else if(true_classes[j]==i)//positive class
				{
					if(true_classes[j]==predicted_classes[j]) {//positive class predicted
						true_positives++;
						correct_classifications++;
					}
					else if(true_classes[j]!=predicted_classes[j]) {//negative class predicted
						false_negatives++;
					}
				}
			}
			this.specificity[i]=(true_negatives)/(true_negatives+false_positives);
			this.sensitivity[i]= (true_positives)/(true_positives+false_negatives);
			this.precision[i]= (true_positives)/(true_positives+false_positives);
			this.f1score[i]= 2*true_positives/(2*true_positives+false_negatives+false_positives);
			this.weight_classes[i]=(true_positives+false_negatives)/(float)this.total;
			//weighted metrics
			this.specificity[this.numb_classes]+=this.weight_classes[i]*this.specificity[i];
			this.sensitivity[this.numb_classes]+=this.weight_classes[i]*this.sensitivity[i];
			this.precision[this.numb_classes]+=this.weight_classes[i]*this.precision[i];
			this.f1score[this.numb_classes]+=this.weight_classes[i]*this.f1score[i];
			}
		
		this.accuracy=correct_classifications/this.total;
		
		return;
	}
	
	/** Returns an array with the classifiers's F1score of the for each class and the weighted F1score 
	 * @return array with the classifiers's F1score of the for each class and the weighted F1score 
	 */
	public float[] getF1Score() {
		float []output=new float[numb_classes+1];
		output=Arrays.copyOf(this.f1score,numb_classes+1);
		return output;
	}

	/**Returns the classifier's Accuracy
	 * @return classifier's Accuracy
	 */
	public double getAccuracy() {
		return this.accuracy;
	}

	/**Returns the classifier's Time to Build
	 * @return classifier's Time to Build
	 */
	public long getTimeToBuild() {
		return this.timeToBuild;
	}
	
	/**Returns the classifier's Time to Test
	 * @return classifier's Time to Test
	 */
	public long getTimeToTest() {
		return this.timeToTest;
	}

	/**Returns an array with the classfier's Specifity for each class and the weighted Specifity
	 * @return array with the classfier's Specifity for each class and the weighted Specifity
	 */
	public float[] getSpecifity() {
		float []output=new float[numb_classes+1];
		output=Arrays.copyOf(this.specificity,numb_classes+1);
		return output;
	}
	
	/**Returns an array with the classfier's Sensitivity for each class and the weighted Sensitivity
	 * @return array with the classfier's Sensitivity for each class and the weighted Sensitivity
	 */
	public float[] getSensitivity() {
		float []output=new float[numb_classes+1];
		output=Arrays.copyOf(this.sensitivity,numb_classes+1);
		return output;
	}
	
	/**Returns an array with the classfier's Precision for each class and the weighted Precision
	 * @return array with the classfier's Precision for each class and the weighted Precision
	 */
	public float[] getPrecision() {
		float []output=new float[numb_classes+1];
		output=Arrays.copyOf(this.precision,numb_classes+1);
		return output;
	}
	
	
	@Override
	public String toString() {
		String output=new String();
		output=	"Classfier:\n"+classifier+"\n"+
				"Time to Build:\t"+timeToBuild+" ms\n"+
				"Testing the classifier:\t"+"\n";
		
		for(int i=0;i<this.total;i++) {
			output+="->instance "+(i+1)+":\t"+predicted_classes[i]+"\n";
		}
		
		output+="Time to test:\t"+timeToTest+" ms\n"+
				"Resume:\t"+
				accuracy+" "+
				Arrays.toString(specificity)+" "+
				Arrays.toString(sensitivity)+" "+
				Arrays.toString(f1score);
		return output;
		//"classes "+classes+"\n"+
		//"labels"+Arrays.toString(true_classes)+"\n"+
		//"predicted_classes"+Arrays.toString(predicted_classes)+"\n"+
	}

	/**
	 * 	Function used to train the classifier and test it and get appropriate measures of performance
	 * @param c Classifier used
	 * @param trainData Dataset used to train
	 * @param testData Dataset used to test and get the metrics
	 */
	public ClassifierMetrics(Classifier c, Dataset trainData, Dataset testData) {
		this.classifier=c;
		this.testDataset=testData;
		this.trainDataset=trainData;
		this.total=this.testDataset.getNumberOfInstances();
		this.true_classes=new int[this.total];
		this.predicted_classes=new int[this.total];
		this.classes=new ArrayList<Integer>();
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
		
		//Get true classes
		for(int i=0;i<this.total;i++) {
			this.true_classes[i]=this.testDataset.getInstance(i).getClassValue();
		}
		
		//Get available classes type and number
		for(int i=0; i<true_classes.length; i++){
	        if(!this.classes.contains(true_classes[i])){
	            this.classes.add(true_classes[i]);
	            numb_classes++;
	        }
	    }
		
		this.f1score=new float[numb_classes+1];
		this.specificity=new float[numb_classes+1];
		this.sensitivity=new float[numb_classes+1];
		this.precision=new float[numb_classes+1];
		this.weight_classes=new float[numb_classes];
		
		computeMetrics();
		
	}

}