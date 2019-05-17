package character3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import edu.princeton.cs.algs4.Queue;

/**
 * 基于二叉查找树的符号表
 * @author Administrator
 *
 */
public class BST <Key extends Comparable<Key>,Value>{
	private Node root;
	private class Node{
		private Key key;
		private Value value;
		private Node left,right;
		private int N;
		private int h;//表示以这个节点为根节点的树的高度
		private int h2;//表示在整个数中所处的层数
		public Node(Key key,Value value,int N,int h)
		{
			this.key=key;this.value=value;this.N=N;this.h=h;
		}
	}
	public int size()
	{
		return size(root);
	}
	private int size(Node node)
	{
		if(node==null)	return 0;
		return node.N;
	}
	public int height()
	{
		return height(root);
	}
	public int height(Node node)
	{
		if(node==null)	return 0;
		return node.h;
	}
	
	public Value get(Key key)
	{
		return get(root,key);
	}
	private Value get(Node node,Key key)
	{
		if(node==null) return null;
		int cmp=key.compareTo(node.key);
		if(cmp>0) 		return get(node.right,key);
		else if(cmp<0) 	return get(node.left,key);
		else   			return node.value;
	}
	public void put(Key key,Value value)
	{
		root = put(root,key,value);
	}
	private Node put(Node node,Key key,Value value)
	{
		if(node==null) 	return new Node(key, value, 1,0);
		int cmp = key.compareTo(node.key);
		if(cmp>0) 		node.right = put(node.right,key,value);
		else if(cmp<0)  node.left  = put(node.left, key,value);
		else 			node.value = value;
		node.N = size(node.left) + size(node.right) + 1;
		int lefth=height(node.left);
		int righth=height(node.right);
		node.h = (lefth>=righth?lefth:righth)+1;
		return node;
	}
	public Key floor(Key key)
	{
		Node x = floor(root,key);
		if(x==null) return null;
		return x.key;
	}
	private Node floor(Node node,Key key)
	{
		if(node == null)	return null;
		int cmp = key.compareTo(node.key);
		if(cmp==0)	return node;
		if(cmp<0)	return floor(node.left,key);
		Node x = floor(node.right,key);
		if(x!=null) 	return x;//右子树的值一定比根节点的值大
		else 			return node;
	}
	public Key ceiling(Key key)
	{
		Node node = ceiling(root,key);
		if(node==null) return null;
		return	node.key;
	}
	private Node ceiling(Node node, Key key)
	{
		if(node == null)	return null;
		int cmp = key.compareTo(node.key);
		if(cmp==0) 	return node;
		if(cmp>0)	return ceiling(node.right,key);
		Node x = ceiling(node.left,key);
		if(x!=null) return x;
		return node;
	}
	public Key min()
	{
		if(root==null) return null;
		return min(root).key;
	}
	private Node min(Node node)
	{
		if(node.left==null)	return node;
		else 				return min(node.left);
	}
	public Key max()
	{
		if(root==null) return null;
		return max(root).key;
	}
	private Node max(Node node)
	{
		if(node.right==null)	return node;
		else 				return max(node.right);
	}
	public Key select(int k)
	{
		Node node = select(root,k);
		if(node==null)	return null;
		return node.key;
	}
	private Node select(Node node,int k)
	{
		if(node == null)	return null;
		int t = size(node.left);
		if(t>k)		return select(node.left,k);
		if(t<k)		return select(node.right,k-(t+1));
		return node;
	}
	public int rank(Key key)
	{
		return rank(root,key);
	}
	private int rank(Node node, Key key)
	{
		if(node == null)	return 0;
		int cmp = key.compareTo(node.key);
		if(cmp==0)	return size(node.left);
		if(cmp<0)	return rank(node.left,key);	
		int t = size(node.left)+1;
		return t+rank(node.right,key);
	}
	public void deleteMin()
	{
		if(root == null)	return;
		root = deleteMin(root);
	}
	private Node deleteMin(Node node)
	{
		if(node.left==null)		return node.right;
		Node x = deleteMin(node.left);
		node.left = x;
		node.N = size(node.left) + size(node.right)+1;
		int lefth=height(node.left);
		int righth=height(node.right);
		node.h = (lefth>=righth?lefth:righth)+1;
		return node;
		
	}
	public void delete(Key key)
	{
		root=delete(root,key);
	}
	private Node delete(Node node, Key key)
	{
		if(node == null)	return null;
		int cmp = key.compareTo(node.key);
		if(cmp>0)		node.right = delete(node.right,key);
		else if(cmp<0)	node.left  = delete(node.left, key);
		else {
			if(node.right==null)	return node.left;
			if(node.left==null)		return node.right;
			Node t = node;
			node = min(t.right);
			node.right = deleteMin(t.right);
			node.left  = t.left;
		}
		node.N = size(node.left) + size(node.right) +1;
		int lefth=height(node.left);
		int righth=height(node.right);
		node.h = (lefth>=righth?lefth:righth)+1;
		return node;
	}
	public Iterable<Key> keys()
	{
		return keys(min(),max());
	}
	private Iterable<Key> keys(Key lo,Key hi)
	{
		Queue<Key> queue = new Queue<Key>();
		keys(root,queue,lo,hi);
		return queue;
	}
	private void keys(Node x,Queue<Key> queue,Key lo,Key hi)
	{
		if(x==null) 	return ;
		int cmpLo = x.key.compareTo(lo);
		int cmpHi = x.key.compareTo(hi);
		if(cmpLo>0)	keys(x.left,queue,lo,hi);
		if(cmpLo>=0&&cmpHi<=0)
			queue.enqueue(x.key);
		if(cmpHi<0)	keys(x.right,queue,lo,hi);
		
	}
	public String toString()
	{
		return show(root);
	}
	public void show()
	{
		for(Key key:keys())
		{
			System.out.println(key+" "+get(key));
		}
		System.out.println();
//		show(root);
	}
	private String show(Node node)
	{
		if(node==null)	return null;
		String space="  ";
		String s="";
		LinkedList<Node> v = new LinkedList<Node>();
		v.add(node);
		node.h2=0;
		int i=0;
		int t=v.size();
		int h=v.get(0).h2;
		int n = size(v.get(0))/2;
		for(int z=0;z<n;z++)
			s+=" ";
		while(i<t)
		{
			for(;i<t;i++)
			{
				Node x = v.get(i);
				if(x!=null)
				{
					if(x.h2!=h)	{
						s+="\n";h=x.h2;
						n-=2;
						for(int z=0;z<n;z++)
							s+=" ";
					}
					s+=x.key.toString()+" ";
					if(x.left!=null)
					{
						v.add(x.left);
						x.left.h2=x.h2+1;
					}else	v.add(null);
					if(x.right!=null)
					{
						v.add(x.right);
						x.right.h2=x.h2+1;
					}else	v.add(null);
				}else {
					s+="  ";
				}	
			}
			t=v.size();
		}
		return s;
	}
	public static void main(String[] args) {
		BST<String,Integer> bst = new BST<String ,Integer >();
		String str="A 2 D 5 E 9 H 6 W 3 R 8 B 5 W 15 G 46 E 56 Q 78 R 96 F 15 A 6";
		String s[]= str.split(" ");
		for(int i=0;i<s.length;i+=2)
		{
			bst.put(s[i], new Integer(Integer.parseInt(s[i+1])));
		}
		System.out.println(bst);

	}
}










