import java.io.File;

public class decisionTree {
	Node root;
	dataManipulator Info;
	String[] titles;
	
	public decisionTree(File file) {
		Info = new dataManipulator(file);
		Info.modifyDataAge();
		Info.modifyDataMonth();
		titles = Info.getTitles();
	}
	
	public void buildDecisionTree() {
		ID3Tree algorithm = new ID3Tree(Info.getTrainingData(), Info.getTitles(), .90, 50);
		algorithm.trainTree();
		root = algorithm.getTree();
	}
	
	public void createTestData(double testPercent) {
		Info.splitData(testPercent);
	}

	public double testTree(){
		dataForm testData = Info.getTestData();
		int right = 0;
		
		for(int i = 0; i < testData.getSize(); i++){
			String[] row =  testData.getRow(i);
			if(getResult(root, row)) {
				right++;
			}
		}
		
		double correctPer = right/(double)testData.getSize();
		
		return correctPer;
	}
	
	private boolean getResult(Node att, String[] row) {
		String a = att.attribute;
		boolean question = false;
		
		if(((a == "+") && (row[row.length-1].equals("yes"))) || 
				((a == "-") && (row[row.length-1].equals("no")))) {
			return true;
		} else if((a == "-") || (a == "+")) {
			return false;
		} else {
			int column = getColumn(a);
			String answer = row[column];
			Node next = att.children.get(answer);
			if(next == null) {
				return att.yes;
			}
			return getResult(next, row);
		}
	}
	
	private int getColumn(String att) {
		int a = 0;
		
		for(int i = 0; i < titles.length; i++) {
			if(titles[i].equals(att)) {
				break;
			} else {
				a++;
			}
		}
		
		return a;
	}
}