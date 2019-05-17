package character3;
import edu.princeton.cs.algs4.Queue;
/**
 * 无序的链表
 * @author Administrator
 *
 * @param <Key>
 * @param <Value>
 */
public class SequentialSearchST <Key extends Comparable<Key>,Value>{
	private Node first;
	private class Node
	{
		private Key key;
		private Value val;
		private Node next;
		public Node(Key key,Value val,Node next)
		{
			this.key = key; this.val = val; this.next = next;
		}
	}
	public Value get(Key key)
	{
		for(Node x = first;x!=null;x=x.next)
		{
			if(x.key.compareTo(key)==0)
				return x.val;
		}
		return null;
	}
	public void put(Key key,Value val)
	{
		for(Node x = first;x!=null;x=x.next)
		{
			if(x.key.compareTo(key)==0)
				x.val = val;
		}
		//新建结点放在头部，并将其后继结点改为原来第一个结点
		first = new Node(key,val,first);
	}
	public void delete(Key key)
	{
		if(first!=null&&key.compareTo(first.key)==0)
		{
			first = first.next;
			return ;
		}
		for(Node x = first;x.next!=null;x=x.next)
		{
			if(key.compareTo(x.next.key)==0)
			{
				x.next = x.next.next;
				return ;
			}
			
		}
	}
	public Iterable<Key> keys()
	{
		Queue<Key> queue = new Queue<Key>();
		for(Node x=first;x!=null;x=x.next)
		{
			queue.enqueue(x.key);;
		}
		return queue;
	}
}
