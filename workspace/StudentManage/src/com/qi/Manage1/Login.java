package com.qi.Manage1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Login extends JFrame implements ActionListener
{
	//登录类型
	private String loginType="stuLogin";
	JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	//标签
	JLabel jl1=null;
	JLabel jl2=null;
	JLabel jl3=null;
	JLabel jl4=null;
	JLabel jl5=null;
	JLabel jl6=null;
	//按钮
	JButton jb1,jb2;
	//输入框
	JTextField jtf;
	//密码框
	JPasswordField jpf;
	
	JComboBox jcb=null;
	public Login(){
		
		//总
		jp1=new JPanel();
		jp2=new JPanel();
		
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();

		jl1=new JLabel("                     欢迎进入学生管理系统!");
		jl2=new JLabel("用户类型");
		jl3=new JLabel("用  户  名");
		jl4=new JLabel("密        码");
		jl6=new JLabel("                     用户登录");
		
		jtf = new JTextField(10);
		jpf = new JPasswordField(10);
		
		jb1 = new JButton("登录");
		jb2 = new JButton("退出");
		
		
		String[] str = {"学生","教师","管理员"};
		jcb = new JComboBox(str);
		jcb.addActionListener(this);
		
		ImageIcon image = new ImageIcon("image/login.jpg");
        image.setImage(image.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
		jl5=new JLabel(image);
		//按钮
		jb1=new JButton("登录");
		jb2=new JButton("退出");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp3.add(jl2);
		jp3.add(jcb);
		
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp4.add(jl3);
		jp4.add(jtf);
		
		jp5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp5.add(jl4);
		jp5.add(jpf);
		
		jp6.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp6.add(jb1);
		jp6.add(jb2);
		
		jp1.setLayout(new GridLayout(6,1));
		
		jp1.add(jl1);
		jp1.add(jl6);
		jp1.add(jp3);
		jp1.add(jp4);
		jp1.add(jp5);
		jp1.add(jp6);
		
		
		jp2.add(jl5);

		//设置布局
		this.setLayout(new GridLayout(1,2));
		
		this.add(jp1);
		this.add(jp2);
		//设置界面位置及大小
		this.setBounds(480, 225, 600, 300);
		//禁止改变界面大小
		this.setResizable(false);
		//设置标题
		this.setTitle("学生管理系统");
		//显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jcb)
		{
			int i = jcb.getSelectedIndex();
			switch(i){
				//0表示学生
				case 0:
					//1表示学生
					loginType = "stuLogin";
					break;
				//1表示教师
				case 1:
					loginType = "teaLogin";
					break;
				//2表示管理员
				case 2:
					loginType = "manLogin";
					break;
				
			}
		}
		if(e.getSource()==jb1){
			if(loginType==null){
				JOptionPane.showMessageDialog
				(this, "请选择登录身份", "error",JOptionPane.WARNING_MESSAGE); 
			}else if(loginType.equals("stuLogin")){
				StudentWindow stl = new StudentWindow();
				this.dispose();
			}else if(loginType.equals("teaLogin")){
				TeacherWindow tww= new TeacherWindow();
				this.dispose();
			}else if(loginType.equals("manLogin")){
				ManagerWindow mww= new ManagerWindow();
				this.dispose();
			}
			
		}
		else if(e.getSource()==jb2)
		{
			System.exit(0);
		}
	}
}
	