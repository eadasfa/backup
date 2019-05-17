package test;
import java.util.Arrays;
import java.util.LinkedList;

import com.qi.util.TreeNode;
import com.qi.util.TreePrintUtil;
/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class BSTToLinkList {
	private TreeNode pre=null;//java全是值传递  
	private TreeNode head = null;
	public TreeNode chooseMethodNumber(TreeNode root, int methodNumber) {
		TreeNode head = null;
		switch(methodNumber){
		case 1:
			return Convert1(root);
		case 2:
			return Convert2(root);
		case 3:
			return Convert3(root);
		default:
		}
		return head;
	}
	//第一种方法
	public TreeNode Convert1(TreeNode pRootOfTree) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        LDR(pRootOfTree, queue);
        return traList(queue);
    }
	private void LDR(TreeNode node,LinkedList<TreeNode> queue)
	{
		if(node==null) return;
		LDR(node.left, queue);
		queue.offer(node);
		LDR(node.right,queue);
	}
	private TreeNode traList(LinkedList<TreeNode> queue) {
		if(queue.size()==0)
			return null;
		TreeNode head = queue.poll();
		TreeNode temp=null;
		for(temp = head;!queue.isEmpty();temp = temp.right) 
		{
			TreeNode node = queue.poll();
			temp.right = node;
			node.left = temp;
		}
		head.left = null;
		temp.right = null;
		return head;
	}
	public TreeNode Convert2(TreeNode pRootOfTree) {
		LDR2(pRootOfTree);
		return head;
    }
	//第二种方法
	private void LDR2(TreeNode node)
	{
		if(node==null) return;
		LDR2(node.left);

		if(pre == null&&head==null) {
			pre = node;head = node;
		}
		else {
			pre.right = node;
			node.left = pre;
			pre = node;
		}
		LDR2(node.right);
		
	}
	
	//第三种方法
	public TreeNode Convert3(TreeNode node) {
		if(node==null) return null;
		convertNode(node,null);
		TreeNode temp = node;
		while(temp.left!=null) {
			temp = temp.left;
		}
		return temp;
	}
	//因为java中都是值传递 在递归中改变了pLashNodeInList引用对原来的pLashNodeInList变量无影响 
	//所以在这里使用返回值的方式来修改原来的pLashNodeInList引用
	private TreeNode convertNode(TreeNode node, TreeNode pLashNodeInList) {
		if(node == null)  return pLashNodeInList;
		//遍历左节点
		pLashNodeInList = convertNode(node.left,pLashNodeInList);
		
		node.left = pLashNodeInList;
		if(pLashNodeInList!=null) {
			pLashNodeInList.right = node;
		}
		pLashNodeInList = node;
		//遍历右节点
		pLashNodeInList = convertNode(node.right,pLashNodeInList);
		return pLashNodeInList;
	}
	public static void main(String[] args) {
		TreeNode tree = TreeNode.proATree();
		TreePrintUtil.PrintFromTopToBottom(tree);
		TreePrintUtil.pirnt(tree);
		TreeNode head = new BSTToLinkList().chooseMethodNumber(tree, 3);
		while(head!=null) {
			System.out.print(head.val+" ");
			head = head.right;
		}
	}
	
	
}
