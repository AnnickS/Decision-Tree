import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class dataSet {
	private List<String[]> dataValues = new ArrayList<String[]>();
	private List<String[]> testData = new ArrayList<String[]>();
	private List<String[]> trainingData = new ArrayList<String[]>();
	private String[] titles;
	
	public dataSet(File file) {
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
	
	public int getRows() {
		return titles.length;
	}
	
	public String[] getTitles() {
		return titles;
	}
	
	public int getTestDataLength() {
		return testData.size();
	}
	
	public int getTrainingDataLength() {
		return trainingData.size();
	}
	
	public String[] getTestDataRow(int i) {
		return testData.get(i);
	}
	
	public String[] getTrainingDataRow(int i) {
		return trainingData.get(i);
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
