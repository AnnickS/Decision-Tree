import java.io.File;

public class treeBuilder {
	static decisionTree loanTree;

	public static void main(String[] args) {
		loanTree = new decisionTree(new File("data/credit.csv"));
		loanTree.createTrainingData(.1);
		loanTree.listDataPoints();
	}

}
