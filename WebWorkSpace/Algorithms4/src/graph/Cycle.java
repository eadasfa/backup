package graph;
/**
 * 无向图是否有环
 * @author Administrator
 *
 */
public class Cycle {
	private boolean []marked;
	private boolean hasCycle;
	public Cycle(Graph G)
	{
		marked = new boolean[G.V()];
		for(int v=0;v<G.V();v++)
		{
			if(!marked[v])
			{
				dfs(G,v,v);
			}
		}
	}
	private void dfs(Graph G,int v,int last)
	{
		marked[v] = true;
		for(int w:G.adj(v))
		{
			if(!marked[w])
				dfs(G,w,v);
			else if(w!=last)	
			{
				hasCycle = true;
				return ;
			}
		}
	}
	public boolean hasCycle()
	{	return hasCycle;	}
}
