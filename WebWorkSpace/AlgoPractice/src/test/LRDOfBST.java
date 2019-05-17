package test;
/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 * BST:binary sort tree  二叉搜索树
 *  	左结点<根节点<右结点
 *  ps:
 *BST的后序序列的合法序列是，对于一个序列S，最后一个元素是x （也就是根），
 *如果去掉最后一个元素的序列为T，那么T满足：T可以分成两段，前一段（左子树）小于x，
 *后一段（右子树）大于x，且这两段（子树）都是合法的后序序列。
 */
public class LRDOfBST {
	public boolean VerifySquenceOfBST(int [] sequence) {
		if(sequence == null || sequence.length == 0)
			return false;
		return VerifySquenceOfBST(sequence, 0, sequence.length-1);
    }
	/**
	 * @param sequence
	 * @param lo 下限(包括)
	 * @param hi 上限(包括)
	 * @return
	 */
	private boolean VerifySquenceOfBST(int [] sequence,int lo, int hi ) {
		//递归终点
		if(lo>=hi)	return true;
		int i = lo;
		//后序遍历 前一段（左子树）小于x
		while(i<=hi-1&&sequence[i++]<=sequence[hi]);
		int mid = --i ;
		//后一段（右子树）大于x
		if(!(i==hi-1&&sequence[i]<=sequence[hi])) {
			while(i<=hi-1) 
				if(sequence[i++]<=sequence[hi])
					return false;
		}
		return VerifySquenceOfBST(sequence,lo,mid-1)
				&&VerifySquenceOfBST(sequence, mid, hi-1) ;
    }
	public static void main(String[] args) {
		int sequence[] = {};
		LRDOfBST lrd = new LRDOfBST();
		System.out.println(lrd.VerifySquenceOfBST(sequence));
	}
}
