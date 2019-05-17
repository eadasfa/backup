package com.copy;

import java.io.*;

public class Copy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//思路 先把图片读入到内存----》写入某个文件
		//因为是字节流，因此中能用字节流完成
		
		//File f1 =new File("C:\\Users\\Administrator\\Desktop\\bomb_3.gif");
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\a.jpg");
			fos = new FileOutputStream("E:\\a.gif");
			byte []bytes=new byte[1024];
			int n=0;
			while((n=fis.read(bytes))!=-1)
			{
				//输出到指定位置
				try {
					fos.write(bytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
