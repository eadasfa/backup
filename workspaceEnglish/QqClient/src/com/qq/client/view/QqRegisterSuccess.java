package com.qq.client.view;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.qq.client.tools.Mytools;

public class QqRegisterSuccess extends JDialog{

	JLabel jl1,jl2,jl3;
	
	public QqRegisterSuccess(Frame owner,String title,boolean modal,String qqnumber) 
	{
		super(owner,title,modal);
		this.setLayout(new GridLayout(3,1));
		jl1 = new JLabel("您申请的QQ号码为：",JLabel.CENTER);
		jl1.setFont(Mytools.f1);	
		jl2 = new JLabel(qqnumber,JLabel.CENTER);
		jl2.setFont(Mytools.f2);
		jl2.setForeground(Color.blue);
		jl3 = new JLabel("请记好您的QQ号码",JLabel.CENTER);
		jl3.setFont(Mytools.f1);
		this.add(jl1);
		this.add(jl2);
		this.add(jl3);
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	
}
