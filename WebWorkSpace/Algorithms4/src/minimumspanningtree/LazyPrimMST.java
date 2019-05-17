package minimumspanningtree;
import edu.princeton.cs.algs4.In;
/**
 * Prim算法延时实现
 */
import edu.princeton.cs.algs4.Queue;
import qi.priorityqueue.MinPQ;

public class LazyPrimMST {
	private boolean[]marked;	//the vertexes of minimum spanning tree
	private Queue<Edge> mst;	//edges of the minimum spanning tree
	private MinPQ<Edge> pq;		//横切边
	private double weight;		//The weight of the minimum spanning tree
	public LazyPrimMST(EdgeWeightGraph G)
	{
		pq = new MinPQ<Edge>(G.V());
		mst = new Queue<Edge>();
		marked = new boolean[G.V()];
		visit(G,0);
		while(mst.size()!=G.V()-1)
		{
			Edge e = pq.deMin();
			int v = e.either();
			int w = e.other(v);
			if(marked[v]&&marked[w])
				continue;
			mst.enqueue(e);
			weight += e.weight();
			if(marked[v])	visit(G,w);
			else 			visit(G,v);
			
		}
	}
	private void visit(EdgeWeightGraph G,int v)
	{
		//标记顶点v并将所有连接v和未被标记顶点的边加入pq
		marked[v] = true;
		for(Edge e:G.adj(v))
		{
			if(!marked[e.other(v)])
				pq.insert(e);
		}
	}
	public Iterable<Edge> edges()
	{	return mst;		}
	public double weight()
	{	return weight;	}
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
