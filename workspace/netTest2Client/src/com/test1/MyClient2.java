/**
 * ���ǿͻ���
 */
package com.test1;
import java.net.*;
import java.nio.Buffer;
import java.io.*;
public class MyClient2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient2 mc = new MyClient2();
	}

	public MyClient2() {
		
		try {
			//���ӷ������˿�
			Socket s = new Socket("127.0.0.1",999);
			//����
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			//���ܷ�������Ϣ
			InputStreamReader isr1 = new InputStreamReader(s.getInputStream());
			BufferedReader br1 = new BufferedReader(isr1);
			//����̨��������
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			while(true)
			{
				System.out.println("��������Ҫ�Է�����˵�Ļ���");
				//�ͻ����ȴӿ���̨����
				String info = br.readLine();
				//Ȼ���͵�������
				pw.println(info);
				//���ܴӷ����������Ļ�
				String res = br1.readLine();
				System.out.println("������˵��"+res);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
