/**
 * ����������������
 * ��Ϊ�ͻ���Ҫ���ڶ�ȡ��״̬��������ǰ�������һ���߳�
 */
package com.qq.client.view;
import com.qq.client.tools.*;
import com.qq.common.*;
import com.qq.client.model.*;
import java.awt.*;

import javax.swing.*;

import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class QqChat extends JFrame implements ActionListener{

	JTextField jtf;
	JTextArea jta;
	JButton jb;
	JScrollPane jsp;
	JPanel jp;
	String ownerId,friendId;
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		QqChat qq = new QqChat("1");
//	}
	public QqChat(String ownerId,String friendId)  {
		this.ownerId = ownerId;
		this.friendId = friendId;
		jta = new JTextArea();
		jta.setEditable(false);
		jsp = new JScrollPane(jta);
		jtf = new JTextField(15);
		jb = new JButton("����");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		this.add(jsp,"Center");
		this.add(jp,"South");
		this.setSize(300,200);
		this.setTitle(ownerId+" ���ں� "+friendId+" ����");
		this.setIconImage((new ImageIcon("image/qq.gif")).getImage());
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb)
		{
			//����û�����˷��Ͱ�ť
			
			Message m = new Message();
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setMesType(MessageType.message_comm_mes);
			m.setSendTime(new java.util.Date().toString());
			//������ȥ
			this.jta.setEditable(true);
			String info0  = m.getSender() + " �� "+m.getGetter()+" ˵ \r\n   "+jtf.getText()+"\r\n";
			this.jta.append(info0);
			this.jta.setEditable(false);
			
			//System.out.println("QqChat "+m.getSender()+"��"+m.getGetter()+"˵"+m.getCon());
			//new QqClientUser().sendMessage(m);
			//���͸�������
			try {
				ObjectOutputStream oos = new ObjectOutputStream
						(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				jtf.setText("");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	//дһ������������ʾ��Ϣ
	
	public  void showMessage(Message m)
	{
		this.jta.setEditable(true);
		String info0  = m.getSender() + " �� "+m.getGetter()+" ˵ \r\n   "+m.getCon()+"\r\n";
		this.jta.append(info0);
		this.jta.setEditable(false);
	}
		

	
	
}
