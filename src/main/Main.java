package main;

import classifier.Classifier;
import classifier.ClassifierMetrics;
import classifier.LLBayesianNetworkClassifier;
import classifier.MDLBayesianNetworkClassifier;
import dataset.Dataset;
import dataset.DatasetLoader;

public class Main {

	public static void main(String[] args) {
		Dataset train = DatasetLoader.loadDatasetFromCsv("bias-train.csv");
		Dataset test = DatasetLoader.loadDatasetFromCsv("bias-test.csv");
		Classifier ll = new LLBayesianNetworkClassifier();
		ClassifierMetrics metrics = new ClassifierMetrics(ll, train,test);
		System.out.println("accuracy" + metrics.getAccuracy());

	}

}
