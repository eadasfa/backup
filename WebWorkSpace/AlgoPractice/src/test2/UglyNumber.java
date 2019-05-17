package test2;

import java.util.Arrays;

/**
 * ��ֻ����������2��3��5��������������Ugly Number����
 * ����6��8���ǳ�������14���ǣ���Ϊ������������7�� 
 * ϰ�������ǰ�1�����ǵ�һ���������󰴴�С�����˳��ĵ�N��������
 */
public class UglyNumber {
	public static void main(String[] args) {
		System.out.println(new UglyNumber().GetUglyNumber_Solution(1500));
	}
	public int GetUglyNumber_Solution(int index) {
		if(index<=0) return 0;
		int[] res = new int[index];
		res[0] = 1;
		int nextUglyNumber = 1;
		int pMultiply2 = 0;
		int pMultiply3 = 0;
		int pMultiply5 = 0;
		while(nextUglyNumber<index) {
			int min = Min(res[pMultiply2]*2,res[pMultiply3]*3,res[pMultiply5]*5);
			res[nextUglyNumber++] = min;
			while(res[pMultiply2]*2<=min) pMultiply2++;
			while(res[pMultiply3]*3<=min) pMultiply3++;
			while(res[pMultiply5]*5<=min) pMultiply5++;
		}
		System.out.println(Arrays.toString(res));
        return res[index-1];
    }
	private int Min(int a, int b, int c) {
		int min = a<=b?a:b;
		return min<=c?min:c;
	}
}