import java.util.HashMap;
import java.util.Map;

public class Node {
	boolean yes;
	String attribute = "";
	Map<String, Node> children = new HashMap<String, Node>();
}
