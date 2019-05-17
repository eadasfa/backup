package character3;

import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;
/**
 * 基于二分查找的符号表
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class BinarySearchST<Key extends Comparable<Key>, Value>
{
	private Key[] keys;
	private Value[] values;
	private int N=0;
	public BinarySearchST(int capacity)
	{
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}
	public int size()
	{
		return N;
	}
	public boolean isEmpty()
	{
		return N==0;
	}
	public int rank(Key k)
	{
		int lo = 0;
		int hi = N-1;
		while(lo<=hi)
		{
			int mid = lo + (hi-lo)/2;
			int cmp = k.compareTo(keys[mid]);
			if(cmp<0)		hi = mid-1;
			else if(cmp>0)  lo = mid+1;
			else 		 	return mid;
		}
		return lo;
	}
	public int rank(Key k,int lo,int hi)
	{
		if(lo>hi) return lo;
		int mid = (lo+hi)/2;
		int cmp = k.compareTo(keys[mid]);
		if(cmp>0) 		return rank(k,mid+1,hi);
		else if(cmp<0) 	return rank(k,lo,mid-1);
		else 			return mid;
	}
	public void put(Key key, Value value)
	{
		int i = rank(key);
		if(i<N && keys[i].compareTo(key)==0)
		{
			values[i] = value;
		}else {
			for(int j=N;j>i;j--)
			{
				keys[j] = keys[j-1];
				values[j] = values[j-1];
			}
			keys[i] = key; 	values[i] = value;
			N++;
		}
	}
	public Value get(Key key)
	{
		if(isEmpty()) return null;
		int i = rank(key);
		if(i<N&&keys[i].compareTo(key)==0)//若存在
		{
			  	return values[i];
		}
		else 	return null;
			
	}
	public Key min()
	{
		return keys[0];
	}
	public Key max()
	{
		return keys[N-1];
	}
	public Key select(int k)
	{
		return keys[k-1];
	}
	private void resize(int length)
	{
		Key[]k = (Key[]) new Comparable[length];
		Value[] v = (Value[]) new Object[length];
		for(int i=0;i<N;i++)
		{
			k[i] = keys[i];
			v[i] = values[i];
		}
		keys = k;
		values = v;
	}
	public boolean contains(Key k)
	{
		int i = rank(k);
		if(i<N&&keys[i].compareTo(k)==0)
			return true;
		return false;
			
	}
	public void delete(Key key)
	{
		int i = rank(key);
		if(i<N && keys[i].compareTo(key)==0)
		{
			for(int j=i;j+1<N;j++)
			{
				keys[j] = keys[j+1];
				values[j] = values[j+1];
			}
			N--;
			keys[N] = null;
			values[N] = null;
		}
	}
	public Iterable<Key> keys(Key lo, Key hi)
	{
		Queue<Key> q = new Queue<Key>();
		for(int i= rank(lo);i<rank(hi);i++)
		{
			q.enqueue(keys[i]);
		}
		if(this.contains(hi))
			q.enqueue(hi);
		return q;
	}
}





















