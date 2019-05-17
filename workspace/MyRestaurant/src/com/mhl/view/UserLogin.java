package com.mhl.view;
import com.mhl.tools.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import com.mhl.model.*;

public class UserLogin extends JDialog implements ActionListener{
	
	//�������
	JLabel jl1,jl2,jl3;
	JTextField jName;
	JPasswordField jPasswd;
	JButton jb1,jb2;

	public static void main(String args[]){
		UserLogin user = new UserLogin();
	}
	public UserLogin()
	{
		this.setLayout(null);
		Container ct = this.getContentPane();
		//�������
		jl1 = new JLabel("�������û���:");
		jl1.setFont(Mytools.f1);
		jl1.setBounds(60,190,150,30);
		ct.add(jl1);
		
		jl2 = new JLabel("��Ա����");
		jl2.setForeground(Color.red);
	
		jl2.setFont(Mytools.f2);
		jl2.setBounds(110,210,80,20);
		ct.add(jl2);
		
		jl3 = new JLabel("  ����������:");
		jl3.setFont(Mytools.f1);
		jl3.setBounds(60,240,150,30);
		ct.add(jl3);
		
		jName = new JTextField(10);
		jName.setBounds(170,195,150,35);
		//�����°�
		jName.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jName);
		jPasswd = new JPasswordField(10);
		jPasswd.setBounds(170,245,150,35);
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jPasswd);
		jb1 = new JButton("��¼");
		jb1.setFont(Mytools.f1);
		jb1.setBounds(110,300,70,30);
		ct.add(jb1);
		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��");
		jb2.setFont(Mytools.f1);
		jb2.setBounds(210,300,70,30);
		ct.add(jb2);
		jb2.addActionListener(this);
		//��ʵ�����¿�
		this.setUndecorated(true);
		//����һ��BackImage
		BackImage bi = new BackImage();
		//ȷ��ͼƬλ��
		bi.setBounds(0,0,360,360);
		ct.add(bi);
		this.setSize(360,360);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	//�ڲ���
	class BackImage extends JPanel
	{
		Image im;
		public BackImage()
		{
			try {
				im=ImageIO.read(new File("image//login.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g)
		{
			g.drawImage(im,0, 0, 360, 360, this);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//��¼
		if(e.getSource()==jb1)
		{
			String u = this.jName.getText().trim();
			String p = String.valueOf(this.jPasswd.getPassword());
			UserModel um = new UserModel();
			String res = um.checkUser(u, p).trim();
	
			if(res.equals("����")||res.equals("����"))
			{
		
				new Windows1();
				//�رյ�½����
				this.dispose();
			}
		}
		//ȡ��
		else if(e.getSource()==jb2)
		{
			System.exit(0);
		}
	}

}
