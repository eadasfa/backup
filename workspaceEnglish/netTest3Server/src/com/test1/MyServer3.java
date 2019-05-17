/**
 * ��������
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
	//����Ϣ�����ͻ��˵Ķ���
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
		jb = new JButton("����");
		jb.addActionListener(this);
		jp1 = new JPanel();
		jp1.add(jtf);
		jp1.add(jb);
		this.add(jsp,"Center");
		this.add(jp1,"South");
		this.setSize(400,300);
		this.setTitle("QQ��������Server");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		try {
			//����������
			ServerSocket ss = new ServerSocket(999);
			//�ȴ�����
			Socket s = ss.accept();
			pw = new PrintWriter(s.getOutputStream(),true);
			InputStreamReader isr = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			//��ȡ�ӿͻ��˷�������Ϣ
			while(true)
			{
				//��ȡ�ӿͻ��˷�������Ϣ
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
		//����û����·�����Ϣ�İ�ť
		if(e.getSource()==jb)
		{
			//�ѷ�������jtfд�����ݷ��͸��ͻ���
			String info = jtf.getText();
			//�ѿͻ��˷��͵���Ϣ��ʾ��jta
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
			//�ѷ�������jtfд�����ݷ��͸��ͻ���
			String info = jtf.getText();
			//�ѿͻ��˷��͵���Ϣ��ʾ��jta
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
