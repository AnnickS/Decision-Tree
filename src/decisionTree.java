import java.io.File;

public class decisionTree {
	dataSet Info;
	
	public decisionTree(File file) {
		Info = new dataSet(file);
	}
	
	public void listDataPoints() {
		String[] titles = Info.getTitles();
		
		for(int i = 0; i < Info.getRows(); i++) {
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