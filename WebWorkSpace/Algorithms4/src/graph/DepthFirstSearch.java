package graph;

public class DepthFirstSearch {

	public boolean []marked;
	private int count;
	public DepthFirstSearch(Graph G,int s)
	{
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	public void dfs(Graph G, int v) //����v�����Ķ�����
	{
		//����ǰ�����
		marked[v] = true;
		//����ͨ��Ŀ���� 1 
		count++;
		//�����뵱ǰ�������ڵ����ж���
		for(int w:G.adj(v))
			if(!marked[w])
				//ͨ���ݹ������ʵ��������ȱ���
				dfs(G,w);
	}
	public boolean marked(int v)
	{
		return marked[v];
	}
	public int count()
	{
		return count;
	}
	
}
