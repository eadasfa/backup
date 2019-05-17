package graph;

import edu.princeton.cs.algs4.Stack;
/**
 * 使用优先深度搜索查找路径
 * @author Administrator
 *
 */
public class DepthFirstPaths {
	private boolean []marked;//在当前顶点上是否调用过dfp
	private int[]edgeTo;//从起点到一个顶点的已知路径上的最后一个顶点
	private final int s;//起点
	public DepthFirstPaths(Graph G,int s)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s=s;
		dfp(G,s);
	}
	public void dfp(Graph G,int v)
	{
		marked[v] = true;
		for(int w:G.adj(v))
		{
			if(!marked[w])
			{
				edgeTo[w] = v;
				dfp(G,w);
			}
		}
	}
	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v))	return null;
		//java.util.Stack的迭代是从栈底开始的
		//以下用的edu.princeton.cs.algs4.Stack,从栈顶开始
		Stack<Integer> path = new Stack<Integer>();
		int t = v;
		while(t!=this.s)
		{
			path.push(t);
			t = edgeTo[t];
		}
		path.push(s);
		return path;
	}
	public boolean hasPathTo(int v)
	{
		return marked[v];//如果v顶点被标记过，则代表其被访问过，也就是有到达v的路径
	}
	public boolean marked(int v)
	{	return marked[v];	   }
}
