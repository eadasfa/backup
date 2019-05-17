package qi.sort;

/**
 * ¶ÑÅÅÐò
 * @author Administrator
 *
 */
public class Heap {
	public static void sort(Comparable[]a)
	{
		buildMaxHeap(a);
		int N = a.length;
		for(int i=N-1;i>=1;i--)
		{
			SortExample.exch(a, 0, i);
			maxHeap(a, 0, i);
		}
	}
	public static void maxHeap(Comparable[]a,int i,int len)
	{
		int left = i*2+1;
		int right = i*2+2;
		int N = len;
		int large = i;
		if(left<N  && SortExample.less(a[large], a[left]))
			large = left;
		if(right<N && SortExample.less(a[large], a[right]))
			large = right;
		if(large != i)
		{
			SortExample.exch(a, i, large);
			maxHeap(a, large, N);
		}
	}
	public static void buildMaxHeap(Comparable[]a)
	{
		int N = a.length;
		for(int i = N/2;i>=0;i--)
		{
			maxHeap(a, i, N);
		}
	}
	public static void main(String[] args) {
		int N=100000;
		Double a[] = new Double[N];
		for(int i=0;i<N;i++)
		{
			a[i] = new Double(Math.random()*10000000);
		}
		long t1 = System.currentTimeMillis();
		Heap.sort(a);
		long t2 = System.currentTimeMillis();
		System.out.println((t2-t1)*1.0/1000+"s");
//		SortExample.show(a);
		
	}
}
