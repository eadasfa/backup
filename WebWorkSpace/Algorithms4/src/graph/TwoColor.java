package graph;
/**
 * 是否是二分图
 * @author Administrator
 *
 */
public class TwoColor {
	private boolean []marked;
	private boolean []colors;
	private boolean isTwoColor=true;
	public TwoColor(Graph G)
	{
		marked = new boolean[G.V()];
		colors = new boolean[G.V()];
		for(int v=0;v<G.V();v++)
		{
			if(!marked[v])
			{
				dfs(G,v);
			}
		}
	}
	private void dfs(Graph G,int v)
	{
		marked[v] = true;
		for(int w:G.adj(v))
		{
			if(!marked[w]) {
				colors[w] = !colors[v];
			}
			else if(colors[w] == colors[v])
			{
				isTwoColor = false;
				return;
			}
		}
		
	}
	public boolean isTwoColor()
	{	return isTwoColor;	}
}
