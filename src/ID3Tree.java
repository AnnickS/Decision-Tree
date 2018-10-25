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
		boolean[] traversed = new boolean[titles.length-1];
		traversed[4] = true;
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
		double percent = (double)examples.getPositive()/(double)examples.getSize();
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
				break;
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
		root.attribute = targetAttribute;
		int column = getColumn(targetAttribute);
		traversed[column] = true;
		String[] values = trainingData.getAttributesofColumn(column);
		String[] exampAtt = examples.getAttributesofColumn(column);
		boolean found = false;
		for(String v : values) {
			for(String s : exampAtt) {
				if(s.equals(v)) {
					found = true;
					break;
				}
			}
			Node child = new Node();
			child.attribute = v;
			if(found) {
				dataForm ex = examples.breakData(column, v);
				root.children.put(v, train(ex, traversed));
			} else {
				if(percent >= .5) {
					root.attribute = "+";
					return root;
				}else {
					root.attribute = "-";
					return root;
				}
			}
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
		
		int[] totalAcc=examples.getColumnAccuracy(index);
		gain = entropy(totalAcc);
		String[] attributes = trainingData.getAttributesofColumn(index);
		for(String s: attributes) {
			int[]currentAcc=examples.getAttributeAccuracy(index, s);
			double e=  (currentAcc[0]+currentAcc[1])/(double)(totalAcc[0]+totalAcc[1])*entropy(currentAcc);
			gain -= e;
		}
		
		return gain;
	}
	
	private double entropy(int[] values) {
		int total = values[0]+values[1];
		double percentP = (double)values[0]/(double)total;
		double percentN = (double)values[1]/(double)total;
		double e = 0.0;
		double pos = Math.log(percentP)/Math.log(2);
		double neg = Math.log(percentN)/Math.log(2);
			
		e = -(percentP)*pos-(percentN)*neg;
		
		return Double.isNaN(e) ? 0 : e;
	}
}