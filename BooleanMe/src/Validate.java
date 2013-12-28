import java.util.ArrayList;
import java.util.Stack;
/*
 * 	the operators 
 * 	* biconditional
 * 	> implies
 * 	^ and
 * 	v or
 */

public class Validate {
	String exp; // Expression
	final char and = '&';
	final char or = '|';
	final char not = '!';
	final char imp = '>';
	final char bi = '*';
	final char open_br = '(';
	final char close_br = ')';
	
	public Validate (String word)
	{
            word = word.replaceAll(" ", "");
            exp = word;
	}
	
	public boolean check_string()
	{
		char[] word = exp.toCharArray();
                if(word.length==0) return false;
		Stack<Character> s1 = new Stack<Character>();
		int first = 0; 
		// 0 first state
		// 1 need and operator and other word
		// 2 need and other word only
		boolean avoidDoubleNot = false;
		for (int i = 0; i < word.length; i++) {
			if(word[i]==open_br){
				if(!s1.isEmpty())
				{ // a&!(b&a)
					if(s1.peek()==not)
					{
						s1.pop();
						if(!s1.isEmpty())
							if(s1.peek()!=open_br) s1.pop();
					}
					else if(s1.peek()!=open_br)
						s1.pop();
				}
				s1.add('(');
			}
			else if(isChar(word[i]))
			{
				if(first==1) return false;
				first = 1;
				if(i==0)
					continue;
				else
				{
					if(s1.isEmpty()&&avoidDoubleNot){
						avoidDoubleNot = false;
						continue;
					}
					else if(s1.isEmpty()){
						
						return false;
					}
					if(s1.peek()==not)
					{
						if(i==1)
						{
							s1.pop();
							continue;
						}
						else
						{
							if(s1.size()<2)
								return false;
							else
							{
								s1.pop();
								if(s1.peek()==open_br)
									continue;
								else
								{
									s1.pop();
								}
							}
						}
					}
					else
					{
						if(!(first==0||s1.peek()==open_br))
							s1.pop();
					}
				}
			}
			else if(word[i]==imp|word[i]==bi|word[i]==or|word[i]==and)
			{
				if(first!=1) return false;
				first = 2;
				s1.add(word[i]);
				//a&(a&b)!a
				//a&!b
			}
			else if(word[i]==not)
			{
				if(i==0)
					s1.add('!');
				else if(s1.isEmpty()) return false;
				else if(s1.peek()==open_br)
				{
					// check that case (A!B)
					if(first==1) return false;
				}
				else if(s1.peek()==not){
					avoidDoubleNot = true;
					s1.pop();
				}
				else s1.add('!');
			}
			else if(word[i]==close_br)
			{
				if(s1.isEmpty()) return false;
				else if(s1.peek()!=open_br) return false;
				else s1.pop();
			}
			else return false;
		}
		if(!s1.isEmpty()|first==2) return false;
		return true;
	}

	public void replaceSigns()
	{
		char[] jop = exp.toCharArray();
		ArrayList<Character> after = new ArrayList<Character>();
		for (int i = 0; i < jop.length; i++) {
			if(jop[i]==or|jop[i]==and|jop[i]==not|jop[i]==open_br|jop[i]==close_br|isChar(jop[i])) after.add(jop[i]);
			else if(jop[i]=='<')
			{
				if(i+2<jop.length)
				{
					if(jop[i+1]=='-'&&jop[i+2]=='>'){
						after.add('*');
						i+=2;
					}
					else after.add('$');
				}
				else
				{
					after.add('$');
				}
			}
			else if(jop[i]=='-')
			{
				if(i+1<jop.length)
				{
					if(jop[i+1]=='>')
					{
						after.add('>');
						i++;
					}
					else after.add('$');
				}
				else after.add('$');
			}
			else 
			{
				after.add('$');
			}
		}
		exp = "";
		for (int i = 0; i < after.size(); i++) {
			exp = exp + after.get(i);
		}
	}
	
	public static boolean isChar(char x)
	{
		if((x<=90&&x>=65)|(x<=122&&x>=97)) return true;
		return false;
	}
	
	
}
