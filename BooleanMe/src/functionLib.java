
public class functionLib {
	public static boolean[] dismalTobinary(int x)
	{
		/*
		 *  1 = [1,0,0,0.....] length 64
		 *  1 = [true,false,false,.....]
		 */
		boolean[] mask = new boolean[64];
		if(x==0) return mask;
		for (int i = 0; x>0; i++) {
			mask[i] = x%2==1?true:false;
			x/=2;
		}
		return mask;
	}
	
	
	
	public static boolean eval_and(boolean a,boolean b)
	{
		return a&b;
	}
	public static boolean eval_or(boolean a,boolean b)
	{
		return a|b;
	}
	public static boolean eval_implies(boolean a,boolean b)
	{
		if(a) return b;
		return !a;
	}
	public static boolean eval_biconditional(boolean a,boolean b)
	{
		return !(a^b);
	}
}
