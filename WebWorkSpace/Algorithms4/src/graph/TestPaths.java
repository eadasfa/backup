package graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class TestPaths {
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
//		TestPaths.showDFP(G, s);
//		TestPaths.showBFP(G, s);
		TestPaths.testCC(G);
//		System.out.println(new Cycle(G).hasCycle());
		System.out.println(new TwoColor(G).isTwoColor());
	}
	public static void testCC(Graph G)
	{
		System.out.println("Test CC:");
		System.out.println(G);
		CC cc = new CC(G);
		int M = cc.count();
		System.out.println(M+" components");
		
		Bag<Integer> []components;
		components = (Bag<Integer>[]) new Bag[M];
		for(int i=0;i<M;i++)
		{
			components[i] = new Bag<Integer>();
		}
		for(int V=0;V<G.V();V++)
		{
			components[cc.id(V)].add(V);
		}
		for(int i=0;i<M;i++)
		{
			System.out.print("id="+i+": ");
			for(int v:components[i])
				System.out.print(v+" ");
			System.out.println();
		}
	}
	public static void showDFP(Graph G,int s)
	{
		System.out.println("DepthFirstPaths:");
		DepthFirstPaths dfp = new DepthFirstPaths(G,s);
		for(int v=0;v<G.V();v++)
		{
			if(dfp.hasPathTo(v))
			{
				System.out.printf("\t%d to %d: ",s,v);
				for(int w:dfp.pathTo(v))
				{
					if(w==s)	System.out.print(s);
					else		System.out.print("-"+w);
				}
				System.out.println();
			}
		}
	}
	public static void showBFP(Graph G,int s)
	{
		System.out.println("BreadthFirstPaths:");
		BreadthFirstPaths dfp = new BreadthFirstPaths(G,s);
		for(int v=0;v<G.V();v++)
		{
			if(dfp.hasPathTo(v))
			{
				System.out.printf("\t%d to %d: ",s,v);
				for(int w:dfp.pathTo(v))
				{
					if(w==s)	System.out.print(s);
					else		System.out.print("-"+w);
				}
				System.out.println();
			}
		}
	}
}
