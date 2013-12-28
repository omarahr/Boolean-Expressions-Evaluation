import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Evaluation {
	ArrayList<Character> symbols;
	ArrayList<char[]> postfix;
	Map<Character, Boolean> mask;
	public Evaluation (ArrayList<char[]> a)
	{
		mask = new HashMap<Character, Boolean>();
		postfix = a;
		symbols = new ArrayList<Character>();
		for (int i = 0; i < a.size(); i++) {
			char[] b = a.get(i);
			for (int j = 0; j < b.length; j++) {
				if(Validate.isChar(b[j]))
				{
					if(mask.containsKey(b[j])) continue;
					else{
						symbols.add(b[j]);
						mask.put(b[j], false);
					}
				}
			}
		}
		Collections.sort(symbols);
	}
	
	public DataObject makeTruthTable()
	{
		ArrayList<ArrayList<Boolean>> expValue = new ArrayList<ArrayList<Boolean>>();
		ArrayList<boolean[]> maskes = new ArrayList<boolean[]>() ;
		//
		int x =1;
		for (int i = 0; i < symbols.size(); i++) {
			x*=2;
		}
		for (int i = 0; i < x; i++) {
			maskes.add(makeMask(i));
			ArrayList<Boolean> row = new ArrayList<Boolean>();
			expValue.add(row);
			for (int j = 0; j < postfix.size(); j++) {
				expValue.get(i).add(solve(j));
			}
		}
		DataObject data = new DataObject(expValue, maskes, symbols);
		return data;
	}
	
	boolean solve(int index)
	{
		Stack<Boolean> source = new Stack<Boolean>();
		char[] postfixExp = postfix.get(index);
		for (int i = 0; i < postfixExp.length; i++) {
			if(Validate.isChar(postfixExp[i]))
			{
				source.push(mask.get(postfixExp[i]));
			}
			else
			{
				if(postfixExp[i]=='!')
				{
					boolean a = source.pop();
					source.push(!a);
				}else{
					boolean b = source.pop();
					boolean a = source.pop();
					boolean result = booleanCases(a,b,postfixExp[i]);
					source.push(result);
				}
			}
		}
		return source.pop();
	}
	
	
	boolean booleanCases(boolean a ,boolean b,char x)
	{
		if(x=='&')
			return functionLib.eval_and(a, b);
		else if(x=='|')
			return functionLib.eval_or(a, b);
		else if(x=='>')
			return functionLib.eval_implies(a, b);
		else if(x=='*')
			return functionLib.eval_biconditional(a, b);
		System.out.println("if this statment appears some thing worng happend in evaltion");
		return false;
	}
	
	boolean[] makeMask(int x)
	{
		boolean[] booleanMask = functionLib.dismalTobinary(x);
		for (int i = 0, j = symbols.size()-1; i < symbols.size();j--, i++) {
			mask.put(symbols.get(i), booleanMask[j]);
		}
		return booleanMask;
	}
	void printmask(boolean[] a,int x)
	{
		int t = (int)(Math.log(x)/Math.log(2));
		t = symbols.size()-1>t?symbols.size()-1:t;
		for (int i = t; i>-1; i--) {
			System.out.print((a[i]==true?1:0)+" ");
		}
	}
}
