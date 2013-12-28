import java.io.IOException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> in = new ArrayList<String>();
		int x = sc.nextInt();
		for (int i = 0; i < x; i++) {
			String input = sc.next();
			in.add(input);
		}
		MainFeatures.check_Equvilance(in);
		sc.close();
	}

}
