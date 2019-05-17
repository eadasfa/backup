package qi.sort;
/**
 * 自顶向下的归并排序
 * 算法复杂度NlgN
 * describe: 将整个数组分为两份，递归进行归并
 *
 */
public class Merge {
	public static Comparable []aux;
	public static void sort(Comparable []a)
	{
		aux = new Comparable[a.length];
		Merge.mergeSort(a, 0,a.length-1);
		
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
	public static void mergeSort(Comparable[]a , int lo,  int hi)
	{
		if(hi<=lo) 	return ;
		int mid = (lo+hi)/2;
		mergeSort(a, lo, mid);
		mergeSort(a, mid+1, hi);
		if(!SortExample.less(a[mid], a[mid+1]))
			merge(a, lo, mid, hi);
	}
	public static void main(String[] args) {
		String str= "agasgwafasd";
		String s[] = str.split("");
		for(int i=0;i<s.length;i++)
		{
			System.out.print(s[i]);
		}
		System.out.println();
		Merge.sort(s);
		SortExample.show(s);
		
	}
}
