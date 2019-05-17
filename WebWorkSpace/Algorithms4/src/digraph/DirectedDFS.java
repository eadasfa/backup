package digraph;

/**
 * 顶点是否是可达的
 * @author Administrator
 *
 */
public class DirectedDFS {
	private boolean marked[];
	public DirectedDFS(Digraph G,int s)
	{//从定点s出发可以到达的顶点
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	public DirectedDFS(Digraph G,Iterable<Integer> sourse)
	{//从一组顶点出发可以到达的顶点
		marked = new boolean[G.V()];
		for(int s:sourse)
			if(!marked[s])
				dfs(G,s);
	}
	public void dfs(Digraph G,int v)
	{
		marked[v] = true;
		for(int w:G.adj(v))
			if(!marked[w])
				dfs(G,w);
	}
	//v是可达的吗
	public boolean marked(int v)
	{
		return marked[v];
	}
	
}
