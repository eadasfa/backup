package test;
/**
 * ��������һ�����ֳ��ֵĴ����������鳤�ȵ�һ�룬���ҳ�������֡�
 * ��������һ������Ϊ9������{1,2,3,2,2,2,5,4,2}��
 * ��������2�������г�����5�Σ��������鳤�ȵ�һ�룬������2��
 * ��������������0��
 */
/**
 * ˼· ���ڳ���һ��  ͬʱȥ��������ͬ������  ���µľ��ǽ��
 * @author dangqi
 *
 */
public class MoreThanHalfNum {
	public static void main(String[] args) {
		MoreThanHalfNum n = new MoreThanHalfNum();
		int a[] = {1,1,2,2,2,2,5,4,2};
		System.out.println(n.moreThanHalfNum2(a));
	}
	public int moreThanHalfNum_Solution(int [] array) {
		int n = moreThanHalfNum(array);
		int count=0;
		for(int t = 0;t<array.length;t++)
			if(array[t]==n)
				count++;
	
		if(count>array.length/2)
			return n;
		return 0;		
	}
	public int moreThanHalfNum2(int [] array) {
		
		int count = 0;
		int pre = 0;
		for(int i = 0; i<array.length;i++) {
			if(count == 0) {
				count=1;
				pre = array[i];
				continue;
			}
			if(array[i] == pre) count++;
			else count--;
		}
		int times = 0;
		for(int i = 0; i<array.length;i++) {
			if(array[i] == pre)
				times++;
		}
		if(times>array.length/2) return pre;
		
		return -1;
	}
	public int moreThanHalfNum(int [] array) {
		
		for(int i=0,j=1;i<array.length;i+=2,j+=2) {
			
			//���jǡ�õ���array.length ˵��ԭ����Ϊ��������ʣ��һ�� ���ǽ��
			if(j==array.length)	return array[i];
			//���jǡ�õ���array.length ˵��ԭ����Ϊż��������ʣ������
			if(j==array.length-1)	return array[i];
			
			while(j<array.length&&array[i]==array[j])	j++;
			
			if(j<array.length)
			{
				swap(array, i+1, j);
				j = i+1;
			}
			else 
				return array[i];
		}
		
		return 0;
    }
	private void swap(int []a,int i,int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
