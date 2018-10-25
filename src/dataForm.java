import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//A class that holds data and performs functionalities
//that can manipulate that data and get statistic from it.
public class dataForm {
	private List<String[]> data = new ArrayList<String[]>();
	
	public dataForm() {
	}
	
	public dataForm(List<String[]> data) {
		this.data = data;
	}
	
	public void add(String[] row) {
		data.add(row);
	}
	
	public String[] getRow(int row) {
		return data.get(row);
	}
	
	public int getSize() {
		return data.size();
	}
	
	//Calculates how many results are positive
	//in this data set.
	public int getPositive() {
		int yes = 0;
		if(!data.isEmpty()) {
			int column = data.get(0).length-1;
			for(String[] i : data) {
				if(i[column].equals("yes")) {
					yes++;
				}
			}
		}
		return yes;
	}
	
	public String[] getAttributesofColumn(int column) {
		List<String> att = new ArrayList<String>();
		for(String[] a : data) {
			if(!att.contains(a[column])) {
				att.add(a[column]);
			}
		}

		return att.toArray(new String[0]);
	}
	
	//Gets the positive and negative counts of a column
	public int[] getColumnAccuracy(int column) {
		int positive = 0;
		int negative = 0;
		
		for(String[] s : data) {
			if(s[s.length-1].equals("yes")) {
				positive++;
			} else {
				negative++;
			}
		}
		
		int[] both = new int[2];
		both[0] = positive;
		both[1] = negative;
		return both;
	}
	
	//Gets the positive and negative counts of an attribute in a column
	public int[] getAttributeAccuracy(int column, String attribute) {
		int positive = 0;
		int negative = 0;
		
		for(String[] s : data) {
			if(s[column].equals(attribute)) {
				if(s[s.length-1].equals("yes")) {
					positive++;
				} else {
					negative++;
				}
			}
		}
		
		int[] both = new int[2];
		both[0] = positive;
		both[1] = negative;
		
		return both;
	}
	
	public dataForm breakData(int column, String value){
		dataForm splitData = new dataForm();
		
		for(String[] s : data) {
			if(s[column].equals(value)){
				splitData.add(s);
			}
		}
		
		return splitData;
	}
}
