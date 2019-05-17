package digraph;
/**
 * 有向图
 */
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Digraph {
	private int V;
	private int E;
	private Bag<Integer> []adj;
	
	public Digraph(int V)
	{
		this.V = V;
		this.E=0;
		adj = (Bag<Integer>[])new Bag[V];
		for(int i=0;i<V;i++)
		{
			adj[i] = new Bag<Integer>();
		}
	}
	public Digraph(In in)
	{
		this(in.readInt());
		int E = in.readInt();
		for(int i=0;i<E;i++)
		{
			addEdge(in.readInt(), in.readInt());
		}
	}
	public int V() {return V;}
	public int E() {return E;}
	public void addEdge(int v, int w)
	{
		adj[v].add(w);
		E++;
	}
	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}
	public Digraph reverse()//将该有向图反转
	{
		Digraph dg = new Digraph(this.V);
		for(int v=0;v<V;v++)
		{
			for(int w:this.adj(v))
			{
				dg.addEdge(w, v);
			}
		}
		return dg;
	}
}
