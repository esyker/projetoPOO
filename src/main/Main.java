package main;

import dataset.Dataset;
import dataset.DatasetLoader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dataset d = DatasetLoader.loadDatasetFromCsv("C:\\Users\\Diogo\\eclipse-workspace\\projetoPOO\\src\\main\\bias-train.csv");
		
		System.out.println(d);

	}

}
