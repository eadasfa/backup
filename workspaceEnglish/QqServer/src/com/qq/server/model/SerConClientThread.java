/**
 * 功能：是服务器和某个客户端的通讯线程
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
		//把服务器和该客户端的连接赋给s
		this.uid = uid;
		this.s =s;
	}
	//让该线程通知其他用户
	public void notifyOther(String iam)
	{
		//得到所有在线的人的线程
		HashMap hm = ManageClientThread.hm;
		//数据包
		Message m = new Message();
		m.setCon(iam);
		m.setMesType(MessageType.message_res_onLineFriend);
		String []qq={uid};
		System.out.println("iam="+iam+"  "+"uid"+uid);
		//得到QQ号为“qq”的好友帐号
		UserModel use = new UserModel();
		Vector<String> qqfriend=use.getQqFriend(qq);
		for(int i=0;i<qqfriend.size();i++)
		{
			Iterator it = hm.keySet().iterator();
			//取出一个好友
			String qf = qqfriend.get(i);

			while(it.hasNext())
			{
				String onLineUserId=it.next().toString();
				
				System.out.println(qf+"  "+onLineUserId);
				try {
					//取出在线人的id
					
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
			//这里该线程就可以接受客户端的信息
			try {
				if(!s.isClosed()){
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					Message m = (Message)ois.readObject();
					//转发
					//对从客户端取得的消息进行类型判断，做相应处理
					if(m.getMesType().equals(MessageType.message_comm_mes))
					{
						//取得接受人的通信线程
						SerConClientThread sc =  ManageClientThread.getClientThread(m.getGetter());
						ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
						oos.writeObject(m);
					}else if(m.getMesType().equals(MessageType.message_get_onLineFriend))
					{
						//把在线的服务器的好友返回给客户端
//						System.out.println(m.getSender()+"要好友");
						Vector res = ManageClientThread.getAllOnLineUserid(m.getSender());
						
//						String a[]=res.split("");
//						System.out.println(m.getSender()+"的好友");
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
