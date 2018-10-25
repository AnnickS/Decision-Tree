import java.io.File;

public class program {
	static decisionTree loanTree;

	public static void main(String[] args) {
		double avPercent = 0.0;
		
		//Creates 100 different trees from 100 different
		//partitions of data.
		for(int i = 0; i <= 100; i++) {
			loanTree = new decisionTree(new File("data/credit.csv"));
			loanTree.createTestData(.1);
			loanTree.buildDecisionTree();
			double correct = loanTree.testTree();
			avPercent += correct;
			System.out.println("Percentage Correct: " + correct);
		}
		
		System.out.println("\nAverage Percentage Correct: " + avPercent/(double)100);
	}

}
