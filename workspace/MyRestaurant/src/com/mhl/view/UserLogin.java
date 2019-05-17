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
	
	//定义组件
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
		//创建组件
		jl1 = new JLabel("请输入用户名:");
		jl1.setFont(Mytools.f1);
		jl1.setBounds(60,190,150,30);
		ct.add(jl1);
		
		jl2 = new JLabel("或员工号");
		jl2.setForeground(Color.red);
	
		jl2.setFont(Mytools.f2);
		jl2.setBounds(110,210,80,20);
		ct.add(jl2);
		
		jl3 = new JLabel("  请输入密码:");
		jl3.setFont(Mytools.f1);
		jl3.setBounds(60,240,150,30);
		ct.add(jl3);
		
		jName = new JTextField(10);
		jName.setBounds(170,195,150,35);
		//设置下凹
		jName.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jName);
		jPasswd = new JPasswordField(10);
		jPasswd.setBounds(170,245,150,35);
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jPasswd);
		jb1 = new JButton("登录");
		jb1.setFont(Mytools.f1);
		jb1.setBounds(110,300,70,30);
		ct.add(jb1);
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.setFont(Mytools.f1);
		jb2.setBounds(210,300,70,30);
		ct.add(jb2);
		jb2.addActionListener(this);
		//不实用上下框
		this.setUndecorated(true);
		//创建一个BackImage
		BackImage bi = new BackImage();
		//确定图片位置
		bi.setBounds(0,0,360,360);
		ct.add(bi);
		this.setSize(360,360);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	//内部类
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
		//登录
		if(e.getSource()==jb1)
		{
			String u = this.jName.getText().trim();
			String p = String.valueOf(this.jPasswd.getPassword());
			UserModel um = new UserModel();
			String res = um.checkUser(u, p).trim();
	
			if(res.equals("经理")||res.equals("主管"))
			{
		
				new Windows1();
				//关闭登陆界面
				this.dispose();
			}
		}
		//取消
		else if(e.getSource()==jb2)
		{
			System.exit(0);
		}
	}

}
