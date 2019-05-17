package test;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * ���� x, k �������� x + y = x | y �ĵ� k С�������� y �� | �Ƕ����ƵĻ�(or)���㣬���� 3 | 5 = 7��

	���統 x=5��k=1ʱ���� 2����Ϊ5+1=6 ������ 5|1=5���� 5+2=7 ���� 5 | 2 = 7��
	
	
	��������:
	ÿ���������������һ�����ݣ�ÿ������Ϊ���������� x , k�� ���� 0 < x , k �� 2,000,000,000��
	
	
	
	�������:
	���һ����y��
	
	
	��������1:
	5 1
	
	�������1:
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
		BigInteger bi = new BigInteger(nBinary.toString(), 2);	//ת��ΪBigLong����
    	return Long.parseLong(bi.toString())-n;		
	}
}

