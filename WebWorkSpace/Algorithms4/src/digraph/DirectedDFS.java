package digraph;

/**
 * �����Ƿ��ǿɴ��
 * @author Administrator
 *
 */
public class DirectedDFS {
	private boolean marked[];
	public DirectedDFS(Digraph G,int s)
	{//�Ӷ���s�������Ե���Ķ���
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	public DirectedDFS(Digraph G,Iterable<Integer> sourse)
	{//��һ�鶥��������Ե���Ķ���
		marked = new boolean[G.V()];
		for(int s:sourse)
			if(!marked[s])
				dfs(G,s);
	}
	public void dfs(Digraph G,int v)
	{
		marked[v] = true;
		for(int w:G.adj(v))
			if(!marked[w])
				dfs(G,w);
	}
	//v�ǿɴ����
	public boolean marked(int v)
	{
		return marked[v];
	}
	
}
