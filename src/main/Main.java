package main;

import dataset.Dataset;
import dataset.DefaultDataset;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dataset d = new DefaultDataset("C:\\Users\\Diogo\\eclipse-workspace\\projetoPOO\\src\\main\\bias-train.csv");
		
		System.out.println(d);

	}

}
