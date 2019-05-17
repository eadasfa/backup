package graph;

import edu.princeton.cs.algs4.In;

public class TestSearch {
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		DepthFirstSearch dfs = new DepthFirstSearch(G, s);
		System.out.println("The list of the vertexes connected with 's' is" );
		System.out.print("  ");
		for(int v = 0; v<G.V();v++)
		{
			if(dfs.marked(v))
				System.out.print(v+" ");
		}
		System.out.println();
		System.out.print("The Graph is ");
		if(dfs.count()!=G.V())
			System.out.print("Not ");
		System.out.println("Connected");
	}
}
