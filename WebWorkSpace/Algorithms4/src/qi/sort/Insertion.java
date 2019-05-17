package qi.sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * ≤Â»Î≈≈–Ú
 * @author Administrator
 *N^2/2
 */
public class Insertion {
	public static void sort(Comparable []a)
	{
		int N = a.length;
		for (int i=1;i < N;i++)
		{
			for (int j=i-1;j>=0;j--)
			{
				if(SortExample.less(a[j+1],a[j]))
				{
					SortExample.exch(a, j+1, j);
				}
			}
		}	
	}
	public static void main(String[] args) {
//		int N=100;
//		
//		Double a[] = new Double[N];
//		for(int i=0;i<N;i++)
//			a[i] = new Double(Math.random());
//		Insertion.sort(a);
//		SortExample.showPicture(a);
		String str= "SAD";
		String s[] = str.split("");
		for(int i=0;i<s.length;i++)
		{
			System.out.print(s[i]);
		}
		System.out.println();
		Insertion.sort(s);
		SortExample.show(s);
	}
}
