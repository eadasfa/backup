package com.dq.langchao;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Main mn = new Main();
		int t = s.nextInt();
		for(int i=0;i<t;i++) {
			int m = s.nextInt();
			int n = s.nextInt();
			int a = s.nextInt();	
			System.out.println(mn.getMin(m,n,a));
		}
	}
	public int getMin(int m,int n,int a) {
		int res1 = 0;
		int res2=0;
		res1 = m%a==0?m/a:(m/a+1);
		res2 = n%a==0?n/a:(n/a+1);
	
		return res1*res2;
	}
}











