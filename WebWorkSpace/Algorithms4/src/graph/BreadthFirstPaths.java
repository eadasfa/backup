package graph;

/**
 * 优先广度搜索
 */
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstPaths {

	private boolean []marked;
	private int []edgeTo;
	private final int s;
	public BreadthFirstPaths(Graph G,int s)
	{
		this.s = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		bfs(G,s);
	}
	private void bfs(Graph G, int s)
	{
		Queue<Integer> queue = new Queue<Integer>();
		marked[s] = true;
		queue.enqueue(s);
		while(!queue.isEmpty())
		{
			int v = queue.dequeue();
			for(int w:G.adj(v))
			{	
				if(!marked[w])
				{
					edgeTo[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
			}
		}
	}
	public boolean hasPathTo(int w)
	{
		return marked[w];
	}
	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v))	return null;
		Stack<Integer> stack = new Stack<Integer>();
		int t=v;
		while(t!=this.s)
		{
			stack.push(t);
			t = edgeTo[t];
		}
		stack.push(s);
		return stack;
	}
	
}
