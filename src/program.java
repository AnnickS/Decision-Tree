import java.io.File;

public class program {
	static decisionTree loanTree;

	public static void main(String[] args) {
		for(int i = 0; i <= 100; i++) {
		loanTree = new decisionTree(new File("data/credit.csv"));
		loanTree.createTestData(.1);
		loanTree.buildDecisionTree();
		loanTree.testTree();
		}
	}

}
