package dynamicprogramming;

public class Demo {
	public static int maxN(int a[])
	{
		int m[] = new int[a.length];
		for(int i=a.length-1;i>=0;i--)
		{
			if(i == a.length-1 ) 
				m[i] = 1;
			else
			{
				for(int t = i+1;t<m.length;t++)
				{
					if(m[t] > m[i]&&a[t]<=a[i])
						m[i] = m[t];
				}
				m[i]+=1;
			}	
		}
		return m[0];
	}
	public static void main(String[] args) {
		int a[] = {389,207,155,300,299,170,158,65,160};
		System.out.println(maxN(a));
	}
}
