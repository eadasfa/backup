/**
 * 服务器端
 *简易聊天
 */
package com.test1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;

import javax.swing.*;
public class MyClient3 extends JFrame implements ActionListener,KeyListener{

	JTextArea jta = null;
	JTextField jtf = null;
	JButton jb = null;
	JPanel jp1=null;
	JScrollPane jsp = null;
	PrintWriter pw = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyClient3  ms = new MyClient3 ();
	}

	public MyClient3 () {
		
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
		this.setTitle("QQ简易聊天Client");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		try {
			Socket s = new Socket("127.0.0.1",999);
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			pw = new PrintWriter(s.getOutputStream(),true);
			while(true)
			{
				//不停地读从服务器端发来的信息
				String info = br.readLine();
				jta.append("Server:\r\n"+info+"\r\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb)
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
