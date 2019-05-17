package com.qi.Manage1;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class StudentWindow extends JFrame implements ActionListener,MouseListener
{

	
	
	JLabel jl1,jl2,jl3,jl4,jl5;
	
	JPanel jp1,jp2,jp3,jp4,jp5;
	
	public StudentWindow()  {
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		
		
		jl1 = new JLabel("个人信息");
		//jl1.setFont(new Font("宋体",Font.BOLD,28));
		jl2 = new JLabel("成绩查询");
		jl3 = new JLabel("选修信息");
		jl4 = new JLabel("修改密码");
		
		
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jp1.setLayout(new GridLayout(2,1));
//		ImageIcon image = new ImageIcon("image/stu.jpg");
//      image.setImage(image.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
//      jl5 = new JLabel(image);
//      jp4.add(jl5);
        
        jp3.setLayout(new GridLayout(1,4));
		jp3.add(jl1);
		jp3.add(jl2);
		jp3.add(jl3);
		jp3.add(jl4);
		
		jp1.add(jp3);
		
		
		this.setLayout(new BorderLayout(0,0));
		this.add(jp1,BorderLayout.NORTH);
		this.add(jp2,BorderLayout.CENTER);
		this.setSize(1000,600);
		this.setLocation(100,90);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
