package qi.sort;

import java.util.Arrays;

public class Count {
	private static int N=10;
	private static final int k = (int) Math.log10(N);
	private static int c[]=new int[N];
	
	public static void sort(Integer a[])
	{
		Integer[] A = a;
		Integer[] B = new Integer[a.length];
		for(int i=k;i<=10;i+=k)
		{
			reset();
			for(int j=0;j<A.length;j++) {
				int t = num(A[j],i);

				c[t]++;
				
			}

			for(int j=1;j<c.length;j++){
				c[j] = c[j-1]+c[j];
			}
			for(int j=A.length-1;j>=0;j--) {
				int t = num(A[j],i);
				B[--c[t]] = A[j];
			}
			Integer[]temp = A;
			A = B;
			B = temp;
		}
	}
	private static int num(int x,int i)
	{
		return (x/((int)(Math.pow(10, i-k))))%N;
	}
	private static void reset()
	{
		for(int i=0;i<c.length;i++)
			c[i] = 0;
	}
	public static void main(String[] args) {
		Integer a[] = {564,4894,787,56,4489,49,153,4984,2317,16552,4648,2164,26848,6156,133,25,14,5164};
		Count.sort(a);
		System.out.println(Arrays.toString(a));

	}
}
