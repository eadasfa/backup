package qi.union_find;

import edu.princeton.cs.algs4.StdOut;

public class UF {
	private int []id;//����ID
	private int count;//��������
	int N=1000;
	public UF(int N0)
	{
		count = N0;
		id = new int[N];
		for (int i=0;i < N;i++)
			id[i]=i;
	}
	public int count()
	{
		return count;
	}
	public boolean connected(int p, int q)
	{	return find(p)==find(q);	}
	public int find(int p)
	{ 	return id[p];	}
	public void quick_find_union(int p,int q)
	{
		//��p��q�鲢��ͬһ��������
		int pID = find(p);
		int qID = find(q);
		//���pq�Ѿ�����ͬ�ķ����У�����Ҫ��ȡ�κζ���
		if(pID==qID)	return ;
		//����
		for (int i = 0;i<id.length;i++)
			if(id[i]==pID)	id[i]=pID;
		count--;
	}
	public static void main(String[] args) {
		ReadNumber rn = new ReadNumber(args[0]);
		int N = rn.getANumber();
		System.out.println(N);
		UF uf = new UF(rn.num.length-1);
		int p = rn.getANumber();
		int q = rn.getANumber();
		int t=0;
		while(q!=-1&&p!=-1)
		{
			if(uf.connected(p, q)) continue;
			uf.quick_find_union(p, q);
			StdOut.println(p+" "+q);
			t++;
			p = rn.getANumber();
			q = rn.getANumber();
		}
		System.out.println(uf.count+" components");
		
		
	}
}

















