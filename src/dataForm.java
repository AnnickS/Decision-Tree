import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class dataForm {
	private List<String[]> data = new ArrayList<String[]>();
	private String[] titles;
	
	public dataForm() {
	}
	
	public dataForm(List<String[]> data, String[] titles) {
		this.data = data;
		this.titles = titles;
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
	
	public int getPositive() {
		int yes = 0;
		int column = data.get(0).length;
		for(String[] i : data) {
			if(i[column].equals("yes")) {
				yes++;
			}
		}
		
		return yes;
	}
	
	public void voidColumn(int column) {
		List<String[]> redundantData = new ArrayList<String[]>();
		for(int i = 0; i< data.size(); i++) {
			String[] row = data.get(i);
			row[column] = "";
			redundantData.add(row);
		}
		data = redundantData;
	}
	
	public String[] getAttributesofColumn(int column) {
		List<String> att = new ArrayList<String>();
		for(String[] a : data) {
			if(!att.contains(a[column])) {
				att.add(a[column]);
			}
		}
		
		return (String[])att.toArray();
	}
	
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
		both[0] = negative;
		return both;
	}
	
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
