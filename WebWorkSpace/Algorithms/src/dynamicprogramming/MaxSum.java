package dynamicprogramming;

public class MaxSum {
	public static void main(String[] args) {
		int a[] = {-2,11,-4,13,-5,-2,-20,5};
		test(a);
	}
	public static void test(int a[])
	{
		int b[] = getSum2(a);
		System.out.printf("Max Sum :"+b[0]+"\trange:[%d,%d]\n",b[1],b[2]);
	}
	public static int[] getSum(int []a)
	{
		int n = a.length;
		int c[][] = new int[n][n];
		/**
		 * b[0] the max sum 
		 * b[1] the start
		 * b[2] the end(included)
		 */
		
		int b[] = new int[3];	
		for(int i=0;i<n;i++)
		{
			for(int j=i;j<n;j++)
			{
				if(i==j) c[i][j] = a[i];
				else c[i][j] = c[i][j-1]+a[j];
				if(c[i][j]>b[0])
				{
					b[0] = c[i][j];
					b[1] = i;
					b[2] = j;
				}
			}
		}
		return b;
	}
	public static int[] getSum2(int []a)
	{
		int n = a.length;
		int b=0;
		int result[]=new int[3];	//以i为结尾的最大和
		/**
		 * result[0] the max sum 
		 * result[1] the start
		 * result[2] the end(included)
		 */
		for(int i=0;i<n;i++)
		{
			if(b+a[i]<a[i])
			{
				b=a[i];	
				if(result[0]<b) result[1] = i;
			}
			else  b+=a[i];	
			if(result[0]<b) {	
				result[0] = b;result[2] = i;
			}
		}
		return result;
	}
}
