package test;

import java.util.Arrays;

/**
 * 
 *输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 *所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 *
 */
public class ReOrderArray {
	public void reOrderArray(int [] array) 
	{
		int m[] = new int[array.length];
		int k = 0;
		for(int i=0;i<array.length;i++)
		{
			if((array[i]&1)==1)	k++;
		}
		for(int i=0,t=0;i<array.length;i++)
		{
			if(array[i]%2==0)	m[k++] = array[i];
			else m[t++] = array[i];
		}
		for(int i=0,t=0;i<array.length;i++)
		{
			array[i] = m[i];
		}
    }
	public void exch(int []a,int i,int j)
	{
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	public static void main(String[] args) {
		int array[] = {1,2,4,3,5,6,7,8,9,10};
		System.out.println(Arrays.toString(array));
		ReOrderArray r = new ReOrderArray();
		r.reOrderArray(array);
		System.out.println(Arrays.toString(array));
	}
}
