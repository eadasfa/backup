/**
 * ����һ���ͻ��˳��򣬿������ӷ�����
 */
package com.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.io.*;
public class MyClient1 {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient1 mc = new MyClient1();
	}
	public MyClient1() {
		try {
			//socket��������ȥ����ĳ����������  "127.0.0.1"������ip
			//999�˿ں�
			Socket s = new Socket("127.0.0.1",999);
			
			//���s��Socket���ӳɹ����Ϳ������������������
			//����ͨ��pw��sд���� true��ʾ��ʹˢ��
			PrintWriter pw  = new PrintWriter(s.getOutputStream(),true);
			pw.println("��ã����ǿͻ���");
			//Ҫ��ȡs�е�����
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String info = br.readLine();
			System.out.println(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
