package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readwenjian {
 public static void main(String []args)
 {
	//��ȡ�ļ�����
			File f = new File("C:\\Users\\Administrator\\Desktop\\text.txt");
			FileInputStream fis=null;
			try {
				//FILEû�ж�д������������Ҫ��FileInputStream
				fis= new FileInputStream(f);
				
				//����һ���ֽ�����
				byte []bytes=new byte[1024];
				int n=0;//�õ�ʵ�ʶ�ȡ�����ֽ�����
				//ѭ����ȡ
				while((n=fis.read(bytes))!=-1)
				{
					//���ֽ�ת��ΪString
					String s=new String(bytes,0,n);
					System.out.println(s);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 }
}
