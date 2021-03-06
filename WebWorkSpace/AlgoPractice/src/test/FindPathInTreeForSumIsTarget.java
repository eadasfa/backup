package test;

import java.util.ArrayList;

import com.qi.util.TreeNode;
/**
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
public class FindPathInTreeForSumIsTarget {
	private static int target;
	public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
		this.target = target;
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
		dfs(root, 0, res, new ArrayList<Integer>());
		return res;
	}
	@SuppressWarnings("unchecked")
	private void dfs(TreeNode root,int sum,
				ArrayList<ArrayList<Integer>> res,
					ArrayList<Integer> temp) {
		if(root==null||sum+root.val>target)	return ;
		Integer t = new Integer(root.val);
		temp.add(t);
		if(sum+root.val==target&&root.left == null&& root.right ==null) 
			res.add((ArrayList<Integer>) temp.clone());
		//递归
		dfs(root.left, sum+root.val, res, temp);
		dfs(root.right, sum+root.val, res, temp);
		//退回
		temp.remove(t);	
	}
	public static void main(String[] args) {

		int t[] = {12,11,0,14,0,0,13,11,0,0};
		TreeNode root = TreeNode.proATree(t);
		PrintTree.PrintFromTopToBottom(root);
		
		FindPathInTreeForSumIsTarget  fp = new FindPathInTreeForSumIsTarget();
		int target = 37;
		for(ArrayList<Integer> t1:fp.FindPath(root, target)) {
			System.out.print(t1.get(0));
			for(int i=1;i<t1.size();i++) {
				System.out.print("-"+t1.get(i));
			}
			System.out.println();
		}
	
	}
}
