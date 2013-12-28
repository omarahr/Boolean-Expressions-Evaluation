import java.util.*;

public class InfixToPostfix {
	static Map<Character, Integer> counts = new HashMap<Character, Integer>();
	static final char close_br = ')';
	static final char open_br = '(';

	public static char[] change(String x) {
		makeLookupTable();
		Stack<Character> per = new Stack<Character>();
		char[] infix = x.toCharArray();
		ArrayList<Character> postfix = new ArrayList<Character>();
		for (int i = 0; i < infix.length; i++) {
			if (Validate.isChar(infix[i])) {
				postfix.add(infix[i]);
			} else {
				if (per.isEmpty()) {
					per.push(infix[i]);
				} else {
					if (infix[i] == close_br) {
						while (!per.isEmpty()) {
							if (per.peek() == open_br) {
								per.pop();
								break;
							} else {
								postfix.add(per.pop());
							}
						}
					} else if (infix[i] == open_br)
						per.add('(');
					else {
						while (!per.isEmpty()) {
							if (counts.get(per.peek()) > counts.get(infix[i])) {
								postfix.add(per.pop());
							} else
								break;
						}
						per.add(infix[i]);
					}
				}
			}
		}
		while (!per.isEmpty())
			postfix.add(per.pop());
		char[] postFix = new char[postfix.size()];
		for (int i = 0; i < postFix.length; i++) {
			postFix[i] = postfix.get(i);
		}
		return postFix;
	}

	public static void makeLookupTable() {
		counts.put('(', 0);
		counts.put('*', 1);
		counts.put('>', 2);
		counts.put('|', 3);
		counts.put('&', 4);
		counts.put('!', 5);
	}

}
