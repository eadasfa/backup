package com.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

public class Windows extends JFrame implements ActionListener{
	
	int sign=0;
	String sql;
	SqlHelp sp=null;
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	String user;
	MyMainPanel mmp;//开始
	Balance balance;//余额
	Loan loan;//贷款
	Ptransfer ptr ;//转账
	Repayment rep;//还贷
	ChangePassword cpd;//修改密码
	public Windows(String user) {
		this.user = user;
		jb1 = new JButton("余额");
		jb1.addActionListener(this);
		jb2 = new JButton("贷款");
		jb2.addActionListener(this);
		jb3 = new JButton("转账");
		jb3.addActionListener(this);
		jb4 = new JButton("还贷");
		jb4.addActionListener(this);
		jb5 = new JButton("修改密码");
		jb5.addActionListener(this);
		jb6 = new JButton("退出");
		jb6.addActionListener(this);
		
		jp1 = new JPanel();
		jp2 = new JPanel();			
		
		jp1.setLayout(new GridLayout(6,1));
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		jp1.add(jb5);
		jp1.add(jb6);
		
		this.add(jp1 ,BorderLayout.WEST);
		mmp  = new MyMainPanel();
		Thread t = new Thread(mmp);
		t.start();
		this.add(mmp,BorderLayout.CENTER);
		this.setTitle(user);
		this.setSize(600,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			if(sign!=1){
				this.wdClose();
				balance = new Balance(this.user);
				Thread t = new Thread(balance);
				t.start();
				this.sign=1;
				this.add(balance,BorderLayout.CENTER);				
				this.setVisible(true);
			}
	
		}else if(e.getSource()==jb2)
		{
			if(sign!=2){
				this.wdClose();
				loan = new Loan(this.user);
				Thread t = new Thread(loan);
				t.start();
				this.sign=2;
				this.add(loan,BorderLayout.CENTER);
				this.setVisible(true);
			}
		}
		else if(e.getSource()==jb3)
		{
			if(sign!=3){
				this.wdClose();
				ptr = new Ptransfer(this.user,this);
				this.sign=3;
				this.add(ptr,BorderLayout.CENTER);
				this.setVisible(true);
			}
		}
		else if(e.getSource()==jb4)
		{
			if(sign!=4){
				this.wdClose();
				rep = new Repayment(this.user,this);
				this.sign=4;
				this.add(rep,BorderLayout.CENTER);
				this.setVisible(true);
			}
		}
		else if(e.getSource()==jb5)
		{
			if(sign!=5){
				this.wdClose();
				cpd = new ChangePassword(this.user);
				this.sign=5;
				this.add(cpd,BorderLayout.CENTER);
				this.setVisible(true);
			}
		}
		else if(e.getSource()==jb6)
		{
			this.dispose();
		}
	}
	public void wdClose()
	{
		switch(this.sign){
		case 0:
			this.remove(mmp);
			break;
		case 1:
			this.remove(balance);
			break;
		case 2:
			this.remove(loan);
			break;
		case 3:
			this.remove(ptr);
			break;
		case 4:
			this.remove(rep);
			break;
		case 5:
			this.remove(cpd);
			break;
		case 6:
			break;
			
		}
	}

}

