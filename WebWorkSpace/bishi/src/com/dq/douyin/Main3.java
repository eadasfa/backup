package com.dq.douyin;

import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		
		Main3 m = new Main3();
		
		System.out.println(m.getRes(s));
		
	}
	public int num=0;
	public int getRes(String s) {
		if(s.length()<4)
			return 0;
		else
		{
			splitTo4(s, "", 1);
			return num;
		}
	}
	public void splitTo4(String s,String prefix,int k) {
		if(k==4) {
			if(s.length()<=0||s.length()>=4||Integer.parseInt(s)>255)
				return;
			else
			{
				System.out.println(prefix+s);
				num++;
			}
		}
		if(s.length()<=0)
			return;
		for(int i=0;i<3&&i<s.length();i++) {
			int t = Integer.parseInt(s.substring(0, i+1));
			if(t<=255) {
				splitTo4(s.substring(i+1), prefix+s.substring(0, i+1)+".", k+1);
			}
		}
	}
}