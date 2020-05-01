package classifier;

import dataset.*;
import java.time.Instant;
import java.time.Duration;

public class ClassifierMetrics {

	Classifier classifier;
	Dataset trainDataset,testDataset;
	
	int[] predicted_classes;
	int[] true_classes;
	
	//metrics;
	int true_positives,true_negatives,false_positives,false_negatives;
	long timeToBuild;
	long timeToTest;
	double f1score,accuracy,specificity,sensitivity,precision; 

	void computeAccuracy() {
		this.accuracy=(this.true_positives+this.true_negatives)/(this.false_negatives+this.false_positives
				+this.true_positives+this.true_negatives);
	}

	void computeSpecifity() {
		this.specificity=(this.true_negatives)/(this.true_negatives+this.false_positives);
	}

	void computeSensitivity() {
		this.sensitivity= (this.true_positives)/(this.true_positives+this.false_negatives);
	}
	
	void computePrecision() {
		this.precision= (this.true_positives)/(this.true_positives+this.false_positives);
	}
	
	void computeF1Score() {
		this.f1score= 2*(this.precision*this.sensitivity)/(this.precision+this.sensitivity);
	}
	
	void compute_metrics() {
		for(int i=0;i<this.testDataset.getNumberOfInstances();i++) {
			this.true_classes[i]=this.testDataset.getInstance(i).getClassValue();
			
			if(true_classes[i]==0) {
				if(this.predicted_classes[i]==0) {
					this.true_negatives++;
				}
				else
					this.false_positives++;
			}
			else {//true class 1
				if(this.predicted_classes[i]==0) {
					this.false_negatives++;
				}
				else
					this.true_positives++;
			}
		}
		
		computeAccuracy();
		computeSpecifity();
		computeSensitivity();
		computePrecision();
		computeF1Score();
	}
	
	public double getF1Score() {
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

	public double getSpecifity() {
		return this.specificity;
	}

	public double getSensitivity() {
		return this.sensitivity;
	}
	
	public double getPrecision() {
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
		
		compute_metrics();
		
	}

}