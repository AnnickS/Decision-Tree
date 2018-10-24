import java.io.File;

public class decisionTree {
	Node root;
	dataManipulator Info;
	
	public decisionTree(File file) {
		Info = new dataManipulator(file);
		Info.modifyDataAge();
		Info.modifyDataMonth();
		//Voids column 'amount'
		Info.voidColumn(4);
		//Amount: 250 - 18424
	}
	
	public void listAttributeTitles() {
		String[] titles = Info.getTitles();
		
		for(int i = 0; i < titles.length; i++) {
			System.out.println(titles[i]);
		}
		System.out.println();
	}
	
	public void buildDecisionTree() {
		ID3Tree algorithm = new ID3Tree(Info.getTrainingData(), Info.getTitles(), .8, 80);
		algorithm.trainTree();
		root = algorithm.getTree();
	}
	
	public void createTrainingData(double testPercent) {
		Info.splitData(testPercent);
	}

	public void testTree(dataForm testData){
		int right;
		int wrong;
		
		for(int i = 0; i < testData.getSize(); i++){
			String[] row =  testData.getRow(i);
		}
	}
}