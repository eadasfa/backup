package digraph;

import edu.princeton.cs.algs4.Stack;
/**
 * 环存在否
 * @author Administrator
 *
 */
public class DirectedCycle {
	private boolean marked[];
	private int[]edgeTo;
	private Stack<Integer> cycle;
	private boolean []onStack;
	
	public DirectedCycle(Digraph G)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		onStack = new boolean[G.V()];
		
		for(int v=0;v<G.V();v++)
		{
			if(!marked[v])
				dfs(G,v);
		}
	}
	private void dfs(Digraph G,int v)
	{
		marked[v] = true;//在v节点上调用dfs了
		onStack[v] = true;//表示递归调用的站上的所有顶点
		for(int w:G.adj(v))
		{
			if(hasCycle())	return ;
			if(!marked[w])
			{
				edgeTo[w] = v;
				dfs(G,w);
			}else if(onStack[w]){
				cycle = new Stack<Integer>();
				for(int x=v;x!=w;x=edgeTo[x])
				{// 因为成环,所以沿着v倒退会回到w
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	public boolean hasCycle()
	{
		return cycle!=null;
	}
	public Iterable<Integer> cycle()
	{
		return cycle;
	}
}
