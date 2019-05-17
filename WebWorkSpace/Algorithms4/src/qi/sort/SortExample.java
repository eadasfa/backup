package qi.sort;

import edu.princeton.cs.algs4.StdDraw;

public class SortExample {
	public static void sort(Comparable []a)
	{
		
	}
	public static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w)<=0;
	}
	public static void exch(Comparable []a,int i,int j)
	{
		Comparable r = a[i];
		a[i] = a[j];
		a[j] = r;
	}
	public static void exch(Comparable i,Comparable j)
	{
		Comparable r = i;
		i = j;
		j = r;
	}
	public static void show(Comparable []a)
	{
		
		for (int i=0;i<a.length;i++)
			System.out.print(a[i]+" ");
		System.out.println();
		
	}
	public static void showPicture(Comparable []a)
	{
		int N = a.length;
		for (int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
			Double t = (Double)a[i];
			double x = 1.0*i/N;
			double y = t.doubleValue()/2;
			double halfWidth = 1.0/(N*20);
			double halfHeight = y ;
			StdDraw.filledRectangle(x, y, halfWidth, halfHeight);
		}
	}
	public static boolean isSorted(Comparable []a)
	{
		for(int i=1; i < a.length;i++)
		{
			if(less(a[i],a[i-1]))
				return false;
		}
		return true;
	}
}
