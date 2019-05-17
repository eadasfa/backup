/**
 * ���ǿͻ������ӷ������ĺ�̨
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
	//���͵�һ������
	
	public QqClientConServer()
	{

	}
	public boolean SendLoginInfoToServer(Object o)
	{
		boolean b = false;
		try {
			//s = new Socket("10.173.0.28",9999);
			s = new Socket(ip,port);
			//�����¼�İ�
			Message m = new Message();
			m.setMesType(MessageType.message_want_login);
			//��������,Ҫ���¼
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(m);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms =(Message)ois.readObject();
			//�յ���������ָ��Ƿ������¼
			if(ms.getMesType().equals(MessageType.message_allowed_login)){
				//�����¼�����͵�¼��Ϣ
				oos.writeObject(o);
				//���������֤�û���¼�ĵط�
				Message mm = (Message)ois.readObject();
				if(mm.getMesType().equals(MessageType.message_succeed))
				{
					//����һ����QQ�úͷ������˱���ͨѶ���ӵ��߳�
					ClientConServerThread ccst = new ClientConServerThread(s);
					//������ͨѶ�߳�
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
