package test;

/**
 * ����ĳ��������ǰ���������������Ľ�������ؽ����ö�������
 * ���������ǰ���������������Ľ���ж������ظ������֡�
 * ��������ǰ���������{1,2,4,7,3,5,6,8}�������������{4,7,2,1,5,3,8,6}�����ؽ������������ء�
 */

public class TreeNodeFromPreMid {
	
	
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
    	
		return reConstructBinaryTree( pre, in,
	    		0,pre.length-1,0,in.length-1);
    }
    public TreeNode reConstructBinaryTree(int [] pre,int [] in,
    		int prelo,int prehi,int inlo,int inhi) {
    	if(prehi>=pre.length||prelo>prehi||inlo>inhi)	return null;
    	int t = BinaryS(in, pre[prelo], inlo, inhi);
    	int t2= t-inlo;
    	TreeNode head = new TreeNode(pre[prelo]);
    	if(t==-1)	return null;
    	head.left = reConstructBinaryTree(pre, in,
    			prelo+1,prelo+t2,inlo,t-1);
    	head.right =reConstructBinaryTree(pre, in,
    			prelo+t2+1,prehi,t+1,inhi);
		return head;
    }
    public int BinaryS(int in[],int s,int lo,int hi)
    {
    	for(int i=lo;i<=hi;i++)
    		if(in[i]==s)
    			return i;
    	return -1;
    }
    class TreeNode {
	     int val;
	     TreeNode left;
	     TreeNode right;
	     TreeNode(int x) { val = x; }
	 }
    public void show(TreeNode t)
    {
    	System.out.print(t.val);
    	if(t.left!=null)
    		show(t.left);
    	if(t.right!=null)
    		show(t.right);
    }
    public static void main(String[] args) {
		int pre[]= {1,2,3,4};
		int in[]= {1,2,3,4};
		TreeNodeFromPreMid  tf = new TreeNodeFromPreMid();
		TreeNode t = tf.reConstructBinaryTree(pre, in);
		tf.show(t);

	}
}
