package com.qq.server.model;

import com.qq.common.Message;
import com.qq.server.*;
import java.util.*;
public class ManageClientThread {
	
	public static HashMap hm = new HashMap<String ,SerConClientThread>();
	//向hm中添加一个客户端通讯线程
	public static void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	//取出线程
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	//返回在线的人的情况
	public static Vector getAllOnLineUserid(String uid)
	{
		String []qq={uid};
		//得到QQ号为“qq”的好友帐号
		UserModel use = new UserModel();
		Vector<String> qqfriend=use.getQqFriend(qq);
		//用迭代器完成
		Iterator it = hm.keySet().iterator();
		
		for(int i = 0;i<qqfriend.size();i++)
		{
			boolean b = false;
			while(it.hasNext())
			{
				if(it.next().toString().equals(qqfriend.get(i)))
				{
					b=true;
					qqfriend.setElementAt(qqfriend.get(i)+" "+b, i);
				}
			}
			if(!b){
				qqfriend.setElementAt(qqfriend.get(i)+" "+b, i);
			}
		}
		return qqfriend;
	}
	//向所有与服务器相连的客户端发送服务器将要断开的消息
	public static void sendMessageToAll(Message m)
	{
		//用迭代器完成
		Iterator it = hm.keySet().iterator();
		while(it.hasNext())
		{
			String onLineId = it.next().toString();
			ManageClientThread.getClientThread(onLineId).sendMessageToClient(m);		
		}
		
	}
	public static void removeSerConClientThread(String uid)
	{
		
		ManageClientThread.hm.remove(ManageClientThread.getClientThread(uid));
	}
}
