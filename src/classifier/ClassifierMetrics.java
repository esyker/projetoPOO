package classifier;

import dataset.*;
import java.time.Instant;
import java.time.Duration;

public class ClassifierMetrics {

	Classifier classifier;
	Dataset trainData,testData;
	long timeToBuild;
	long timeToTest;
	
	public void getF1Score() {
		
	}

	public void getAccuracy() {
		
	}

	public long getTimeToBuild() {
		return this.timeToBuild;
	}

	public long getTimeToTest() {
		return this.timeToTest;
	}

	public void getSpecifity() {
		
	}

	public void getSensitivity() {
		
	}

	/**
	 * 
	 * @param c
	 * @param trainData
	 * @param testData
	 */
	public ClassifierMetrics(Classifier c, Dataset trainData, Dataset testData) {
		this.classifier=c;
		this.testData=testData;
		this.trainData=trainData;
		
		//Build
		Instant build_start = Instant.now();
		this.classifier.buildClassifier(this.trainData);
		Instant build_finish = Instant.now();
		this.timeToBuild= Duration.between(build_start,build_finish).toMillis();
		
		//Test
		//Instant test_start = Instant.now();
		//this.classifier.buildClassifier(this.trainData);
		//Instant test_finish = Instant.now();
		//this.timeToTest= Duration.between(test_start,test_finish).toMillis();
		
	}

}