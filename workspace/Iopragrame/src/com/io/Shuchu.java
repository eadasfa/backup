package com.io;
import java.io.*;
public class Shuchu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f= new File("C:\\Users\\Administrator\\Desktop\\text.txt");
		FileOutputStream fos=null;
		try {
			String s="ÄãºÃ£¬ÖÐ¹ú£¡";
		
			fos=new FileOutputStream(f);
			fos.write(s.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
