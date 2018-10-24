public class ID3Tree {
	private Node treeRoot;
	private double percentThreshold;
	private int amountThreshold;
	private String[] titles;
	private dataForm trainingData;
	
	public ID3Tree(dataForm training, String[] titles, double percent, int amount) {
		trainingData = training;
		this.titles = titles;
		percentThreshold = percent;
		amountThreshold = amount;
	}
	
	public Node getTree() {
		return treeRoot;
	}
	
	public void trainTree() {
		boolean[] traversed = new boolean[titles.length];
		treeRoot = train(trainingData, traversed);
	}
	
	private int getColumn(String Lable) {
		int column = 0;
		for(String s : titles) {
			if(!s.equals(Lable)) {
				column++;
			} else {
				break;
			}
		}
		return column;
	}
	
	private Node train(dataForm examples, boolean[] traversed) {
		Node root = new Node();
		double percent = examples.getPositive()/examples.getSize();
		if(percent >= percentThreshold) {
			root.attribute = "+";
			return root;
		}else if((1-percent) >= percentThreshold) {
			root.attribute = "-";
			return root;
		}
		boolean allTraveled = true;
		for(boolean i : traversed) {
			if(!i) {
				allTraveled = false;
			}
		}
		if(allTraveled || (examples.getSize() <= amountThreshold)) {
			if(percent >= .5) {
				root.attribute = "+";
				return root;
			}
			else {
				root.attribute = "-";
				return root;
			}
		}
		
		String targetAttribute = findBest(examples, traversed);
		
		int column = getColumn(targetAttribute);
		traversed[column] = true;
		String[] values = trainingData.getAttributesofColumn(column);
		for(String v : values) {
			Node child = new Node();
			child.attribute = v;
			train(trainingData.breakData(column, v), traversed);
			root.children.put(v, train(trainingData.breakData(column, v), traversed));
		}
		return root;
	}
	
	private String findBest(dataForm examples, boolean[] traversed) {
		double[] gain = new double[titles.length-1];
		String attribute;
		for(int i = 0; i < gain.length; i++) {
			if(traversed[i]) {
				gain[i] = 0;
				continue;
			} else {
				gain[i] = calculateGain(examples, i);
			}
		}
		
		double largest = gain[0];
		int index = 0;
		for(int i = 0; i < gain.length; i++) {
			if(gain[i] > largest) {
				largest = gain[i];
				index = i;
			}
		}
		
		return titles[index];
	}
	
	private double calculateGain(dataForm examples, int index) {
		double gain;
		
		gain = entropy(examples.getColumnAccuracy(index));
		String[] attributes = examples.getAttributesofColumn(index);
		for(String s: attributes) {
			gain -= entropy(examples.getAttributeAccuracy(index, s));
		}
		
		return gain;
	}
	
	private double entropy(int[] values) {
		int total = values[0]+values[1];
		double percentP = values[0]/total;
		double percentN = values[1]/total;
		int n = -1;
		double e = 0.0;
		for(int i = 0; i < values.length; i++) {
			double pos = Math.log(percentP)/Math.log(2);
			double neg = Math.log(percentN)/Math.log(2);
			
			e = (percentP)*pos*(n)-(percentN)*neg;
		}
		
		return Double.isNaN(e) ? 0 : e;
	}
}