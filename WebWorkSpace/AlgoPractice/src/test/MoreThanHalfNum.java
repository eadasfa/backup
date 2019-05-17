package test;
/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * 如果不存在则输出0。
 */
/**
 * 思路 对于超过一半  同时去除两个不同的数字  留下的就是结果
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
			
			//如果j恰好等于array.length 说明原数组为奇数个，剩余一个 就是结果
			if(j==array.length)	return array[i];
			//如果j恰好等于array.length 说明原数组为偶数数个，剩下两个
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
