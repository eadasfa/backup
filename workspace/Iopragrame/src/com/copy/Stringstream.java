package com.copy;
import java.io.*;
public class Stringstream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�ļ���ȡ��
		FileReader fr=null;
		//д�뵽�ļ�
		FileWriter fw=null;
		
		try {
			//��������
			fr=new FileReader("C:\\Users\\Administrator\\Desktop\\text.txt");
			fw=new FileWriter("E:\\text.txt");
			int n=0;
			//���뵽�ڴ�
			char[]c=new char[1024];
			while((n=fr.read(c))!=-1)
			{
				//c��0 �� n ����ַ�����s
				String s=new String(c,0,n);
			//	System.out.println(c);
				fw.write(c,0,n);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
