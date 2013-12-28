import java.util.ArrayList;


public class DataObject {
	ArrayList<ArrayList<Boolean>> expValue;
	ArrayList<boolean[]> maskes ;
	ArrayList<Character> symbols;
	
	public DataObject(ArrayList<ArrayList<Boolean>> a,ArrayList<boolean[]> b, ArrayList<Character> c)
	{
		expValue = a;
		maskes = b;
		symbols = c;
	}
	
}
