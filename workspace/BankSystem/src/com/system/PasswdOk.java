package com.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

public class PasswdOk extends JDialog implements ActionListener{
	
	JPanel jp1,jp2,jp3;
	JPasswordField jtf;
	JLabel jl;
	JButton jb1,jb2;
	Vector v ;
	Boolean flag = true;
	Check check;
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public PasswdOk(Frame owner,String user) {
		super(owner,"转账",true);
		v = new Vector();
		v.add(user);
		jl = new JLabel("密码");
		jtf= new JPasswordField(10);
		jb1 = new JButton("确定");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.addActionListener(this);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp1.add(jl);
		jp2.add(jtf);
		jp3.add(jb1);
		jp3.add(jb2);
		this.setLayout(new GridLayout(3,1));
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			String password = String.valueOf(jtf.getPassword());
			v.add(password);
			check = new Check();
			flag = check.pass(v);
			if(!this.flag)
			{
				JOptionPane.showMessageDialog(this, "密码错误");
				v.remove(1);
				
			}else{
				this.dispose();
			}
		}else if(e.getSource()==jb2)
		{
			this.dispose();
		}
	}
	
}
