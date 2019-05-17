/**
 * ���������ķ����������ڼ������ȴ�QQ����
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
			//��9999����
			System.out.println("���Ƿ����������ڼ���9999");
			ss = new ServerSocket(9999);
			
			while(true){
				//�������ȴ�����
				s = ss.accept();
				//���ܿͻ��˷�������Ϣ��
				ois = new ObjectInputStream(s.getInputStream());
				ms = (Message)ois.readObject();
				//�жϽ��ܵİ�������
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
			//�����½��
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			//ͬ���½,������ͬ���½�İ�
			ms.setMesType(MessageType.message_allowed_login);
			oos.writeObject(ms);
			
			//���ܵ�¼��Ϣ��
			User u = (User)ois.readObject();
			
			
			System.out.println(u.getPasswd());
			
			Message m = new Message();
			String []paras={u.getUserId(),u.getPasswd()};
			UserModel um = new UserModel();
			//�ж��Ƿ������¼		
			if(um.queryLoginCheck(paras))
			{
				//���سɹ���½����Ϣ��
				m.setMesType(MessageType.message_succeed);
				oos.writeObject(m);
				//���ﵥ��һ���̣߳��ø��߳���ͻ��˱���ͨѶ
				
				SerConClientThread scct = new SerConClientThread(s,u.getUserId());
				ManageClientThread.addClientThread(u.getUserId(), scct);
				//������ÿͻ���ͨѶ���߳�
				scct.start();
				
				//��֪ͨ���������û�
				scct.notifyOther(u.getUserId());
			}else {
				m.setMesType(MessageType.message_login_fail);
				oos.writeObject(m);
				//�ر�����
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
						//�ɹ�ע��
						mf.setMesType(MessageType.message_register_success);
						//�������ɵ�QQ����
						mf.setGetter(paras0[0]);
					}else
					{
						//ע��ʧ��
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
			//������������������Ŀͻ��˷��ͷ�������Ҫ�Ͽ�����Ϣ
			Message mmm  = new Message();
			mmm.setMesType(MessageType.message_server_will_close);
			ManageClientThread.sendMessageToAll(mmm);
			//�رշ�����
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
