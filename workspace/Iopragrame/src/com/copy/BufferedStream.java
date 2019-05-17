package com.copy;
import java.io.*;
/*
 * 缓冲字符流
 */
public class BufferedStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BufferedReader br=null;
		BufferedWriter bw=null;
		try{
			//想创建FileReader对象
			FileReader fr=new FileReader("C:\\Users\\Administrator\\Desktop\\text.txt");
			FileWriter fw=new FileWriter("E:\\b.txt");
			br = new BufferedReader(fr);
			bw = new  BufferedWriter(fw);
			String s="";
			while((s=br.readLine())!=null)
			{
				bw.write(s+"\r\n");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			try {	
				br.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
