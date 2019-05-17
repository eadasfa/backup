package qi.sort;



public class Quick {
	public static void sort(Comparable []a)
	{
//		StdRandom.shuffle(a);//随机打乱顺序
		sort(a, 0, a.length-1);
	}
	public static void sort(Comparable []a, int lo, int hi)
	{
		if(hi<=lo) return ;
		int j = partition(a, lo, hi);
		sort(a, lo, j);
		sort(a, j+1, hi);
	}
	public static int partition(Comparable []a, int lo, int hi)
	{
		int i = lo;
		int j = hi+1;
		Comparable v = a[lo+(int)(Math.random()*(hi-lo))];
		SortExample.exch(v, a[lo]);
		while(true)
		{
			//当与“哨兵相等时不要管他，否则会陷入无限循环”
			while(SortExample.less(a[++i], v)) if(i==hi) break;
			while(SortExample.less(v, a[--j])) if(j==lo) break;
			if(i >= j)
				break;
			SortExample.exch(a, i, j);
		}
		SortExample.exch(a, lo, j);
		
		return j;
	}
	public static void main(String[] args) {
//		String str= "ASFSGEXDBSESERDD";
//		String s[] = str.split("");
//		for(int i=0;i<s.length;i++)
//		{
//			System.out.print(s[i]);
//		}
//		System.out.println();
//		Quick.sort(s);
//		SortExample.show(s);
		int N = 13000;
		int T = 1;
		double t= SortCompare.timeRandomInput("Quick", N, T);
		System.out.println(t+"s");
	}
}
