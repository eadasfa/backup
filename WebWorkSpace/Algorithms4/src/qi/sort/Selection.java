package qi.sort;

public class Selection {
	public static void sort(Comparable []a)
	{
		int N = a.length;
		for(int i=0;i<N;i++)
		{
			int min = i;
			for(int j=i+1;j<N;j++)
				if(SortExample.less(a[j],a[min]))
					min = j;
//			System.out.println("exchange:("+a[i]+","+a[min]+")");
			SortExample.exch(a, i, min);;
		}
	}
	public static void main(String[] args) {
//		String []s= {"A","D","C","Q","M","N","H"};
//		Selection.sort(s);
//		SortExample.show(s);
		String str= "ADCEB";
		String s[] = str.split("");
		for(int i=0;i<s.length;i++)
		{
			System.out.print(s[i]);
		}
		System.out.println();
		Selection.sort(s);
		SortExample.show(s);
	}
}
