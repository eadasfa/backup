package com.qi.util;
import java.util.*;
public class TreeNode {
	public int val = 0;
    public TreeNode left = null;
    public TreeNode right = null;
    //默认每个 val的最大值
    private static final int MAX_DEFALUT = 100;
    //结点数
    private final static int N_DEFALUT = 8;
    //不产生节点的可能性
    private final static double NULL_POINT_DEFALUT = 0.07;
    public TreeNode(int val) {
        this.val = val;
    }
    
    //产生一个随机的树
    public static TreeNode proATree() {
    	return proATree(MAX_DEFALUT,N_DEFALUT);
    }
    public static TreeNode proATree(int max) {
    	return proATree(max,N_DEFALUT);
    }
    public static TreeNode proATree(int max,int n) {
    	Random r = new Random();
    	TreeNode root = new TreeNode(r.nextInt(max));
    	LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.offer(root);
    	int i=1;
    	while(i<n &&!queue.isEmpty()) {
    		TreeNode node = queue.poll();
    		if(i<n&&Math.random()>NULL_POINT_DEFALUT)
    		{
    			node.left = new TreeNode(r.nextInt(max));
    			queue.offer(node.left);
    			i++;
    		}
    		if(i<n&&Math.random()>NULL_POINT_DEFALUT)
    		{
    			node.right = new TreeNode(r.nextInt(max));
    			queue.offer(node.right);
    			i++;
    		}
    	}
    	return root;
    }
    public static TreeNode proATree(int []t) {
    	if(t==null||t.length==0) return null;
    	TreeNode root = new TreeNode(t[0]);
    	LinkedList<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	for(int i=1;i<t.length&&!queue.isEmpty();i++) {
    		TreeNode node = queue.poll();
    		node.left = new TreeNode(t[i++]);
    		queue.offer(node.left);
    		if(i<t.length) {
    			node.right = new TreeNode(t[i]);
    			queue.offer(node.right);
    		}	
    	}
    	return root;
    }
    
    public TreeNode getLeftChild() {
    	return this.left;
    }

    public TreeNode getRightChild() {
    	return this.right;
    }
    public String toString() {
    	return "["+this.val+"]";
    }
    public static void main(String[] args) {
		TreeNode root = TreeNode.proATree(new int[] {1,2,3,4,5,6,7,8,9});
		TreePrintUtil.PrintFromTopToBottom(root);
		TreePrintUtil.pirnt(root);
	}
}
