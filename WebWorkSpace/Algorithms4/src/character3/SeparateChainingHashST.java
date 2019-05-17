package character3;

import edu.princeton.cs.algs4.Queue;
/**
 * 基于拉链法的散列表
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class SeparateChainingHashST <Key extends Comparable<Key>,Value>{
	private int N;//键值对总数
	private int M;//散列表大小
	private SequentialSearchST<Key,Value> st[];
	public SeparateChainingHashST()
	{
		this(997);
	}
	public SeparateChainingHashST(int M)
	{
		this.M = M;
		st = (SequentialSearchST<Key,Value>[])new SequentialSearchST[M];
		for(int i=0;i<M;i++)
		{
			st[i] = new SequentialSearchST<Key,Value>();
//			System.out.println(st[i]==null);
		}
	}
	public int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}
	public Value get(Key key)
	{
		return (Value) st[hash(key)].get(key);
	}
	public void put(Key key,Value val)
	{
		st[hash(key)].put(key, val);
	}
	public void delete(Key key)
	{
		st[hash(key)].delete(key);
	}
	public Iterable<Key> keys()
	{
		Queue<Key> queue = new Queue<Key>();
		for(int i=0;i<M;i++)
		{
			for(Key k:st[i].keys())
				queue.enqueue(k);
		}
		return queue;
	}
	public static void main(String[] args) {
		SeparateChainingHashST<String,Integer> st = new SeparateChainingHashST<String, Integer>(10);
	}
}
