/**
 * 这是第一个服务器程序，让他在999端口监听
 * 可以接受从客户端发来的信息
 */
package com.test1;
import java.io.*;
import java.net.*;
public class MyServer1 {
	
	public static void main(String args[])
	{
		MyServer1 ms1 = new MyServer1();
	}
	public MyServer1() {
		try {
			//在999端口监听
			ServerSocket ss = new ServerSocket(999);
			System.out.println("我是999");
			//等待某个客户端连接，该函数会返回一个Socket连接
			//若没连接 则函数不往下运行
			Socket s = ss.accept();
			//要读取s中的数据
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String info = br.readLine();
			System.out.println(info);
			
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			pw.println("我是服务器,我收到了你的信息："+" "+info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
