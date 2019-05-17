package test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * 每组测试用例仅包含一组数据，每组数据第一行为一个正整数 n ，
 *  接下来有 n 行，每行一个长度不超过 12 且仅包含大写字母 A-J 的字符串。 
 *  n 不大于 50，且至少存在一个字符不是任何字符串的首字母。
 * 输出描述:
 *	输出一个数，表示最大和是多少。
 *	输入例子1:
 *		2
 *		ABC
 *		BCA
 *	
 *	输出例子1:
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
