/**
 * 这是客户端
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
			//连接服务器端口
			Socket s = new Socket("127.0.0.1",999);
			//发送
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			//接受服务器信息
			InputStreamReader isr1 = new InputStreamReader(s.getInputStream());
			BufferedReader br1 = new BufferedReader(isr1);
			//控制台接受输入
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			while(true)
			{
				System.out.println("请输入想要对服务器说的话：");
				//客户端先从控制台接受
				String info = br.readLine();
				//然后发送到服务器
				pw.println(info);
				//接受从服务器发来的话
				String res = br1.readLine();
				System.out.println("服务器说："+res);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
