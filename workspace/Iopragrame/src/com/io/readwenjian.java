package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readwenjian {
 public static void main(String []args)
 {
	//读取文件内容
			File f = new File("C:\\Users\\Administrator\\Desktop\\text.txt");
			FileInputStream fis=null;
			try {
				//FILE没有读写能力，所以需要个FileInputStream
				fis= new FileInputStream(f);
				
				//定义一个字节数组
				byte []bytes=new byte[1024];
				int n=0;//得到实际读取到的字节数；
				//循环读取
				while((n=fis.read(bytes))!=-1)
				{
					//把字节转化为String
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
