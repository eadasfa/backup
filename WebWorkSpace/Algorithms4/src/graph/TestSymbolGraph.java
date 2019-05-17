package graph;

import edu.princeton.cs.algs4.StdIn;

public class TestSymbolGraph {
	public static void main(String[] args) {
		String filename=args[0];
		String delim = args[1];
		SymbolGraph sg = new SymbolGraph(filename, delim);
		Graph G = sg.G();
		System.out.println(G.V());
		for(String source:sg.keys())
		{	
			for(int w:G.adj(sg.index(source)))
			{
				System.out.println(" "+sg.name(w));
			}
		}
	}
}
