package test;

import com.qi.util.TreeNode;
import com.qi.util.TreePrintUtil;

/**
 * ���������Ķ�����������任ΪԴ�������ľ���
 * ��������:
 * �������ľ����壺Դ������ 
 *   	    8
 *  	   /  \
 *   	  6   10
 *   	 / \  / \
 *   	5  7 9 11
 *  	���������
 *   	    8
 *   	   /  \
 *  	  10   6
 *   	 / \  / \
 *   	11 9 7  5
 */
public class MirrorTree {
	private static String tree = "";
	public static void Mirror(TreeNode root) 
	{
		if(root==null)	return;
		Mirror(root.left);
		Mirror(root.right);
		TreeNode t = root.left;
		root.left = root.right;
		root.right = t;
		
	}
	public static void main(String[] args) {
		TreeNode m = TreeNode.proATree();
		TreePrintUtil.pirnt(m);
		System.out.println();
		MirrorTree.Mirror(m);
		TreePrintUtil.pirnt(m);
	}
	
}

