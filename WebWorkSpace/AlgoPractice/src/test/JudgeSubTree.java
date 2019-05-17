package test;

import com.qi.util.TreeNode;

/**
 * �������ö�����A��B���ж�B�ǲ���A���ӽṹ��
 * ps������Լ��������������һ�������ӽṹ
 */
public class JudgeSubTree {
	public boolean HasSubtree(TreeNode root1,TreeNode root2) {
	    if(root1 == null || root2 == null) return false;
	    //�жϸ��ڵ��Ƿ���ȣ�����ȼ����ж���root1.val=root2.val�Ƿ�������
	    if(root1.val == root2.val && isSubTree(root1, root2))
	    	return true;
	    //�ж�root1.left���Ƿ���root2�ӽṹ
	    if(HasSubtree(root1.left, root2))
	    	return true;
	    return HasSubtree(root1.right, root2);
	    
    }
	private boolean isSubTree(TreeNode root1,TreeNode root2) {
		//������root2�ĵײ�,��ǰ�߶���ͬ
		if(root2 == null)	return true;
		//������root1�ĵײ���root1û����˵�������ӽṹ
		//����ֵ��ͬ 
		if(root1 == null || root1.val != root2.val)
			return false;
		
		else
			return isSubTree(root1.left, root2.left)&&isSubTree(root1.right, root2.right);
	}
}


