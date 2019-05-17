package digraph;

import edu.princeton.cs.algs4.Stack;
/**
 * �����ڷ�
 * @author Administrator
 *
 */
public class DirectedCycle {
	private boolean marked[];
	private int[]edgeTo;
	private Stack<Integer> cycle;
	private boolean []onStack;
	
	public DirectedCycle(Digraph G)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		onStack = new boolean[G.V()];
		
		for(int v=0;v<G.V();v++)
		{
			if(!marked[v])
				dfs(G,v);
		}
	}
	private void dfs(Digraph G,int v)
	{
		marked[v] = true;//��v�ڵ��ϵ���dfs��
		onStack[v] = true;//��ʾ�ݹ���õ�վ�ϵ����ж���
		for(int w:G.adj(v))
		{
			if(hasCycle())	return ;
			if(!marked[w])
			{
				edgeTo[w] = v;
				dfs(G,w);
			}else if(onStack[w]){
				cycle = new Stack<Integer>();
				for(int x=v;x!=w;x=edgeTo[x])
				{// ��Ϊ�ɻ�,��������v���˻�ص�w
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	public boolean hasCycle()
	{
		return cycle!=null;
	}
	public Iterable<Integer> cycle()
	{
		return cycle;
	}
}
