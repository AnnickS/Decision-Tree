import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class dataManipulator {
	private List<String[]> dataValues = new ArrayList<String[]>();
	private List<String[]> testData = new ArrayList<String[]>();
	private List<String[]> trainingData = new ArrayList<String[]>();
	private String[] titles;
	
	public dataManipulator(File file) {
		Scanner scan;
		
		try {
			scan = new Scanner(file);
			String line;
			
			if(scan.hasNextLine()) {
				line = scan.nextLine();
				titles = line.split(",");
			}
			
			while(scan.hasNextLine()) {
				line = scan.nextLine();
				
				String[] row = line.split(",");
				
				dataValues.add(row);
			}
			
			scan.close();
			
			int size = dataValues.size();
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file name." + '\n');
		}
	}
	
	public dataForm getTestData() {
		dataForm test = new dataForm(testData, titles);
		
		return test;
	}
	
	public dataForm getTrainingData() {
		dataForm training = new dataForm(trainingData, titles);
		
		return training;
	}
	
	public String[] getTitles() {
		return titles;
	}
	
	public void voidColumn(int column) {
		List<String[]> redundantData = new ArrayList<String[]>();
		for(int i = 0; i< dataValues.size(); i++) {
			String[] row = dataValues.get(i);
			row[column] = "";
			redundantData.add(row);
		}
		dataValues = redundantData;
	}
	
	//Total duration 72
	public void modifyDataMonth() {
		int column = 1;
		List<String[]> redundantData = new ArrayList<String[]>();
		for(int i = 0; i < dataValues.size(); i++) {
			String[] row = dataValues.get(i);
			int a = Integer.parseInt(row[column]);
			if(a >= 0 && a < 12) {
				row[column] = "1";
			} else if(a >= 12 && a < 24) {
				row[column] = "2";
			} else if(a >= 24 && a < 36) {
				row[column] = "3";
			} else if(a >= 36 && a <= 48) {
				row[column] = "4";
			} else if(a >= 48 && a <= 60) {
				row[column] = "5";
			} else if(a >= 60 && a <= 72) {
				row[column] = "6";
			}
			redundantData.add(row);
		}
		dataValues = redundantData;
	}
	
	// 0-75
	public void modifyDataAge() {
		int column = 9;
		List<String[]> redundantData = new ArrayList<String[]>();
		for(int i = 0; i < dataValues.size(); i++) {
			String[] row = dataValues.get(i);
			int a = Integer.parseInt(row[column]);
			if(a > 0 && a < 20) {
				row[column] = "1";
			} else if(a >= 20 && a < 40) {
				row[column] = "2";
			} else if(a >= 40 && a < 60) {
				row[column] = "3";
			} else if(a >= 60 && a <= 80) {
				row[column] = "4";
			}
			
			redundantData.add(row);
		}
		dataValues = redundantData;
	}
	
	public void splitData(double testPercent) {
		Collections.shuffle(dataValues);
		
		int amount = (int)(dataValues.size()*testPercent);
		FileWriter writer;
		
		try {
			writer = new FileWriter("data/test_data.csv");
			String[] data;

			for(int i = 0; i < amount; i++) {
				data = dataValues.get(i);
				testData.add(data);
				
				for(int j = 0; j < data.length; j++) {
					writer.append(data[j]);
					if(j != data.length-1) {
						writer.append(",");
					}
				}
				writer.append("\n");
			}
			
			writer.flush();
			writer.close();	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			writer = new FileWriter("data/training_data.csv");
			String[] data;
			
			for(int i = amount; i < dataValues.size(); i++) {
				data = dataValues.get(i);
				trainingData.add(data);
				
				for(int j = 0; j < data.length; j++) {
					writer.append(data[j]);
					if(j != data.length-1) {
						writer.append(",");
					}
				}
				writer.append("\n");
			}
			
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
