package dynamicprogramming;
public class LCSubstring {
	private String substring;
	private int length=0;
	public LCSubstring() {
	}
	public LCSubstring(String X,String Y) {
		this.getLCS(X, Y);
	}
	public void getLCS(String X,String Y)
	{
		int m = X.length();
		int n = Y.length();
		int c[][] = new int[m][n];
		for(int i=0;i<m;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(X.charAt(i)==Y.charAt(j)) {
					if(i-1<0||j-1<0)	c[i][j]=1;
					else {
						c[i][j]=1+c[i-1][j-1];
					}
					if(c[i][j]>length) {
						length = c[i][j];
						this.substring=i+"";
					}
				}else {
					c[i][j] = 0;
				}
			}
		}
		this.substring = this.LCS(X);
	}
	private String LCS(String X)
	{
		int end =  Integer.parseInt(this.substring)+1;
		return X.substring(end-this.length,end);
	}
	public void test(String X,String Y)
	{
		this.getLCS(X, Y);
	
		System.out.printf("The longest common substring:\n\t%s"
				+ "\nAnd it's length is %d",this.substring,this.length);
	}
	public static void main(String[] args) {
		LCSubstring l = new LCSubstring();
		String X="AGDAWEA";
		String Y ="ASFAWEGWA";
		l.test(X, Y);
	}

}
















