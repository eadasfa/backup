package dynamicprogramming;

import java.util.Arrays;

/**
 * 		1 * 2 的瓷砖覆盖 n * m 的地板，问共有多少种覆盖方式？
 */
public class CoverFloor {
	static final int w = 1;
	static final int h = 2;
	public static void main(String[] args) {
		CoverFloor cf = new CoverFloor();
		System.out.println(cf.getMethodNum(4,4));
	}
	public double test(int m,int n)
	{
		long t1 = System.currentTimeMillis();
		System.out.print("CoverFloor:"+getMethodNum(m, n)+"\ntime:");
		double t = (System.currentTimeMillis()-t1)*1.0/1000;
		System.out.println(t+"s");
		return t;
	}
	public long getMethodNum(int n,int m)
	{
		if(n<m)
		{
			int t = n;n=m;m=t;
		}
		byte floor[][] = new byte[n][m];

		return putI(floor,0);
	}
	public long putI(byte floor[][],int i)//放置第I行
	{
		 int m = floor[0].length;
		 byte c[]=new byte[m];
		 if(i!=0)
		 {
			 for(int j=0;j<m;j++)
			 {
				 floor[i][j] = (byte) (1-floor[i-1][j]);
				 c[j] = floor[i][j];
			 }
		 }
		 return set(floor,c,i,0);
	}
	public long set(byte floor[][],byte[]c,int i,int j)//放置第i行的第j个
	{
		int m =floor[0].length;
		if(j>=m)	{
			if(i==floor.length-1){
				for(int l=0;l<m;l++)
					if(floor[i][l]==0)	return 0;
//				for(int l=0;l<=i;l++)
//					System.out.println(Arrays.toString(floor[l]));
//				System.out.println();
				return 1;
			}
			return putI(floor,i+1);
		}
		if(c[j]==1){ return set(floor,c,i,j+1);	}
		else {
			floor[i][j] = c[j];
			long t1=set(floor,c,i,j+1);
			if(j+1<m&&c[j+1]==0)
			{
				floor[i][j] = 1;
				floor[i][j+1] = 1;
				t1+=set(floor,c,i,j+2);
			}
			return t1;
		}
	}
}
