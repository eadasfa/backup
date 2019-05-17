package com.qq.server.model;

import com.qq.common.Message;
import com.qq.server.*;
import java.util.*;
public class ManageClientThread {
	
	public static HashMap hm = new HashMap<String ,SerConClientThread>();
	//��hm�����һ���ͻ���ͨѶ�߳�
	public static void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	//ȡ���߳�
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	//�������ߵ��˵����
	public static Vector getAllOnLineUserid(String uid)
	{
		String []qq={uid};
		//�õ�QQ��Ϊ��qq���ĺ����ʺ�
		UserModel use = new UserModel();
		Vector<String> qqfriend=use.getQqFriend(qq);
		//�õ��������
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
	//������������������Ŀͻ��˷��ͷ�������Ҫ�Ͽ�����Ϣ
	public static void sendMessageToAll(Message m)
	{
		//�õ��������
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
