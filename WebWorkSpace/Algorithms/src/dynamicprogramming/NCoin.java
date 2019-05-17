package dynamicprogramming;

public class NCoin {
	static final int c1 = 1;
	static final int c3 = 3;
	static final int c5 = 5;
	public static int getNCoin(int money)
	{
		int n5 = money/5;
		int n3 = (money%5)>=3?1:0;
		int n1 = money%5>=3?(money%5-3):(money%5);
		return n5+n3+n1;
	}
	public static void main(String[] args) {
		for(int i=0;i<=15;i++)
		{
			System.out.println("money="+i+" for "+getNCoin(i)+" coins");
		}
	}
}
