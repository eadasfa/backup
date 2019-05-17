/**
 * 这是真正的服务器，他在监听，等待QQ连接
 */
package com.qq.server.model;
import java.net.*;
import java.io.*;
import java.util.*;
import com.qq.server.model.*;
import com.qq.common.*;
public class MyQqServer {

	Message ms;
	boolean flag =false;
	Socket s=null;
	ObjectInputStream ois;
	ServerSocket ss;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public MyQqServer() {
		
	}
	public void openServer()
	{
		this.flag=true;
		try {
			//在9999监听
			System.out.println("我是服务器，正在监听9999");
			ss = new ServerSocket(9999);
			
			while(true){
				//阻塞，等待连接
				s = ss.accept();
				//接受客户端发来的信息包
				ois = new ObjectInputStream(s.getInputStream());
				ms = (Message)ois.readObject();
				//判断接受的包的类型
				if(ms.getMesType().equals(MessageType.message_want_login)){
					this.isMessage_want_login();
				}else if(ms.getMesType().equals(MessageType.message_register)){
					this.isMessage_register();					
				}else if(ms.getMesType().equals(MessageType.message_server_close)){
					this.isMessage_server_close();
					break;
				}
				
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	public void isMessage_want_login()
	{
		try {
			//请求登陆包
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			//同意登陆,并返回同意登陆的包
			ms.setMesType(MessageType.message_allowed_login);
			oos.writeObject(ms);
			
			//接受登录信息包
			User u = (User)ois.readObject();
			
			
			System.out.println(u.getPasswd());
			
			Message m = new Message();
			String []paras={u.getUserId(),u.getPasswd()};
			UserModel um = new UserModel();
			//判断是否允许登录		
			if(um.queryLoginCheck(paras))
			{
				//返回成功登陆的信息包
				m.setMesType(MessageType.message_succeed);
				oos.writeObject(m);
				//这里单开一个线程，让该线程与客户端保持通讯
				
				SerConClientThread scct = new SerConClientThread(s,u.getUserId());
				ManageClientThread.addClientThread(u.getUserId(), scct);
				//启动与该客户端通讯的线程
				scct.start();
				
				//并通知其他在线用户
				scct.notifyOther(u.getUserId());
			}else {
				m.setMesType(MessageType.message_login_fail);
				oos.writeObject(m);
				//关闭连接
				s.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void isMessage_register()
	{
		try {
			UserModel um = new UserModel();
			String []paras = ms.getUserRegister();
			paras[paras.length-1] = this.proQqnumber();
			String []paras0  ={paras[paras.length-1]};
			for(int i=0;i<50;i++){
				if(!um.queryQqExist(paras0))
				{
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					Message mf = new Message();
					if(um.addQqUser(paras))
					{
						//成功注册
						mf.setMesType(MessageType.message_register_success);
						//设置生成的QQ号码
						mf.setGetter(paras0[0]);
					}else
					{
						//注册失败
						mf.setMesType(MessageType.message_register_fail);
					}
					oos.writeObject(mf);
					break;
				}
				else{
					paras0[0] = this.proQqnumber();
					paras[paras.length-1] = paras0[0];
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void isMessage_server_close()
	{
		try {
			//向所有与服务器相连的客户端发送服务器将要断开的消息
			Message mmm  = new Message();
			mmm.setMesType(MessageType.message_server_will_close);
			ManageClientThread.sendMessageToAll(mmm);
			//关闭服务器
			if(s!=null) s.close();
			if(ss!=null) ss.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public String proQqnumber()
	{
		String qqnumber=new String();
		for(int i=0;i<5;i++)
		{
			int a = (int)(Math.random()*10);
			if(i==0&&a==0)
			{
				i--;
				continue;
			}
			qqnumber+=a;
		}
		
		return qqnumber.trim();
	}
}
