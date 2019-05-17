package minimumspanningtree;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
	private Edge[]edgeTo;//距离树最近的边
	private double[]distTo;//距离树最近的距离
	private boolean []marked;
	private IndexMinPQ<Double> pq;//有效的横切边
	private int weight = 0;
	public PrimMST(EdgeWeightGraph G)
	{
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		marked = new boolean[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		for(int i=1;i<G.V();i++)
		{	distTo[i] = Double.MAX_VALUE;}
		pq.insert(0, 0.0);
		while(!pq.isEmpty()) {
			visit(G,pq.delMin());
		}
	}
	public void visit(EdgeWeightGraph G,int v)
	{
		marked[v] = true;
		weight += distTo[v];
		for(Edge e:G.adj(v))
		{
			int w = e.other(v);
			if(marked[w]) continue;
			if(e.weight()<distTo[w])
			{
				edgeTo[w] = e;
				distTo[w] = e.weight();
				if(pq.contains(w))	pq.changeKey(w, distTo[w]);
				else 				pq.insert(w, distTo[w]);
			}
		}
	}
	public Iterable<Edge> edges()
	{
		Bag<Edge> m = new Bag<Edge>();
		for(int i=1;i<edgeTo.length;i++)
			m.add(edgeTo[i]);
		return m;
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
