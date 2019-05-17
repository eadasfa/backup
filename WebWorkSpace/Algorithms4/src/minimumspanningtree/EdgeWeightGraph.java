package minimumspanningtree;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightGraph {
	private final int V;//The count of the vertexes
	private int E;//The count of edges
	private Bag<Edge>[] adj;
	public EdgeWeightGraph(int V)
	{
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[])new Bag[V];
		for(int i=0;i<V;i++)
		{
			adj[i] = new Bag<Edge>();
		}
	}
	public EdgeWeightGraph(In in)
	{
		this(in.readInt());
		int E = in.readInt();
		for(int i=0;i<E;i++)
		{
			addEdge(new Edge(in.readInt(),in.readInt(),in.readDouble()));
		}
	}
	public int V() {	return V;	}
	public int E() {	return E;	}
	public void addEdge(Edge e)
	{
		int v = e.either();
		int w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
	public Iterable<Edge> adj(int v)
	{	return adj[v];}
	public Iterable<Edge> edges()
	{
		Bag<Edge> bag = new Bag<Edge>();
		for(int v=0;v<this.V;v++)
		{
			for(Edge e:adj[v])
				if(v<e.other(v))//avoid double
					bag.add(e);
		}
		return bag;
	}
}
