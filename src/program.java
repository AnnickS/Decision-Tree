import java.io.File;

public class program {
	static decisionTree loanTree;

	public static void main(String[] args) {
		loanTree = new decisionTree(new File("data/credit.csv"));
		loanTree.createTestData(.1);
		loanTree.buildDecisionTree();
		loanTree.listAttributeTitles();
		loanTree.testTree();
	}

}
