package test;

import java.util.ArrayList;

import com.qi.util.TreeNode;
/**
 * ����һ�Ŷ�������һ����������ӡ���������н��ֵ�ĺ�Ϊ��������������·����
 * ·������Ϊ�����ĸ���㿪ʼ����һֱ��Ҷ����������Ľ���γ�һ��·����
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
		//�ݹ�
		dfs(root.left, sum+root.val, res, temp);
		dfs(root.right, sum+root.val, res, temp);
		//�˻�
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