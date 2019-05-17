package test;

import java.util.ArrayList;
import java.util.List;
/**
 * 输入n个整数，找出其中最小的K个数。
 * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
/**
 * 思路:
 */
public class GetLeastKNumbers {
	public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
		ArrayList<Integer> list = new ArrayList<>();
		if(input==null||input.length==0||k>input.length)
			return list;
        sort(input,0,input.length);
        for(int i=0;i<k;i++) {
        	list.add(input[i]);
        }
        return list;
    }
	public static void sort(int[] input, int lo, int hi) {
		if(lo>=hi) return;
		int it = lo;
		int i = lo+1;
		int gt = hi;
		int v = input[lo];
		while(i<gt) {
			if(input[i]<v) swap(input, i++, it++);
			else if(input[i]>v) swap(input, i,--gt);
			else i++;
		}
		sort(input,lo,it-1);
		sort(input,gt,hi);
	}
	public static void swap(int[] input, int i, int j) {
		int t = input[i];
		input[i] = input[j];
		input[j] = t;
	}
}
