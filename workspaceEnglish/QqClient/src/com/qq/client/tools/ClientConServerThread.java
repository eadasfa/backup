/**
 * 客户端连接服务器的线程
 */
package com.qq.client.tools;
import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.*;

import java.net.*;
import java.io.*;
public class ClientConServerThread  extends Thread{
	
	private Socket s;

	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	public ClientConServerThread(Socket s) {
		
		this.s = s;
	}
	//发送我往关闭
	public void sendMessageCloseClient()
	{
		if(this.s.isClosed())
		{
			System.out.println("isClose");
		}
		else
		{
			this.closeClient(MessageType.message_client_active_close);
		}
	}
	public void run()
	{
		while(true)
		{
			//不停的读取从服务器端发来的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m  = (Message)ois.readObject();
				
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{
					//把从服务器取得的消息显示到聊天界面
					QqChat qqChat = ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					qqChat.showMessage(m);
				}else if(m.getMesType().equals(MessageType.message_res_onLineFriend))
				{
					//得到在线信息
					//返回好友在线的信息到聊天列表
					//谁发送 为 接受者
					String getter = m.getGetter();
					//修改相应的好友列表
					QqFriendList qqlist=ManageQqFriendList.getQqFriendList(getter);
					//更新在线好友
					if(qqlist!=null)
					{
						qqlist.updateFriend(m);
					}
					
				}else if(m.getMesType().equals(MessageType.message_server_will_close))
				{
					//接收到服务器要关闭的信息，关闭客户端与服务器的连接
					if(this.s!=null) {
						this.closeClient(MessageType.message_client_will_close);			
						this.s.close();
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void closeClient(String mesType)
	{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(this.s.getOutputStream());
			Message mm = new Message();
			mm.setMesType(mesType);
			oos.writeObject(mm);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
