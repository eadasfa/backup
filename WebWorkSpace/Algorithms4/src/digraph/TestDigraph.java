package digraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class TestDigraph {
	public static void main(String[] args) {
		Digraph G= new Digraph(new In(args[0]));
//		testDirectedDFS(G, args);
		testDirectedCycle(G);
	}
	public static void testDirectedCycle(Digraph G)
	{
		DirectedCycle dc = new DirectedCycle(G);
		if(dc.hasCycle())
			for(int v:dc.cycle())
				System.out.print(v+" ");
		System.out.println();
	}
	public static void testDirectedDFS(Digraph G,String []args)
	{
		Bag<Integer> sources = new Bag<Integer>();
		for(int i=1;i<args.length;i++)
		{
			sources.add(Integer.parseInt(args[i]));
		}
		DirectedDFS reachable = new DirectedDFS(G, sources);
		for(int v=0;v<G.V();v++)
		{
			if(reachable.marked(v))	System.out.print(v+" ");
		}
		System.out.println();
	}
}
