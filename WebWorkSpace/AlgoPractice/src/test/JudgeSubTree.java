package test;

import com.qi.util.TreeNode;

/**
 * 输入两棵二叉树A，B，判断B是不是A的子结构。
 * ps：我们约定空树不是任意一个树的子结构
 */
public class JudgeSubTree {
	public boolean HasSubtree(TreeNode root1,TreeNode root2) {
	    if(root1 == null || root2 == null) return false;
	    //判断根节点是否相等，若相等继续判断以root1.val=root2.val是否是子树
	    if(root1.val == root2.val && isSubTree(root1, root2))
	    	return true;
	    //判断root1.left中是否有root2子结构
	    if(HasSubtree(root1.left, root2))
	    	return true;
	    return HasSubtree(root1.right, root2);
	    
    }
	private boolean isSubTree(TreeNode root1,TreeNode root2) {
		//到达了root2的底部,即前者都相同
		if(root2 == null)	return true;
		//到达了root1的底部而root1没结束说明不是子结构
		//或者值不同 
		if(root1 == null || root1.val != root2.val)
			return false;
		
		else
			return isSubTree(root1.left, root2.left)&&isSubTree(root1.right, root2.right);
	}
}


