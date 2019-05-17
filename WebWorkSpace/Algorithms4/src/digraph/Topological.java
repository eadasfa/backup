package digraph;

/**
 * 拓扑序列
 * 拓扑:首先是无环、其次使得所有顶点均是有前面的指向排在后面的元素
 * @author Administrator
 *
 */

public class Topological {
	private Iterable<Integer>	order;
	public Topological(Digraph G)
	{
		DirectedCycle c = new DirectedCycle(G);
		if(!c.hasCycle())
		{
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order()
	{
		return order;
	}
	public boolean isDAG()
	{
		return order!=null;
	}

}
