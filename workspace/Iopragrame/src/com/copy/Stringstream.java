package com.copy;
import java.io.*;
public class Stringstream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//文件中取出
		FileReader fr=null;
		//写入到文件
		FileWriter fw=null;
		
		try {
			//创建对象
			fr=new FileReader("C:\\Users\\Administrator\\Desktop\\text.txt");
			fw=new FileWriter("E:\\text.txt");
			int n=0;
			//读入到内存
			char[]c=new char[1024];
			while((n=fr.read(c))!=-1)
			{
				//c从0 到 n 组成字符串给s
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
