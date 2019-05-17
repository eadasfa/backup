package test;
/**
 * ����һ���������飬�жϸ������ǲ���ĳ�����������ĺ�������Ľ����
 * ����������Yes,�������No���������������������������ֶ�������ͬ��
 * BST:binary sort tree  ����������
 *  	����<���ڵ�<�ҽ��
 *  ps:
 *BST�ĺ������еĺϷ������ǣ�����һ������S�����һ��Ԫ����x ��Ҳ���Ǹ�����
 *���ȥ�����һ��Ԫ�ص�����ΪT����ôT���㣺T���Էֳ����Σ�ǰһ�Σ���������С��x��
 *��һ�Σ�������������x���������Σ����������ǺϷ��ĺ������С�
 */
public class LRDOfBST {
	public boolean VerifySquenceOfBST(int [] sequence) {
		if(sequence == null || sequence.length == 0)
			return false;
		return VerifySquenceOfBST(sequence, 0, sequence.length-1);
    }
	/**
	 * @param sequence
	 * @param lo ����(����)
	 * @param hi ����(����)
	 * @return
	 */
	private boolean VerifySquenceOfBST(int [] sequence,int lo, int hi ) {
		//�ݹ��յ�
		if(lo>=hi)	return true;
		int i = lo;
		//������� ǰһ�Σ���������С��x
		while(i<=hi-1&&sequence[i++]<=sequence[hi]);
		int mid = --i ;
		//��һ�Σ�������������x
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
