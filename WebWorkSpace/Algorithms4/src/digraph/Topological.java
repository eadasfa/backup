package digraph;

/**
 * ��������
 * ����:�������޻������ʹ�����ж��������ǰ���ָ�����ں����Ԫ��
 * @author Administrator
 *
 */

public class Topological {
	private Iterable<Integer>	order;
	public Topological(Digraph G)
	{
		DirectedCycle c = new DirectedCycle(G);
		if(!c.hasCycle())
		{
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	public Iterable<Integer> order()
	{
		return order;
	}
	public boolean isDAG()
	{
		return order!=null;
	}

}
