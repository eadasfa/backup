/**
 * 这是服务器端的控制界面，可以启动服务器，关闭服务器
 * 可以管理用户
 */
package com.qq.server.view;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

import com.qq.common.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.qq.server.model.MyQqServer;
public class MyServerFrame extends JFrame implements ActionListener,Runnable{

	JPanel jp1;
	JButton jb1,jb2;
	MyQqServer ms;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame qsf = new MyServerFrame();
	}
	public MyServerFrame(){
		ms = new MyQqServer();
		jp1 = new JPanel();
		jb1 = new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2 = new JButton("关闭服务器");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500,400);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		addWindowListener(new closeWindow(this));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//开启服务器
		if(e.getSource()==jb1)
		{
			if(!ms.isFlag())
			{
				Thread t = new Thread(this);
				t.start();
			}
		}else if(e.getSource()==jb2)
		{
			this.closeServer();
		}
	}
	class closeWindow extends WindowAdapter{
		
		MyServerFrame my=null;
		public closeWindow(MyServerFrame my) {
			super();
			// TODO Auto-generated constructor stub
			this.my = my;
		}

		public void windowClosing(WindowEvent e) {
				
			my.closeServer();
		
			System.exit(0);
			
		}
	
	}
	public void closeServer()
	{
		if(ms.isFlag())
		{
			Message mm = new Message();
			mm.setMesType(MessageType.message_server_close);
			try {
				Socket s = new Socket("localhost",9999);
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(mm);
				ms.setFlag(false);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	@Override
	//用于开启服务器
	public void run() {
		// TODO Auto-generated method stub
		ms.openServer();
	}
	
}
