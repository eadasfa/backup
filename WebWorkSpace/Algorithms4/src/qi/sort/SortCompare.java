package qi.sort;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
	public static double time(String alg, Comparable[]a)
	{

		Stopwatch timer = new Stopwatch();
		if     (alg.equals("Selection")) 	Selection.sort(a);
		else if(alg.equals("Shell"))		Shell.sort(a);
		else if(alg.equals("Insertion"))	Insertion.sort(a);
		else if(alg.equals("Heap")) 		Heap.sort(a);
		else if(alg.equals("Merge"))		Merge.sort(a);
		else if(alg.equals("MergeBU")) 		MergeBU.sort(a);
		else if(alg.equals("Quick")) 		Quick.sort(a);
		else if(alg.equals("Quick3way")) 	Quick3way.sort(a);
		else if(alg.equals("Count")) 		Count.sort((Integer[])a);
		else System.out.println("Error!");
		return timer.elapsedTime();
	}
	public static double timeRandomInput(String alg, int N, int T)
	{
		return intRandom(alg, N, T);
	}
	public static double timeRandomInput(String alg, int N, int T,String type)
	{
		if(type.equals("int"))	return intRandom(alg, N, T);
		else if(type.equals("double")) return doubleRandom(alg, N, T);
		else return -1;
	}
	public static double doubleRandom(String alg, int N, int T)
	{
		//使用算法alg将T个长度为N的数组排序
		double total = 0.0;
		Double []a = new Double[N];
		for(int i=0;i<T;i++)
		{
			for(int j=0;j<N;j++)
				a[j] = new Double(StdRandom.uniform());
			total +=time(alg,a);
		}
		return total;
	}
	public static double intRandom(String alg, int N, int T)
	{
		//使用算法alg将T个长度为N的数组排序
		double total = 0.0;
		Integer []a = new Integer[N];
		for(int i=0;i<T;i++)
		{
			for(int j=0;j<N;j++)
				a[j] = new Integer((int)StdRandom.uniform()*rom);
			total +=time(alg,a);
		}
		return total;
	}
	static int rom = 100000000;
	public static void main(String[] args) {
		String alg1 = "Count";
		String alg2 = "Quick3way";
		String sort_type = "int";//double,int
		int N = 100000;
		int T = 100;
		double t1 = timeRandomInput(alg1, N, T,sort_type);
		double t2 = timeRandomInput(alg2, N, T,sort_type);
		System.out.printf("alg1:%.6fs\n",t1);
		System.out.printf("alg2:%.6fs\n",t2);
		System.out.print("For "+N+" random "+sort_type+"s\n  "+alg1+" is ");
		System.out.printf("%.4f times faster than %s.",t2/t1,alg2);
	}
}
