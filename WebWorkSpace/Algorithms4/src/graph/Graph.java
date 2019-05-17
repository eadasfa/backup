package graph;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
/**
 * 图
 */
public class Graph {
	private final int V;//顶点的数量
	private int E=0;//边的数量
	private Bag<Integer>[]adj;
	public Graph(int V)
	{
		this.V = V;
		this.E = E;
		adj = (Bag<Integer>[])new Bag[V];
		for(int i=0;i<V;i++)
		{
			adj[i] = new Bag<Integer>();
		}
	}
	public Graph(In in)
	{ 
		this(in.readInt());
		int E = in.readInt();
		for(int i=0;i<E;i++)
		{
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v,w);
		}
	}
	public int V() {	return V;	}
	public int E() {	return E;	}
	public void addEdge(int v, int w)
	{
		adj[v].add(w);;//new Integer(w)
		adj[w].add(v);;
		E++;
	}
	public static int degree(Graph G,int v)//计算与v相邻的顶点
	{
		int degree = 0;
		for(int w:G.adj(v))	degree++;
		return degree;
	}
	public static int maxDegree(Graph G)
	{
		int max=0;
		for(int i=0;i<G.V();i++)
		{
			if(degree(G,i)>max)
				max = degree(G,i);
		}
		return max;
	}
	public static double avgDegree(Graph G)
	{
		return G.E*2.0/G.V;
	}
	public static int numberOfSelfLoops(Graph G)
	{
		int count=0;
		for(int i=0;i<G.V;i++)
		{
			for(int w:G.adj(i))
				if(w==i) count++;
		}
		return count/2;
	}
	public String toString() 
	{
		String s=this.V()+" vertices, "+this.E()+" edges\n";
		for(int v=0;v<V;v++)
		{
			s+=v+": ";
			for(int w:adj(v))
				s+=w+" ";
			s+="\n";
		}
		return s;
	}
	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}
}








