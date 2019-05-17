package character3;

public class LinearProbingHashST<Key,Value> {
	private int N;		//符号表的键值对的数量
	private int M;		//线性探测表的大小
	private Key[]keys;
	private Value[]vals;
	public LinearProbingHashST()
	{
		this(16);
	}
	public LinearProbingHashST(int M)
	{
		this.M = M;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}
	public int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}
	public void put(Key key, Value val)
	{
		if(N>=M/2) resize(M*2);
		int i;
		for(i=hash(key);keys[i]!=null;i = (i+1)%M)
		{
			if(keys[i].equals(key))	
			{
				vals[i] = val;return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}
	public Value get(Key key)
	{
		for(int i=hash(key);keys[i]!=null;i++)
		{
			if(keys[i].equals(key))
				return vals[i];
		}
		return null;
	}
	public boolean contain(Key key)
	{
		return get(key)!=null;
	}
	public void delete(Key key)
	{
		if(!contain(key)) return ;
		int i;
		for(i=hash(key);!keys[i].equals(key); i = (i+1)%M);
		keys[i] = null;
		vals[i] = null;
		for(i=(i+1)%M;keys[i]!=null;i=(i+1)%M)
		{
			Key k = keys[i];
			Value v = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(k,v);
		}
		N--;
		if(N>0&&N<=M/8)	resize(M/2);
	}
	private void resize(int capacity)
	{
		LinearProbingHashST<Key,Value> st = new LinearProbingHashST<Key, Value>(capacity);
		for(int i=0;i<M;i++)
		{
			if(keys[i] != null)
				st.put(keys[i], vals[i]);
		}
		keys = st.keys;
		vals = st.vals;
		this.M = st.M;
	}
	public void show()
	{
		for(int i=0;i<M;i++)
		{
			if(keys[i] != null)
				System.out.print(keys[i]+" ");
//			else
//				System.out.println("null");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		String str="Z Y X W V U T S R Q P O N M L K J I H G F E D C B A";
		String s[] = str.split(" ");
		LinearProbingHashST<String,Integer> st =
				new LinearProbingHashST<String, Integer>(s.length);
		for(int i=0;i<s.length;i++)
		{
			st.put(s[i], new Integer((int)(Math.random()*100)));
		}
		st.show();
		st.delete("D");
		st.show();
	}
}









