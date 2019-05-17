package test2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * ����һ�����������飬����������������ƴ�������ų�һ������
 * ��ӡ��ƴ�ӳ���������������С��һ����������������{3��32��321}��
 * ���ӡ���������������ųɵ���С����Ϊ321323��
 * @author dangqi
 * @time 2018��9��12��
 */
public class PrintMinNumber {
	
	private int res = -1;
	public  String PrintMinNumber(int[] numbers) {
		if(numbers.length==0) return "";
		Integer[] nums = new Integer[numbers.length];
		for(int i=0;i<nums.length;i++) {
			nums[i] = numbers[i];
		}
		Arrays.sort(nums, new Comparator<Integer>() {

			@Override
			public int compare(Integer a,Integer b) {
				return (a+""+b).compareTo(b+""+a);
			}
		});
		StringBuilder s = new StringBuilder();
		for(int i = 0;i<nums.length;i++) {
			s.append(nums[i]);
		}
		return s.toString();
    }
	
	public static void main(String[] args) {
		PrintMinNumber p = new PrintMinNumber();
		System.out.println(p.PrintMinNumber(new int[] {3,5,1,4,2}));
	}
}