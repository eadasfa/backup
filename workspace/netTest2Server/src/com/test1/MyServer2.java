/**
 * ���� ������999�˿�
 * ͨ������̨��������͸��ͻ���
 */
package com.test1;

import java.io.*;
import java.net.*;
public class MyServer2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServer2 ms = new MyServer2();
	}
	public MyServer2() {
		
		try {
			//��999�˿ڼ���
			ServerSocket ss = new ServerSocket(999);
			
			//�ȴ�����
			Socket s = ss.accept();
			
			//�Ƚ��ܿͻ��˷��͵���Ϣ
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new  BufferedReader(isr);
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			//���ܴӿ���̨�������Ϣ
			InputStreamReader isr2 = new InputStreamReader(System.in);
			BufferedReader br2 = new BufferedReader(isr2);
			while(true)
			{
				String infoFromClient = br.readLine();
				System.out.println("�ͻ��˷��͵���Ϣ��"+infoFromClient);
				
				//���ܴӿ���̨�������Ϣ
				System.out.println("��������ϣ���Կͻ���˵�Ļ���");
				String response = br2.readLine();
				//�Ѵӿ���̨���ܵ���Ϣ������
				pw.println(response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
