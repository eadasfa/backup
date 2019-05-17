/**
 * 这是客户端连接服务器的后台
 */
package com.qq.client.model;
import java.util.*;

import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.*;
import java.net.*;
import java.io.*;

public class QqClientConServer {
	
	String ip = "localhost";
	int  port = 9999;
	public Socket s;
	//发送第一次请求
	
	public QqClientConServer()
	{

	}
	public boolean SendLoginInfoToServer(Object o)
	{
		boolean b = false;
		try {
			//s = new Socket("10.173.0.28",9999);
			s = new Socket(ip,port);
			//请求登录的包
			Message m = new Message();
			m.setMesType(MessageType.message_want_login);
			//发送请求,要求登录
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms =(Message)ois.readObject();
			//收到服务器的指令，是否允许登录
			if(ms.getMesType().equals(MessageType.message_allowed_login)){
				//允许登录，发送登录信息
				oos.writeObject(o);
				//这里就是验证用户登录的地方
				Message mm = (Message)ois.readObject();
				if(mm.getMesType().equals(MessageType.message_succeed))
				{
					//创建一个该QQ好和服务器端保持通讯连接的线程
					ClientConServerThread ccst = new ClientConServerThread(s);
					//启动该通讯线程
					ccst.start();
					ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst) ;
					b = true;
				}
			}
			
		}catch (Exception e) {
			b=false;
			e.printStackTrace();
		}finally
		{
			
		}
		return b;
	}
	public String SendRegisterMessageToServer(Message m)
	{
		String qqnumber="";
		try {
			s = new Socket(ip,port);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message mm = (Message)ois.readObject();
			if(mm.getMesType().equals(MessageType.message_register_success))
			{
				qqnumber = mm.getGetter();
			}
			else if((mm.getMesType().equals(MessageType.message_register_fail)))
			{
				qqnumber="";
			}
			
		}catch (Exception e) {
			qqnumber="";
			e.printStackTrace();
		}
		return qqnumber;
	}
	
}
