package dynamicprogramming;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DPSearchPaths {
	private int []edgeTo;//The last vertex
	private double []weightV;//the weight in path;
	private final int s;
	public DPSearchPaths(EdgeWeightGraph G,int s)
	{
		this.s = s;
		edgeTo = new int[G.V()];
		weightV = new double[G.V()];

		for(int i=0;i<G.V();i++)
			edgeTo[i] = -1;
		dps(G);
	}
	private void dps(EdgeWeightGraph G)
	{
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(s);
		while(!queue.isEmpty())
		{
			int v = queue.poll();
			for(Edge e:G.adj(v))
			{
				int w = e.other(v);
				if(weightV[w]>0)//weightV[w]>0 so the vertex w must be in the queue or has left the queue
				{
					double t = weightV[v]+e.weight();
					if(t<weightV[w]) {
						weightV[w] = t;
						edgeTo[w] = v;
					}
				}
				else
				{	
					if(w==s)	continue;
					edgeTo[w] = v;
					weightV[w] = weightV[v]+e.weight();
					queue.offer(w);
				}
			}
		}
	}
	public double weightTo(int v)
	{	return weightV[v]; 		}
	private Iterable<Integer> pathTo(int v)
	{
		Stack<Integer> stack = new Stack<Integer>();
		for(int w=v;w!=s;w=edgeTo[w])
			stack.push(w);
		stack.push(s);
		Stack<Integer> s2 = new Stack<Integer>();
		while(!stack.isEmpty())
			s2.push(stack.pop());
		return s2;
	}
	public void showPathTo(int v)
	{
		System.out.print(s+" to "+v+":");
		for(int w:pathTo(v))
		{
			if(w==s)	System.out.print(s);
			else System.out.print("-"+w);
		}
		System.out.printf("\nWeight:%.2f\n",weightV[v]);
	}
	public static void main(String[] args) {
		new Test();
	}
}
class Test
{
	public Test() {
		final String str="16 0 1 5 0 2 3 1 3 1 1 4 3 1 5 6 2 4 8 2 5 7"
				+ " 2 6 6 3 7 6 3 8 8 4 7 3 4 8 5 5 8 3 5 9 3 6"
				+ " 8 8 6 9 4 7 10 2 7 11 2 8 11 1 8 12 2 9 11 3"
				+ " 9 12 3 10 13 3 10 14 5 11 13 5 11 14 2 12 13"
				+ " 6 12 14 6 13 15 4 14 15 3 ";
		String s[] = str.split("\\s+");
		EdgeWeightGraph G = new EdgeWeightGraph(Integer.parseInt(s[0]));
//		System.out.println(s.length);
		for(int i=1;i<s.length;i+=3)
		{
			int v = Integer.parseInt(s[i]);
			int w = Integer.parseInt(s[i+1]);
			double weight = Double.parseDouble(s[i+2]);
			Edge e = new Edge(v, w, weight);
			G.addEdge(e);
		}
		DPSearchPaths dp = new DPSearchPaths(G, 0);
		for(int v=0;v<G.V();v++)
			dp.showPathTo(v);
	}
}
