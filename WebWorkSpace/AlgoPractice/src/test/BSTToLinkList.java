package test;
import java.util.Arrays;
import java.util.LinkedList;

import com.qi.util.TreeNode;
import com.qi.util.TreePrintUtil;
/**
 * ����һ�ö��������������ö���������ת����һ�������˫������
 * Ҫ���ܴ����κ��µĽ�㣬ֻ�ܵ������н��ָ���ָ��
 */
public class BSTToLinkList {
	private TreeNode pre=null;//javaȫ��ֵ����  
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
	//��һ�ַ���
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
	//�ڶ��ַ���
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
	
	//�����ַ���
	public TreeNode Convert3(TreeNode node) {
		if(node==null) return null;
		convertNode(node,null);
		TreeNode temp = node;
		while(temp.left!=null) {
			temp = temp.left;
		}
		return temp;
	}
	//��Ϊjava�ж���ֵ���� �ڵݹ��иı���pLashNodeInList���ö�ԭ����pLashNodeInList������Ӱ�� 
	//����������ʹ�÷���ֵ�ķ�ʽ���޸�ԭ����pLashNodeInList����
	private TreeNode convertNode(TreeNode node, TreeNode pLashNodeInList) {
		if(node == null)  return pLashNodeInList;
		//������ڵ�
		pLashNodeInList = convertNode(node.left,pLashNodeInList);
		
		node.left = pLashNodeInList;
		if(pLashNodeInList!=null) {
			pLashNodeInList.right = node;
		}
		pLashNodeInList = node;
		//�����ҽڵ�
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
