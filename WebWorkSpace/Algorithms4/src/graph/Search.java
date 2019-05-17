package graph;

import java.util.HashSet;
import java.util.Set;

public class Search {
	
	public static Set<Integer> SearchVertex(Graph G, int s) 
	{
		Set<Integer> set = new HashSet<Integer>();
		set.add(s);
		SearchVertex(G, s, set) ;
		return set;
	}
	private static void SearchVertex(Graph G, int s,Set<Integer> set) 
	{
		for(int v:G.adj(s))
		{
			if(set.contains(v))
				continue;
			set.add(v);
			SearchVertex(G, v, set) ;
		}
	}
}
