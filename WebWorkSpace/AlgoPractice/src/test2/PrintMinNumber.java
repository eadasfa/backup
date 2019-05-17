package test2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
 * 则打印出这三个数字能排成的最小数字为321323。
 * @author dangqi
 * @time 2018年9月12日
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
