import java.io.File;

public class decisionTree {
	dataSet Info;
	
	public decisionTree(File file) {
		Info = new dataSet(file);
	}
	
	public void listDataPoints(dataSet data) {
		String[] titles = data.getTitles();
		
		for(int i = 0; i < data.getRows(); i++) {
			System.out.println(titles[i]);
		}
		System.out.println();
	}
	
	public void buildDecisionTree() {
		
	}
	
	public void createTrainingData(double testPercent) {
		Info.splitData(testPercent);
	}
}