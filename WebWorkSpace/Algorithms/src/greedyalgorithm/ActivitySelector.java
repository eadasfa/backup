package greedyalgorithm;

public class ActivitySelector {
	public static String select(int []s,int []f)
	{	return select(s, f,0);}
	private static String select(int []s,int []f,int i)
	{
		int k = i+1;
		while(k<s.length&&s[k]<f[i])
			k++;
		if(k==s.length)	return ""+i;
		return i+" "+select(s, f, k);
		
	}
	
	public static String select2(int []s,int []f)
	{	
		String r=0+"";
		for(int i=1,last=0;i<s.length;i++)
		{
			if(s[i]>f[last])
			{
				r+=" "+i;
				last = i;
			}
			
		}
		return r;
	}
	public static void main(String[] args) {
		int s[]= {1,3,0,5,3,5,6,8,8,2,12};
		int f[]= {4,5,6,7,9,9,10,11,12,14,16};
		System.out.println(select(s,f));
		System.out.println(select2(s,f));
	}
}
