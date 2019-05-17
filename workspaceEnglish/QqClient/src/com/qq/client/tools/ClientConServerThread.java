/**
 * �ͻ������ӷ��������߳�
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
	//���������ر�
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
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m  = (Message)ois.readObject();
				
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{
					//�Ѵӷ�����ȡ�õ���Ϣ��ʾ���������
					QqChat qqChat = ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					qqChat.showMessage(m);
				}else if(m.getMesType().equals(MessageType.message_res_onLineFriend))
				{
					//�õ�������Ϣ
					//���غ������ߵ���Ϣ�������б�
					//˭���� Ϊ ������
					String getter = m.getGetter();
					//�޸���Ӧ�ĺ����б�
					QqFriendList qqlist=ManageQqFriendList.getQqFriendList(getter);
					//�������ߺ���
					if(qqlist!=null)
					{
						qqlist.updateFriend(m);
					}
					
				}else if(m.getMesType().equals(MessageType.message_server_will_close))
				{
					//���յ�������Ҫ�رյ���Ϣ���رտͻ����������������
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
