/**
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨѶ�߳�
 */
package com.qq.server.model;
import com.qq.common.*;
import com.qq.server.model.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.io.*;
public class SerConClientThread extends Thread{
	
	Socket s;
	String uid ;
	public SerConClientThread(Socket s,String uid){
		//�ѷ������͸ÿͻ��˵����Ӹ���s
		this.uid = uid;
		this.s =s;
	}
	//�ø��߳�֪ͨ�����û�
	public void notifyOther(String iam)
	{
		//�õ��������ߵ��˵��߳�
		HashMap hm = ManageClientThread.hm;
		//���ݰ�
		Message m = new Message();
		m.setCon(iam);
		m.setMesType(MessageType.message_res_onLineFriend);
		String []qq={uid};
		System.out.println("iam="+iam+"  "+"uid"+uid);
		//�õ�QQ��Ϊ��qq���ĺ����ʺ�
		UserModel use = new UserModel();
		Vector<String> qqfriend=use.getQqFriend(qq);
		for(int i=0;i<qqfriend.size();i++)
		{
			Iterator it = hm.keySet().iterator();
			//ȡ��һ������
			String qf = qqfriend.get(i);

			while(it.hasNext())
			{
				String onLineUserId=it.next().toString();
				
				System.out.println(qf+"  "+onLineUserId);
				try {
					//ȡ�������˵�id
					
					if(qf.equals(onLineUserId))
					{
						
						m.setStage(true);
						System.out.println("equals"+"  "+m.isStage());
						m.setGetter(onLineUserId);
							ObjectOutputStream oos = new ObjectOutputStream
									(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
							oos.writeObject(m);
					}
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void sendMessageToClient(Message m ){
		try {
			if(!this.s.isClosed())
			{
				ObjectOutputStream oos = new ObjectOutputStream(this.s.getOutputStream());
				oos.writeObject(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void run()
	{
		while(true)
		{
			//������߳̾Ϳ��Խ��ܿͻ��˵���Ϣ
			try {
				if(!s.isClosed()){
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					Message m = (Message)ois.readObject();
					//ת��
					//�Դӿͻ���ȡ�õ���Ϣ���������жϣ�����Ӧ����
					if(m.getMesType().equals(MessageType.message_comm_mes))
					{
						//ȡ�ý����˵�ͨ���߳�
						SerConClientThread sc =  ManageClientThread.getClientThread(m.getGetter());
						ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
						oos.writeObject(m);
					}else if(m.getMesType().equals(MessageType.message_get_onLineFriend))
					{
						//�����ߵķ������ĺ��ѷ��ظ��ͻ���
//						System.out.println(m.getSender()+"Ҫ����");
						Vector res = ManageClientThread.getAllOnLineUserid(m.getSender());
						
//						String a[]=res.split("");
//						System.out.println(m.getSender()+"�ĺ���");
//						for(int i =0;i<a.length;i++)
//						{
//							System.out.println(a[i]+" ");
//						}
						Message m2 = new Message();
						m2.setMesType(MessageType.message_res_onLineFriend);
						m2.setGetter(m.getSender());
						m2.setVector(res);
						m2.setStage(false);
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						oos.writeObject(m2);
					}else if(m.getMesType().equals(MessageType.message_client_will_close))
					{
						if(this.s!=null)
						{
							ManageClientThread.removeSerConClientThread(this.uid);
							this.s.close();
							break;
						}
						
					}else if(m.getMesType().equals(MessageType.message_client_active_close))
					{
						Message m3=new Message();
						m3.setMesType(MessageType.message_server_will_close);
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						oos.writeObject(m3);
					}
					
				}else
				{
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
