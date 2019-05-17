package qi.sort;

import java.util.Random;

/**
 * 自底向上的归并排序
 * describe： 两两归并 如大小为10 的数组归并成5个大小为2的数组  在给并呈2个大小为4和一个大小为2的数组
 * 如：
 *	2 5   6 3   4 8   9 5 
 *	25   36   48  59
 *  2356  4589
 *  23455689
 *  算法复杂度
 *  6nlgn
 *  适用于链表组织的数据
 */
public class MergeBU {
	public static Comparable []aux;
	public static void sort(Comparable []a)
	{
		int N = a.length;
		aux = new Comparable[N];
		for(int sz=1;sz<N;sz*=2)//子数组的大小,每次归并后子数组大小增大
		{
			for(int i=0;i<N;i+=2*sz)
			{
				int lo = i;
				int hi = i+2*sz-1;
				int mid = (lo+hi)/2;
				merge(a, lo, mid, hi);
			}
		}
		
	}
	public static void merge(Comparable[]a,int lo, int mid, int hi)
	{
		int i = lo;
		int j = mid+1;
		for(int k = lo;k <= hi;k++)
			aux[k] = a[k];
		
		for(int k = lo;k <= hi; k++)
		{
			if(i>mid)	a[k] = aux[j++];
			else if(j>hi)	a[k] = aux[i++];
			else if(SortExample.less(aux[j], aux[i]))	a[k] = aux[j++];
			else a[k] = aux[i++];
		}
	}
	
	public static void main(String[] args) {
		String str= "ADSDFGREBdsfSER";
		String s[] = str.split("");
		for(int i=0;i<s.length;i++)
		{
			System.out.print(s[i]);
		}
		System.out.println();
		Merge.sort(s);
		SortExample.show(s);
//		System.out.println(new Random().nextInt(10000)%2);
		
	}
}
