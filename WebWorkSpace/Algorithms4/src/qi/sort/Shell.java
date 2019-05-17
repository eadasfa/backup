package qi.sort;
/**
 * œ£∂˚≈≈–Ú
 * @author Administrator
 *
 */


public class Shell {

	public static void sort(Comparable[]a)
	{
		int N = a.length;
		int h=1;//h”––Ú
		while(h<N/3)	h=3*h+1;
		while(h>=1)
		{
			for(int i=h;i<N;i++)
			{
				for(int j = i; j-h>=0&&SortExample.less(a[j], a[j-h]);j-=h)
				{
					SortExample.exch(a, j-h, j);
				}
			}
			h/=3;
		}
	}
	public static void main(String[] args) {
		String []s= {"A","D","C","Q","M","N","H"};
		Insertion.sort(s);
		SortExample.show(s);
	}
}
