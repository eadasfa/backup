package digraph;
/**
 *顶点的优先深度遍历的  先序、后序、逆后序 
 */
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class DepthFirstOrder {
	private boolean []marked;
	private Queue<Integer> pre;//先序
	private Queue<Integer> post;//后序
	private Stack<Integer> reversePost;//逆后序排序
	public DepthFirstOrder(Digraph G)
	{
		pre = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		for(int i=0;i<G.V();i++)
		{
			if(!marked[i]) dfs(G,i);
		}
	}
	private void dfs(Digraph G,int v)
	{
		marked[v] = true;
		pre.enqueue(v);
		for(int w:G.adj(v))
			if(!marked[w])
				dfs(G,w);
		post.enqueue(v);
		reversePost.push(v);
	}
	public Iterable<Integer> pre()
	{	return pre;			}
	public Iterable<Integer> post()
	{	return post;		}
	public Iterable<Integer> reversePost()
	{	return reversePost;	}
}
