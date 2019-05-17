/**
 * 这是与好友聊天界面
 * 因为客户端要处于读取的状态，因此我们把他做成一个线程
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
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);
		this.add(jsp,"Center");
		this.add(jp,"South");
		this.setSize(300,200);
		this.setTitle(ownerId+" 正在和 "+friendId+" 聊天");
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
			//如果用户点击了发送按钮
			
			Message m = new Message();
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setMesType(MessageType.message_comm_mes);
			m.setSendTime(new java.util.Date().toString());
			//附加上去
			this.jta.setEditable(true);
			String info0  = m.getSender() + " 对 "+m.getGetter()+" 说 \r\n   "+jtf.getText()+"\r\n";
			this.jta.append(info0);
			this.jta.setEditable(false);
			
			//System.out.println("QqChat "+m.getSender()+"给"+m.getGetter()+"说"+m.getCon());
			//new QqClientUser().sendMessage(m);
			//发送给服务器
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
	//写一个方法让他显示消息
	
	public  void showMessage(Message m)
	{
		this.jta.setEditable(true);
		String info0  = m.getSender() + " 对 "+m.getGetter()+" 说 \r\n   "+m.getCon()+"\r\n";
		this.jta.append(info0);
		this.jta.setEditable(false);
	}
		

	
	
}
