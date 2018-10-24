import java.io.File;

public class program {
	static decisionTree loanTree;

	public static void main(String[] args) {
		loanTree = new decisionTree(new File("data/credit.csv"));
		loanTree.createTrainingData(.9);
		loanTree.listAttributeTitles();
		loanTree.createTrainingData();
	}

}
