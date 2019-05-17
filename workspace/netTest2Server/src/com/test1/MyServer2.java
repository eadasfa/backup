/**
 * 功能 ：监听999端口
 * 通过控制台，输入回送给客户端
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
			//在999端口监听
			ServerSocket ss = new ServerSocket(999);
			
			//等待连接
			Socket s = ss.accept();
			
			//先接受客户端发送的信息
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new  BufferedReader(isr);
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			//接受从控制台输入的信息
			InputStreamReader isr2 = new InputStreamReader(System.in);
			BufferedReader br2 = new BufferedReader(isr2);
			while(true)
			{
				String infoFromClient = br.readLine();
				System.out.println("客户端发送的消息："+infoFromClient);
				
				//接受从控制台输入的信息
				System.out.println("请输入你希望对客户端说的话：");
				String response = br2.readLine();
				//把从控制台接受的信息，返回
				pw.println(response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
