package test;

import java.util.Arrays;

/**
 * 
 *����һ���������飬ʵ��һ�����������������������ֵ�˳��ʹ�����е�����λ�������ǰ�벿�֣�
 *���е�ż��λ��λ������ĺ�벿�֣�����֤������������ż����ż��֮������λ�ò��䡣
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
