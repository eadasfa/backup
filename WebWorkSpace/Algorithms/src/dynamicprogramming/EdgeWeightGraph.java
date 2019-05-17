package dynamicprogramming;

import java.util.Vector;
public class EdgeWeightGraph {
	private final int V;//The count of the vertexes
	private int E;//The count of edges
	private Vector<Edge>[] adj;
	public EdgeWeightGraph(int V)
	{
		this.V = V;
		this.E = 0;
		adj = (Vector<Edge>[])new Vector[V];
		for(int i=0;i<V;i++)
		{
			adj[i] = new Vector<Edge>();
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
		Vector<Edge> bag = new Vector<Edge>();
		for(int v=0;v<this.V;v++)
		{
			for(Edge e:adj[v])
				if(v<e.other(v))//avoid double
					bag.add(e);
		}
		return bag;
	}
}
