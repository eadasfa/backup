package character3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import edu.princeton.cs.algs4.Queue;


public class RedBlackBST <Key extends Comparable<Key>, Value>
{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;
	private class Node
	{
		private Key key;
		private Value value;
		private Node left,right;
		private int N;//以此为根的树节点的数量
		private boolean color;//表示有父节点指向自己是的链接的颜色，默认为flase即黑色
		
		public Node(Key key,Value value,int N,boolean color)
		{
			this.key=key;this.value=value;this.N=N;this.color=color;
		}
	}
	public boolean isEmpty()
	{
		return size()==0;
	}
	public void put(Key key,Value value)
	{
		root = put(root,key,value);
		root.color = BLACK;
	}
	private Node put(Node h,Key key,Value value)
	{
		if(h == null)	return new Node(key,value,1,RED);
		int cmp = key.compareTo(h.key);
		if(cmp<0)			h.left  = put(h.left,key,value);
		else if(cmp>0)		h.right = put(h.right,key,value);
		else 				h.value = value;
		if(isRed(h.right)&&!isRed(h.left))		h = rotateLeft(h);
		if(isRed(h.left)&&isRed(h.left.left)) 	h = rotateRight(h);
		if(isRed(h.left)&&isRed(h.right))		flipColors(h);
		h.N = size(h.left) + size(h.right) + 1;
		return h;
	}
	private boolean isRed(Node node)
	{	//测试与父节点的连接是不是红链接
		if(node==null)	return false;
		return node.color; 
	}
	private Node rotateLeft(Node node)
	{//旋转为以上面的结点为基准
		Node x = node.right;
		node.right = x.left;
		x.left = node;
		x.color = node.color;
		node.color = RED;
		x.N = node.N;
		node.N = size(node.left) + size(node.right) +1;
		return x;
	}
	private Node rotateRight(Node node)
	{
		Node x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = node.color;
		node.color = RED;
		x.N = node.N;
		node.N = size(node.left) + size(node.right) + 1;
		return x;
	}
	private void flipColors(Node h)
	{
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
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
	public void deleteMin()
	{
		if(root==null)		return ;	
		if(!isRed(root.left)&&!isRed(root.right))
		{//实际上在向下遍历的过程中root.right永远不可能是红结点
		 //所以以上判断只是判断left是不是红结点
			root.color = RED;//将当前结点变为红色结点
		}
		root = deleteMin(root);
		if(!isEmpty())		root.color = BLACK;
	}
	private Node deleteMin(Node h)
	{
		if(h.left==null)	return null;
		/**
		 * 保证被删除的不是2-node：沿着左链接向下变换，保证当前链接不是2-node
		 *   1.如果当前节点和子节点都是2-node，将这三个组合成一个4-node
		 *   2.要保证左子节点不是2-node，可以从其兄弟中借一个键来形成3-node			 
		 */
		if(!isRed(h.left)&&!isRed(h.right))
		{//实际上在向下遍历的过程中h.right永远不可能是红结点
		 //所以以上判断只是判断left是不是红结点
		 //通过递归变化，当前节点早已经变为红结点
			h = moveRedLeft(h);//将当前结点的做节点变为红结点
		}
		h.left = deleteMin(h.left);
		return balance(h);
	}
	public void deleteMax()
	{
		if(root==null)	return ;
		if(!isRed(root.left)&&!isRed(root.right))
			root.color = RED;
		root = deleteMax(root);
		if(!isEmpty())	root.color = BLACK;
	}
	private Node deleteMax(Node h)
	{
		//若左子节点为红结点，向右旋转使得右子结点变成红结点
		if(isRed(h.left))	h = rotateRight(h);
		//上下两句不能颠倒
		//在最后h.left不可能为黑节点，若有树将不会平衡
		if(h.right == null)	return null;
		
		if(!isRed(h.right)&&!isRed(h.right.left))//判断右结点是不是3节点
		{	//因为3节点是用红节点来模拟的，红节点不可能是右孩子，
			//所以不可能是h.right.right，通过上面的一次左旋转，
			//可以获得与h.right.right同样的效果
			h = moveRedRight(h);//将h.right变为3结点
		}
		h.right = deleteMax(h.right);
		return balance(h);
	}
	public void delete(Key key)
	{
		if(root==null)	return ;
		if(!isRed(root.left)&&!isRed(root.right))
			root.color = RED;
		root = delete(root,key);
		if(!isEmpty())	root.color = BLACK;
	}
	private Node delete(Node h, Key key)
	{  
		if(key.compareTo(h.key)<0) {
			if(!isRed(h.left)&&!isRed(h.right))
			{//实际上在向下遍历的过程中h.right永远不可能是红结点
			 //所以以上判断只是判断left是不是红结点
			 //通过递归变化，当前节点早已经变为红结点
				h = moveRedLeft(h);//将当前结点的做节点变为红结点
			}
			h.left = delete(h.left,key);
		}else {
			if(isRed(h.left))
				h = rotateRight(h);
			//要删除的节点是叶子节点
			if(key.compareTo(h.key)==0 && h.right==null)	
				return null;
			if(key.compareTo(h.key)==0)
			{
				Node x = min(h.right);//h.right肯定不为null，若为null则走上一个if
				h.value = x.value;
				h.key = x.key;
				h.right = deleteMin(h.right);
			}else {//如果值大于当前结点的值
				if(!isRed(h.right)&&!isRed(h.right.left))
				{
					h = moveRedRight(h);
				}
				h.right = delete(h.right,key);
			}
			
		}
		return balance(h);
	}
	private Node balance(Node node)
	{//将当前节点恢复平衡
		if(isRed(node.right)&&!isRed(node.left))	  
			node = rotateLeft(node);
		if(isRed(node.left)&& isRed(node.left.left)) 
			node = rotateRight(node);
		if(isRed(node.left) && isRed(node.right))	
			flipColors(node);
		if(node.right!=null&&isRed(node.right.right))			
			node.right = rotateLeft(node.left);
		node.N = size(node.left) + size(node.right) + 1;
		
		return node;
	}
	private Node moveRedLeft(Node node)
	{
		/**
		 * 两种情况:
		 * 		1.node.right.left为黑节点
		 * 		2.node.right.left为红结点
		 * ps:node.right不可能为红结点（规定和建立树时的规则）
		 */
		flipColors(node);
		if(isRed(node.right.left))
		{
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
			flipColors(node);
		}
		return node;
	}
	public Node moveRedRight(Node node)
	{
		flipColors(node);
		if(isRed(node.left.left))
		{
			node = rotateRight(node);
			flipColors(node);
		}
		return node;
		
	}
	
	public Key max()
	{
		if(root==null)	return null;
		return max(root).key;
	}
	private Node max(Node node)
	{
		if(node.right==null)	return node;
		return max(node.right);
	}
	public Key min()
	{
		if(root==null)	return null;
		return min(root).key;
	}
	private Node min(Node node)
	{
		if(node.left==null)	return node;
		return min(node.left);
	}
	public Iterable<Key> keys()
	{
		if(root==null)	return null;
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
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if(cmplo<0)	keys(x.left,queue,lo,hi);
		if(cmplo<=0&&cmphi>=0)
			queue.enqueue(x.key);
		if(cmphi>0) keys(x.right,queue,lo,hi);
			
	}
	/**
	 * TEST
	 */
	private void layerTraversal (Node node){
        LinkedList<Node> s = new LinkedList<Node>();
        s.add(node);
        Node curNode;
        Node nlast = null;
        Node last = node;
        while(!s.isEmpty()){
            curNode = s.poll();
            System.out.print(curNode.key+" ");
            if(curNode.left != null){
                nlast = curNode.left;
                s.add(curNode.left);
            }
            if(curNode.right != null){
                nlast = curNode.right;
                s.add(curNode.right);
            }
            if(curNode == last){
                System.out.println();
                last = nlast;
            }
        }
    }

    //层序遍历节点的颜色（是否为红）
    private void layerTraversalColor (Node node){
        LinkedList<Node> s = new LinkedList<Node>();
        s.add(node);
        Node curNode;
        Node nlast = null;
        Node last = node;
        while(!s.isEmpty()){
            curNode = s.poll();
            System.out.print(curNode.color+" ");
            if(curNode.left != null){
                nlast = curNode.left;
                s.add(curNode.left);
            }
            if(curNode.right != null){
                nlast = curNode.right;
                s.add(curNode.right);
            }
            if(curNode == last){
                System.out.println();
                last = nlast;
            }
        }
    }

    //先序遍历所有节点的键，
    private void preOrderTraversal(Node node){
        Stack<Node> s = new Stack<Node>();
        Node curNode = null;
        s.push(node);
        while(!s.isEmpty()){
            curNode = s.pop();
            System.out.print(curNode.key+" ");
            if(curNode.right != null)
                s.push(curNode.right);
            if(curNode.left != null)
                s.push(curNode.left);
        }
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> rbt = new RedBlackBST<String, Integer>();
        System.out.print("请输入节点个数：");
        @SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        System.out.println("请依次输入"+num+"个字母");
        for (int i = 1; i <= num; i++){
            String value = s.next();
            rbt.put(value, new Integer(i));
        }
        System.out.println("节点颜色是否为红：");
        rbt.layerTraversalColor(rbt.root);
        System.out.println();

        System.out.println("层序遍历");
        rbt.layerTraversal(rbt.root);
        System.out.println();

        System.out.println("先序遍历");
        rbt.preOrderTraversal(rbt.root);
        System.out.println();


//      rbt.deleteMin();
//      System.out.println("测试删除最小键：");
//      System.out.println("节点颜色是否为红：");
//      rbt.layerTraversalColor(rbt.root);
//      System.out.println();
//      
//      System.out.println("层序遍历");
//      rbt.layerTraversal(rbt.root);
//      System.out.println();
//      
//      System.out.println("先序遍历");
//      rbt.preOrderTraversal(rbt.root);
//      System.out.println();
//      
//      
//      rbt.deleteMax();
//      System.out.println("测试删除最大键");
//      System.out.println("节点颜色是否为红：");
//      rbt.layerTraversalColor(rbt.root);
//      System.out.println();
//      
//      System.out.println("层序遍历");
//      rbt.layerTraversal(rbt.root);
//      System.out.println();
//      
//      System.out.println("先序遍历");
//      rbt.preOrderTraversal(rbt.root);
//      System.out.println();

        System.out.println("\n"+"测试删除任意键");
        System.out.println("请输入要删除的键:");
        String key = s.next();
        rbt.delete(key);
        System.out.println("节点颜色是否为红：");
        rbt.layerTraversalColor(rbt.root);
        System.out.println();

        System.out.println("层序遍历");
        rbt.layerTraversal(rbt.root);
        System.out.println();

        System.out.println("先序遍历");
        rbt.preOrderTraversal(rbt.root);
        System.out.println();
    }
}
























