/**
 * 服务器端
 */
package com.test1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;
import java.nio.Buffer;

import javax.swing.*;
public class MyServer3 extends JFrame implements ActionListener,KeyListener{

	JTextArea jta = null;
	JTextField jtf = null;
	JButton jb = null;
	JPanel jp1=null;
	JScrollPane jsp = null;
	//把信息发给客户端的对象
	PrintWriter pw = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 MyServer3 ms = new MyServer3();
	}

	public MyServer3() {
		
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jtf = new JTextField(20);
		jtf.addKeyListener(this);
		jb = new JButton("发送");
		jb.addActionListener(this);
		jp1 = new JPanel();
		jp1.add(jtf);
		jp1.add(jb);
		this.add(jsp,"Center");
		this.add(jp1,"South");
		this.setSize(400,300);
		this.setTitle("QQ简易聊天Server");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		try {
			//服务器监听
			ServerSocket ss = new ServerSocket(999);
			//等待连接
			Socket s = ss.accept();
			pw = new PrintWriter(s.getOutputStream(),true);
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			//读取从客户端发来的信息
			while(true)
			{
				//读取从客户端发来的信息
				String info = br.readLine();
				jta.append("Client:\r\n"+info+"\r\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//如果用户按下发送信息的按钮
		if(e.getSource()==jb)
		{
			//把服务器在jtf写的内容发送给客户端
			String info = jtf.getText();
			//把客户端发送的信息显示到jta
			jta.append("Server:\r\n"+info+"\r\n");
			
			pw.println(info);
			jtf.setText("");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			//把服务器在jtf写的内容发送给客户端
			String info = jtf.getText();
			//把客户端发送的信息显示到jta
			jta.append("Client:\r\n"+info+"\r\n");
			
			pw.println(info);
			jtf.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
