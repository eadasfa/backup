package graph;
/**
 * 使用优先深度搜索找出所有的联通分量
 * @author Administrator
 *
 */
public class CC {
	private boolean[]marked;
	private int []id;//分量标号
	private int count;//分量的数量
	public CC(Graph G)
	{
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int s=0;s<G.V();s++)
		{
			if(marked[s])	continue;
			dfs(G,s);
			count++;
		}
	}
	private void dfs(Graph G,int v)
	{
		marked[v] = true;
		id[v] = count;
		for(int w:G.adj(v))
		{
			if(!marked[w])
			{
				dfs(G,w);
			}
		}
	}
	public boolean connected(int v,int w)
	{	return id[v]==id[w];	}
	public int id(int v)
	{	return id[v];			}
	public int count()
	{	return count;			}
}
