package com.dq.douyin;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<Integer>[] lists = new ArrayList[n+1];
		for(int i=0;i<n;i++) {
			lists[i] = new ArrayList<>();
		}
		for(int i=0;i<m;i++) {
			lists[in.nextInt()].add(in.nextInt());
		}
		for(int i=1;i<n+1;i++) {
//			for()
		}
	}
	
}

