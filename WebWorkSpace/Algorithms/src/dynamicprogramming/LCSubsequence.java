package dynamicprogramming;
/**
 * Longest-common-subsequence problem
 *
 */
public class LCSubsequence {
	private int lcs=0;
	private byte b[][];
	private String subLCS;
	public LCSubsequence(String X, String Y) {
		
		this.lcs=getLCS(X, Y);
		this.subLCS = this.getS(X,X.length()-1,Y.length()-1);
	}
	public LCSubsequence() {
	}

	public int getLCSLength() { return this.lcs;}
	//自底向上
	public int getLCS2(String X, String Y)
	{
		//c[i][j]表示X前x个与Y前j个的最长子序列的长度
		int c[][]=new int[X.length()][Y.length()];
		b = new byte[X.length()][Y.length()];
		for(int i=0;i<X.length();i++)
		{
			for(int j=0;j<Y.length();j++)
			{
				if(X.charAt(i)==Y.charAt(j)) {
					b[i][j] = 1;
					if(i-1<0||j-1<0)	c[i][j]=1;
					else {
						c[i][j]=1+c[i-1][j-1];
					}
				}else {
					b[i][j]=2;
					if(i-1<0&&j-1<0) c[i][j]=0;
					else if(i-1<0)	 {
						c[i][j]=c[i][j-1];
						b[i][j] = 3;
					}
					else if(j-1<0||c[i-1][j]>c[i][j-1])	 
					{
						c[i][j]=c[i-1][j];
						b[i][j] = 2;
					}
					else {
						c[i][j]=c[i][j-1];
						b[i][j] = 3;
					}
				}
			}
		}
		return c[X.length()-1][Y.length()-1];
	}
	//自顶向下 逐渐递减
	private int getLCS(String X, String Y)
	{
		//c[i][j]表示X前x个与Y前j个的最长子序列的长度
		int c[][]=new int[X.length()][Y.length()];
		b = new byte[X.length()][Y.length()];
	
		for(int i=0;i<X.length();i++)
		{
			for(int j=0;j<Y.length();j++)
				c[i][j]=-1;
		}
		return getLCS(c,X,X.length()-1,
					    Y,Y.length()-1);
	}
	private int getLCS(int [][]c,String X,int x, 
									   String Y,int y)
	{
		if(x<0||y<0) return 0;
		if(c[x][y]!=-1)	return c[x][y];
		
		if(X.charAt(x)==Y.charAt(y))
		{
			c[x][y] = 1+getLCS(c,X,x-1,Y,y-1);
			b[x][y] = 1;
		}
		else {
			int t1 = getLCS(c, X, x-1, Y, y);
			int t2 = getLCS(c, X, x, Y, y-1);
			if(t1>=t2)
			{
				c[x][y] = t1;
				b[x][y] = 2;
			}else {
				c[x][y] = t2;
				b[x][y] = 3;
			}
		}
		return c[x][y];
	}

	public void testLSC(int type,String X, String Y)
	{
		this.lcs=0;
		this.b=null;
		this.subLCS=null;
		if(type == 1)
			this.lcs=getLCS(X, Y);
		else if(type==2)
			this.lcs=getLCS2(X, Y);
		this.subLCS = this.getS(X,X.length()-1,Y.length()-1);
		System.out.printf("type:%d		lcs=%d\n%s\n",
				type,this.lcs,this.subLCS);
	}
	public String getS(String X,int x,int y)
	{
		if(x<0||y<0)	return "";
		if(b[x][y]==1)	return getS(X,x-1,y-1)+X.charAt(x);
		if(b[x][y]==2)	return getS(X,x-1,y);
		return getS(X,x,y-1);
	}
	public String toString()
	{
		return this.subLCS;
	}
	public static void main(String[] args) {
		
//		String X="ACCGGTCGAGTGCGCGGAAGCCGGCCGAA";
//		String Y="GTCGTTCGGAATGCCGTTGCTCTGTAAA";
		String X="10010101";
		String Y ="010110110";
		LCSubsequence lcs = new LCSubsequence();
		lcs.testLSC(1, X, Y);
		lcs.testLSC(2, X, Y);
	}
}
