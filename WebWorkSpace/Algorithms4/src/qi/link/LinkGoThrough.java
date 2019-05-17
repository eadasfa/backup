package qi.link;

import java.util.Stack;

import com.qi.util.TreeNode;
import com.qi.util.TreePrintUtil;

import test.PrintTree;
/**
 * 用循环方式遍历二叉树
 * @author dangqi
 * @time 2018年9月23日
 */
public class LinkGoThrough {

	public static void main(String[] args) {
		LinkGoThrough lg = new LinkGoThrough();
		String str = "0  1 2 3 4 5 6 7 8 9";
		TreeNode root = TreeNode.proATree(10);
		PrintTree.PrintFromTopToBottom(root);
		TreePrintUtil.pirnt(root);
		System.out.println("Pre");
		System.out.println(lg.preOrder(root));
		System.out.println(lg.preOrderWithLoop(root));
		System.out.println("\nIn");
		System.out.println(lg.inOrder(root));
		System.out.println(lg.inOrderWithLoop(root));
		
		System.out.println("\nPost");
		System.out.println(lg.postOrder(root));
		System.out.println(lg.postOrderWithLoop(root));
	}
	public String preOrderWithLoop(TreeNode root) {
		if(root == null) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		TreeNode pCur = root;
		Stack<TreeNode> s = new Stack<TreeNode>();
		while(pCur!=null||!s.isEmpty()) {
			while(pCur!=null) {
				str.append(pCur.val);		//这一点与中序遍历不同
				s.push(pCur);
				pCur = pCur.left;
			}
			TreeNode pTop = s.pop();
			pCur = pTop.right;
		}
		return str.toString();
	}
	public String inOrderWithLoop(TreeNode root) {
		if(root == null) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		
		TreeNode pCur = root;
		Stack<TreeNode> s = new Stack<TreeNode>();
		while(pCur!=null||!s.isEmpty()) {
			while(pCur != null) {
				s.push(pCur);
				pCur = pCur.left;
			}
			TreeNode pTop = s.pop();
			str.append(pTop.val); //这一点与先序遍历不同
			pCur = pTop.right;
		}
		return str.toString();
	}
	
	public String postOrderWithLoop(TreeNode root) {
		if(root == null) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		TreeNode pCur = root;
		TreeNode pre = null;
		Stack<TreeNode> s = new Stack<TreeNode>();
		while(pCur!=null||!s.isEmpty()) {
			while(pCur != null) {
				s.push(pCur);
				pCur = pCur.left;
			}
			TreeNode pTop = s.peek();
			if(pTop.right == null||pTop.right==pre) {
				str.append(pTop.val);
				pre = s.pop();	
			}else {
				pCur = pTop.right;
			}
		}
		return str.toString();
	}
	public String preOrder(TreeNode root) {
		if(root == null)
			return "";
		return root.val+preOrder(root.left)+preOrder(root.right);
	}
	public String inOrder(TreeNode root) {
		if(root == null)
			return "";
		return inOrder(root.left)+root.val+inOrder(root.right);
	}
	public String postOrder(TreeNode root) {
		if(root == null)
			return "";
		return postOrder(root.left)+postOrder(root.right)+root.val;
	}
}
