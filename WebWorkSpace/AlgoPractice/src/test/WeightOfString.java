package test;

import java.util.Scanner;

/**
 * 给出 n 个字符串，对于每个 n 个排列 p，按排列给出的顺序(p[0] , p[1] … p[n-1])依次
 * 连接这 n 个字符串都能得到一个长度为这些字符串长度之和的字符串。所以按照这个方法一共可以生成 n! 个字符串。
	一个字符串的权值等于把这个字符串循环左移 i 次后得到的字符串仍和原字符串全等的数量，
	i 的取值为 [1 , 字符串长度]。求这些字符串最后生成的 n! 个字符串中权值为 K 的有多少个。
	注：定义把一个串循环左移 1 次等价于把这个串的第一个字符移动到最后一个字符的后面。
	
	输入描述:
	每组测试用例仅包含一组数据，每组数据第一行为两个正整数 n, K ， n 的大小不超过 8 ， 
	K 不超过 200。接下来有 n 行，每行一个长度不超过 20 且仅包含大写字母的字符串。
	
	输出描述:
	输出一个整数代表权值为 K 的字符串数量。
	输入例子1:
		3 2
		AB
		RAAB
		RA
	
	输出例子1:
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
