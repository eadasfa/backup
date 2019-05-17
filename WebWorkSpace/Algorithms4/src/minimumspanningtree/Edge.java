package minimumspanningtree;

public class Edge implements Comparable<Edge>{

	private int v;//Two vertex
	private int w;
	private double weight;//ШЈжи
	public Edge(int v,int w,double weight) 
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	public double weight()//get the weight of this Edge
	{	return this.weight;	}
	public int either()//one vertex of this edge
	{	return v;	}
	public int other(int vertex)//the other vertex of this edge which is except v
	{	
		if(vertex==this.v)	return w;
		else if(vertex==this.w)	return v;
		else
			try {
				throw new Exception("Inconsistent edge");
			} catch (Exception e) {
				e.printStackTrace();
			}
		return -1;
	}
	
	public int compareTo(Edge that) 
	{
		if(this.weight()<that.weight())
			return -1;
		else if(this.weight()==that.weight())
			return 0;
		return 1;
	}
	public String toString() {
		return String.format("%d-%d %.2f", v,w,weight);
	}
}
