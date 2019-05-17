package test;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 给定 x, k ，求满足 x + y = x | y 的第 k 小的正整数 y 。 | 是二进制的或(or)运算，例如 3 | 5 = 7。

	比如当 x=5，k=1时返回 2，因为5+1=6 不等于 5|1=5，而 5+2=7 等于 5 | 2 = 7。
	
	
	输入描述:
	每组测试用例仅包含一组数据，每组数据为两个正整数 x , k。 满足 0 < x , k ≤ 2,000,000,000。
	
	
	
	输出描述:
	输出一个数y。
	
	
	输入例子1:
	5 1
	
	输出例子1:
	2
 * @author dangqi
 *
 */
public class KOrPlus {
	public static void main(String[] args) {
		
		
		long n = Long.parseLong(args[0]);
		long k = Long.parseLong(args[1]);
		KOrPlus kp = new KOrPlus(n, k);
		System.out.println(kp.getRes());
	}
	private long n;
	private long k;
	public KOrPlus(long n,long k) {
		this.n = n;
		this.k = k;
	}
	public long getRes() {
		StringBuilder kBinary = new StringBuilder(Long.toBinaryString(k));
		StringBuilder nBinary = new StringBuilder(Long.toBinaryString(n));
		int j = nBinary.length()-1;
		for(int i=kBinary.length()-1;i>=0;i--) {
			for(;j>=0&&nBinary.charAt(j)=='1';j--);
			if(j>=0) {
				if(nBinary.charAt(j)!=kBinary.charAt(i)) {
					nBinary.replace(j, j+1, kBinary.charAt(i)+"");
				}
				j--;
			}else {
				nBinary.insert(0, kBinary.charAt(i)+"");
			}
		
		}
		BigInteger bi = new BigInteger(nBinary.toString(), 2);	//转换为BigLong类型
    	return Long.parseLong(bi.toString())-n;		
	}
}

