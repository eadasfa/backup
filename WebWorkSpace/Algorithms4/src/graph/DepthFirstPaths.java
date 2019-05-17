package graph;

import edu.princeton.cs.algs4.Stack;
/**
 * ʹ�����������������·��
 * @author Administrator
 *
 */
public class DepthFirstPaths {
	private boolean []marked;//�ڵ�ǰ�������Ƿ���ù�dfp
	private int[]edgeTo;//����㵽һ���������֪·���ϵ����һ������
	private final int s;//���
	public DepthFirstPaths(Graph G,int s)
	{
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s=s;
		dfp(G,s);
	}
	public void dfp(Graph G,int v)
	{
		marked[v] = true;
		for(int w:G.adj(v))
		{
			if(!marked[w])
			{
				edgeTo[w] = v;
				dfp(G,w);
			}
		}
	}
	public Iterable<Integer> pathTo(int v)
	{
		if(!hasPathTo(v))	return null;
		//java.util.Stack�ĵ����Ǵ�ջ�׿�ʼ��
		//�����õ�edu.princeton.cs.algs4.Stack,��ջ����ʼ
		Stack<Integer> path = new Stack<Integer>();
		int t = v;
		while(t!=this.s)
		{
			path.push(t);
			t = edgeTo[t];
		}
		path.push(s);
		return path;
	}
	public boolean hasPathTo(int v)
	{
		return marked[v];//���v���㱻��ǹ���������䱻���ʹ���Ҳ�����е���v��·��
	}
	public boolean marked(int v)
	{	return marked[v];	   }
}
