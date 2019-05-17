package test;

import java.util.ArrayList;
import java.util.LinkedList;

import com.qi.util.TreeNode;
import com.qi.util.TreePrintUtil;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 */
public class PrintTree {
	public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
		ArrayList<Integer> al = new ArrayList<Integer>();
		if(root == null)	return al;
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		TreeNode temp = null;
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			al.add(node.val);
			if(temp == node) {
				temp = null;
				System.out.println();
			}
			System.out.print(node.val + " ");
			if(node.left != null)
			{
				if(temp == null)
					temp = node.left;
				queue.offer(node.left);
			}
			if(node.right != null)
			{
				if(temp == null)
					temp = node.right;
				queue.offer(node.right);
			}
			
		}
		System.out.println();
		return al;
    }
	public static void main(String[] args) {
		TreeNode root = TreeNode.proATree();
		PrintFromTopToBottom(root);
		System.out.println();
		TreePrintUtil.pirnt(root);
	}
}
