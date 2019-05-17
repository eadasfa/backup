/**
 * 这是一个客户端程序，可以连接服务器
 */
package com.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.io.*;
public class MyClient1 {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient1 mc = new MyClient1();
	}
	public MyClient1() {
		try {
			//socket（）就是去连接某个服务器端  "127.0.0.1"服务器ip
			//999端口号
			Socket s = new Socket("127.0.0.1",999);
			
			//如果s即Socket连接成功，就可以向服务器发送数据
			//我们通过pw向s写数据 true表示即使刷新
			PrintWriter pw  = new PrintWriter(s.getOutputStream(),true);
			pw.println("你好，我是客户端");
			//要读取s中的数据
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String info = br.readLine();
			System.out.println(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
