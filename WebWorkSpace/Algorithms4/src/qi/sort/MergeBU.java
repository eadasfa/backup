package qi.sort;

import java.util.Random;

/**
 * �Ե����ϵĹ鲢����
 * describe�� �����鲢 ���СΪ10 ������鲢��5����СΪ2������  �ڸ�����2����СΪ4��һ����СΪ2������
 * �磺
 *	2 5   6 3   4 8   9 5 
 *	25   36   48  59
 *  2356  4589
 *  23455689
 *  �㷨���Ӷ�
 *  6nlgn
 *  ������������֯������
 */
public class MergeBU {
	public static Comparable []aux;
	public static void sort(Comparable []a)
	{
		int N = a.length;
		aux = new Comparable[N];
		for(int sz=1;sz<N;sz*=2)//������Ĵ�С,ÿ�ι鲢���������С����
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
