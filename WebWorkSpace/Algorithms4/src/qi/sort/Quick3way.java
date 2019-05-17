package qi.sort;
/**
 * 三向切分的快速排序
 * @author Administrator
 *
 */
public class Quick3way {
	public static void sort(Comparable []a)
	{
//		StdRandom.shuffle(a);//随机打乱顺序
		sort(a, 0, a.length-1);
	}
	public static void sort(Comparable []a, int lo, int hi)
	{
		if(hi<=lo) return ;
		int it = lo; 
		int i = lo+1;
		int gt = hi;
		Comparable v = a[lo];
		while(i<=gt)
		{
			int cmp = a[i].compareTo(v);
			if(cmp<0) SortExample.exch(a, i++, it++);
			else if(cmp>0) SortExample.exch(a, i, gt--);
			else i++;
		}
		sort(a, lo, it-1);
		sort(a, gt+1, hi);
	}
	public static void main(String[] args) {
		String str= "ASDD";
		String s[] = str.split("");
		for(int i=0;i<s.length;i++)
		{
			System.out.print(s[i]);
		}
		System.out.println();
		Quick3way.sort(s);
		SortExample.show(s);
	}
}
