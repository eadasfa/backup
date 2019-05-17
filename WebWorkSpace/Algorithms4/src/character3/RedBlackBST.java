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
		private int N;//�Դ�Ϊ�������ڵ������
		private boolean color;//��ʾ�и��ڵ�ָ���Լ��ǵ����ӵ���ɫ��Ĭ��Ϊflase����ɫ
		
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
	{	//�����븸�ڵ�������ǲ��Ǻ�����
		if(node==null)	return false;
		return node.color; 
	}
	private Node rotateLeft(Node node)
	{//��תΪ������Ľ��Ϊ��׼
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
		{//ʵ���������±����Ĺ�����root.right��Զ�������Ǻ���
		 //���������ж�ֻ���ж�left�ǲ��Ǻ���
			root.color = RED;//����ǰ����Ϊ��ɫ���
		}
		root = deleteMin(root);
		if(!isEmpty())		root.color = BLACK;
	}
	private Node deleteMin(Node h)
	{
		if(h.left==null)	return null;
		/**
		 * ��֤��ɾ���Ĳ���2-node���������������±任����֤��ǰ���Ӳ���2-node
		 *   1.�����ǰ�ڵ���ӽڵ㶼��2-node������������ϳ�һ��4-node
		 *   2.Ҫ��֤���ӽڵ㲻��2-node�����Դ����ֵ��н�һ�������γ�3-node			 
		 */
		if(!isRed(h.left)&&!isRed(h.right))
		{//ʵ���������±����Ĺ�����h.right��Զ�������Ǻ���
		 //���������ж�ֻ���ж�left�ǲ��Ǻ���
		 //ͨ���ݹ�仯����ǰ�ڵ����Ѿ���Ϊ����
			h = moveRedLeft(h);//����ǰ�������ڵ��Ϊ����
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
		//�����ӽڵ�Ϊ���㣬������תʹ�����ӽ���ɺ���
		if(isRed(h.left))	h = rotateRight(h);
		//�������䲻�ܵߵ�
		//�����h.left������Ϊ�ڽڵ㣬������������ƽ��
		if(h.right == null)	return null;
		
		if(!isRed(h.right)&&!isRed(h.right.left))//�ж��ҽ���ǲ���3�ڵ�
		{	//��Ϊ3�ڵ����ú�ڵ���ģ��ģ���ڵ㲻�������Һ��ӣ�
			//���Բ�������h.right.right��ͨ�������һ������ת��
			//���Ի����h.right.rightͬ����Ч��
			h = moveRedRight(h);//��h.right��Ϊ3���
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
			{//ʵ���������±����Ĺ�����h.right��Զ�������Ǻ���
			 //���������ж�ֻ���ж�left�ǲ��Ǻ���
			 //ͨ���ݹ�仯����ǰ�ڵ����Ѿ���Ϊ����
				h = moveRedLeft(h);//����ǰ�������ڵ��Ϊ����
			}
			h.left = delete(h.left,key);
		}else {
			if(isRed(h.left))
				h = rotateRight(h);
			//Ҫɾ���Ľڵ���Ҷ�ӽڵ�
			if(key.compareTo(h.key)==0 && h.right==null)	
				return null;
			if(key.compareTo(h.key)==0)
			{
				Node x = min(h.right);//h.right�϶���Ϊnull����Ϊnull������һ��if
				h.value = x.value;
				h.key = x.key;
				h.right = deleteMin(h.right);
			}else {//���ֵ���ڵ�ǰ����ֵ
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
	{//����ǰ�ڵ�ָ�ƽ��
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
		 * �������:
		 * 		1.node.right.leftΪ�ڽڵ�
		 * 		2.node.right.leftΪ����
		 * ps:node.right������Ϊ���㣨�涨�ͽ�����ʱ�Ĺ���
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

    //��������ڵ����ɫ���Ƿ�Ϊ�죩
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

    //����������нڵ�ļ���
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
        System.out.print("������ڵ������");
        @SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        System.out.println("����������"+num+"����ĸ");
        for (int i = 1; i <= num; i++){
            String value = s.next();
            rbt.put(value, new Integer(i));
        }
        System.out.println("�ڵ���ɫ�Ƿ�Ϊ�죺");
        rbt.layerTraversalColor(rbt.root);
        System.out.println();

        System.out.println("�������");
        rbt.layerTraversal(rbt.root);
        System.out.println();

        System.out.println("�������");
        rbt.preOrderTraversal(rbt.root);
        System.out.println();


//      rbt.deleteMin();
//      System.out.println("����ɾ����С����");
//      System.out.println("�ڵ���ɫ�Ƿ�Ϊ�죺");
//      rbt.layerTraversalColor(rbt.root);
//      System.out.println();
//      
//      System.out.println("�������");
//      rbt.layerTraversal(rbt.root);
//      System.out.println();
//      
//      System.out.println("�������");
//      rbt.preOrderTraversal(rbt.root);
//      System.out.println();
//      
//      
//      rbt.deleteMax();
//      System.out.println("����ɾ������");
//      System.out.println("�ڵ���ɫ�Ƿ�Ϊ�죺");
//      rbt.layerTraversalColor(rbt.root);
//      System.out.println();
//      
//      System.out.println("�������");
//      rbt.layerTraversal(rbt.root);
//      System.out.println();
//      
//      System.out.println("�������");
//      rbt.preOrderTraversal(rbt.root);
//      System.out.println();

        System.out.println("\n"+"����ɾ�������");
        System.out.println("������Ҫɾ���ļ�:");
        String key = s.next();
        rbt.delete(key);
        System.out.println("�ڵ���ɫ�Ƿ�Ϊ�죺");
        rbt.layerTraversalColor(rbt.root);
        System.out.println();

        System.out.println("�������");
        rbt.layerTraversal(rbt.root);
        System.out.println();

        System.out.println("�������");
        rbt.preOrderTraversal(rbt.root);
        System.out.println();
    }
}
























