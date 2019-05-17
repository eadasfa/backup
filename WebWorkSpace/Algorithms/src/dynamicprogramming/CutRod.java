package dynamicprogramming;
/**
 * 动态规划，钢条切割
 */
public class CutRod {
	//价目表
	public static final int p[]= {0,1,5,8,9,10,17,17,20,24,30};
	static int t=0;
	public static int cutRod(int n)
	{
		t++;
		if(n==0)	return 0;
		int q = Integer.MIN_VALUE;
		for(int i=1;i<=n;i++)
		{
			q = Math.max(q, p[i]+cutRod(n-i));
		}
		return q;
	}
	public static int memoizedCutRod(int n)
	{
		int marked[] = new int[n+1];
		return cutRod(n,marked);
	}
	public static int cutRod(int n,int[]marked)
	{
		
		if(n==0)	return 0;
		if(marked[n]!=0)	return marked[n];
		t++;
		int q = Integer.MIN_VALUE;
		for(int i=1;i<=n;i++)
		{
			q = Math.max(q, p[i]+cutRod(n-i,marked));
		}
		marked[n] = q;
		return q;
	}
	public void test()
	{
		for(int i=0;i<=10;i++)
		{
			System.out.println("n="+i+"  r"+i+"="+memoizedCutRod(i));
		}
		int i=4;
		System.out.println("r"+i+"="+cutRod(i)+"  t="+t);
		t=0;
		System.out.println("r"+i+"="+memoizedCutRod(i)+"  t="+t);
		
	}
	
	public static void main(String[] args) {
		new CutRod().test();
	}
}
