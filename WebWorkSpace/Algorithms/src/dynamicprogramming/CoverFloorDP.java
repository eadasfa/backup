package dynamicprogramming;
public class CoverFloorDP {
	private int width;
	private int height;
	private long DP[][];
	public static void main(String[] args) {
		
		int m = 2;
		int n = 2;
		CoverFloorDP cfdp= new CoverFloorDP();
		CoverFloor cf = new CoverFloor();
		cfdp.test(m, n);
//		cf.test(m, n);
		
	}
	public double test(int m,int n)
	{
		long t1 = System.currentTimeMillis();
		System.out.print("CoverFloorDP:"+getNum(m, n)+"\ntime:");
		double t = (System.currentTimeMillis()-t1)*1.0/1000;
		System.out.println(t+"s");
		return t;
	}
	public long getNum(int m,int n)
	{
		if(m<n) {
			int t = m;
			m = n;
			n = t;
		}
		height = m;
		width = n;
		int allStates = 1<<width;
		DP = new long[height][allStates];
		for(int i=0;i<allStates;i++)
			if(testFirstLine(i))
				DP[0][i] = 1;
		for(int i=1;i<height;i++)
		{
			for(int j=0;j<allStates;j++)
			{
				for(int last=0;last<allStates;last++)
				{
					if(testTwoLine(j, last))
						DP[i][j] += DP[i-1][last];
				}
			}
		}
		return DP[height-1][allStates-1];
	}
	public boolean testFirstLine(int state)
	{
		int i=0;
		while(i<width)
		{
			if((state & (0x1<<i))!=0)
			{
				if(i+1==width||((state&(0x1<<i+1))==0))	
					return false;
				i+=2;
			}
			else i++;
		}
		return true;
	}
	public boolean testTwoLine(int thisState,int lastState)
	{
		int i=0;
		while(i<width)
		{
			if((thisState & (0x1<<i))==0)
			{
				if((lastState & (0x1<<i))==0)
					return false;
				i++;
			}else {
				if((lastState & (0x1<<i))==0)
					i++;
				else if(i+1 == width||
						!( (((thisState & (0x1<<i+1))!=0))&&(((lastState & (0x1<<i+1))!=0))))
					return false;
				else i+=2;
			}
		}
		return true;
	}
	
}
