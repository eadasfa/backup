/**
 * ���ǵ�һ������������������999�˿ڼ���
 * ���Խ��ܴӿͻ��˷�������Ϣ
 */
package com.test1;
import java.io.*;
import java.net.*;
public class MyServer1 {
	
	public static void main(String args[])
	{
		MyServer1 ms1 = new MyServer1();
	}
	public MyServer1() {
		try {
			//��999�˿ڼ���
			ServerSocket ss = new ServerSocket(999);
			System.out.println("����999");
			//�ȴ�ĳ���ͻ������ӣ��ú����᷵��һ��Socket����
			//��û���� ��������������
			Socket s = ss.accept();
			//Ҫ��ȡs�е�����
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String info = br.readLine();
			System.out.println(info);
			
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			pw.println("���Ƿ�����,���յ��������Ϣ��"+" "+info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
