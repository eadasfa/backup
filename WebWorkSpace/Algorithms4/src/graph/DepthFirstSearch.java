package graph;

public class DepthFirstSearch {

	public boolean []marked;
	private int count;
	public DepthFirstSearch(Graph G,int s)
	{
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	public void dfs(Graph G, int v) //将与v相连的顶点标记
	{
		//将当前结点标记
		marked[v] = true;
		//将连通数目增加 1 
		count++;
		//遍历与当前顶点相邻的所有顶点
		for(int w:G.adj(v))
			if(!marked[w])
				//通过递归调用以实现深度优先遍历
				dfs(G,w);
	}
	public boolean marked(int v)
	{
		return marked[v];
	}
	public int count()
	{
		return count;
	}
	
}
