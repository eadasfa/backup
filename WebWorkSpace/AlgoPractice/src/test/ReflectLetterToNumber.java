package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * ÿ���������������һ�����ݣ�ÿ�����ݵ�һ��Ϊһ�������� n ��
 *  �������� n �У�ÿ��һ�����Ȳ����� 12 �ҽ�������д��ĸ A-J ���ַ����� 
 *  n ������ 50�������ٴ���һ���ַ������κ��ַ���������ĸ��
 * �������:
 *	���һ��������ʾ�����Ƕ��١�
 *	��������1:
 *		2
 *		ABC
 *		BCA
 *	
 *	�������1:
 *		1875
 */
public class ReflectLetterToNumber {
	private Map<Character,Long> weight = new HashMap<>();
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = Integer.parseInt(args[0]);//s.nextInt();
		String[] str = new String[n];
		for(int i=0;i<n;i++) {
			str[i] = args[i+1].toUpperCase();//s.next().toUpperCase();
		}
		System.out.println(new ReflectLetterToNumber().getMaxReflect(str));
		
	}
	public long getMaxReflect(String[] str) {
		setWeights(str);
		long[] s = new long['J'-'A'+1];
		for(int i=0;i<s.length;i++) {
			Long lo = weight.get((char)(i+'A'));
			if(lo==null) {
				weight.put((char)(i+'A'), 0L);
				lo = 0L;
			}
			s[i] = lo*10+i;
		}
		Arrays.sort(s);
		int t=0;
		while(!isVaild((char)(s[0]%10+'A'), str)) {
			if(++t<=s.length)
				swap(s, 0,t );
			else return -1;
		}
		return getRes(s);
	}
	private void swap(long []a,int i,int j) {
		long t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	public long getRes(long[] s) {
		long res=0;
		for(int i=0;i<s.length;i++) {
			res+=(s[i]/10)*(i);
		}
		return res;
	}
	public boolean isVaild(char s,String[]str) {
		for(int i=0;i<str.length;i++) {
			if(str[i].charAt(0)==s) return false;
		}
		return true;
	}
	public void setWeights(String[] str) {
		for(int i=0;i<str.length;i++) {
			setWeight(str[i]);
		}
	}
	public void setWeight(String str) {
		int n = str.length()-1;
		for(int i=0;i<=n;i++) {
			Long t = (Long) weight.get(str.charAt(n-i));
			if(t==null||t==0) {
				weight.put(str.charAt(n-i), (long) Math.pow(10, i));
			}else {
				weight.put(str.charAt(n-i), (long) Math.pow(10, i)+t);
			}
		}
	}
}
