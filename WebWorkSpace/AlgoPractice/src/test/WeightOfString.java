package test;

import java.util.Scanner;

/**
 * ���� n ���ַ���������ÿ�� n ������ p�������и�����˳��(p[0] , p[1] �� p[n-1])����
 * ������ n ���ַ������ܵõ�һ������Ϊ��Щ�ַ�������֮�͵��ַ��������԰����������һ���������� n! ���ַ�����
	һ���ַ�����Ȩֵ���ڰ�����ַ���ѭ������ i �κ�õ����ַ����Ժ�ԭ�ַ���ȫ�ȵ�������
	i ��ȡֵΪ [1 , �ַ�������]������Щ�ַ���������ɵ� n! ���ַ�����ȨֵΪ K ���ж��ٸ���
	ע�������һ����ѭ������ 1 �εȼ��ڰ�������ĵ�һ���ַ��ƶ������һ���ַ��ĺ��档
	
	��������:
	ÿ���������������һ�����ݣ�ÿ�����ݵ�һ��Ϊ���������� n, K �� n �Ĵ�С������ 8 �� 
	K ������ 200���������� n �У�ÿ��һ�����Ȳ����� 20 �ҽ�������д��ĸ���ַ�����
	
	�������:
	���һ����������ȨֵΪ K ���ַ���������
	��������1:
		3 2
		AB
		RAAB
		RA
	
	�������1:
		3
 */
public class WeightOfString {
	public static void main(String[] args) {
//		Scanner s = new Scanner(System.in);
//		int n = s.nextInt();
//		int k = s.nextInt();
//		String[] str = new String[n];
//		for(int i=0;i<n;i++) {
//			str[i] = s.next().toUpperCase();
//		}
		
		int n = Integer.parseInt(args[0]);
		int k = Integer.parseInt(args[1]);
		String[] str = new String[n];
		for(int i=0;i<n;i++) {
			str[i] = args[i+2].toUpperCase();
		}
		WeightOfString ws = new WeightOfString();
		System.out.println(ws.getWeightOfAll(str, k));
	}
	private int k;
	public int getWeightOfAll(String[] str,int k) {
		this.k = k;
		int t = getString("", 0, str);
		return t;
	}
	private int getString(String prefix,int index,String[] str) {
		if(index==str.length) {
			int weight=calculateWeight(new StringBuilder(prefix));
			if(weight == k) {
				return 1;
			}
			return 0;
		}
		int num = 0;
		for(int i=index;i<str.length;i++) {
			swap(str, index, i);
			num += getString(prefix+str[index],index+1,str);
			swap(str, index, i);	
		}
		return num;
	}
	public int calculateWeight(StringBuilder s) {
		String base = s.toString();
		int num = 0;
		for(int i=1;i<=s.length();i++) {
			s.append(s.charAt(0));
			s.replace(0, 1, "");
			if(base.equals(s.toString())) {
				num++;
			}
		}
		return num;
	}
	private void swap(String[] str,int i,int j) {
		if(i == j)	return;
		String s = str[i];
		str[i] = str[j];
		str[j] = s;
	}
}
