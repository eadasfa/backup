package minimumspanningtree;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import qi.priorityqueue.MinPQ;
import qi.union_find.UF;
public class KruskalMST {
	private Queue<Edge> mst ;
	private int weight=0;
	public KruskalMST(EdgeWeightGraph G)
	{
		mst = new Queue<Edge>();
		MinPQ<Edge> pq = new MinPQ<Edge>();
		UF uf = new UF(G.V());
		for(Edge e:G.edges())	pq.insert(e);
		while(!pq.isEmpty()&&mst.size()<G.V()-1)
		{
			Edge e = pq.deMin();
			int v = e.either();
			int w = e.other(v);
			if(uf.connected(v, w))	continue;
			uf.connected(v, w);
			weight += e.weight();
			mst.enqueue(e);
		}
	}
	public Iterable<Edge> edges()
	{
		return mst;
	}
	public int weight()
	{
		return weight;
	}
	public static void main(String[] args) {
		EdgeWeightGraph G = new EdgeWeightGraph(new In(args[0]));
		LazyPrimMST mst = new LazyPrimMST(G);
		for(Edge e:mst.edges())
		{
			System.out.println(e);
		}
		System.out.println(mst.weight());
	}
}
