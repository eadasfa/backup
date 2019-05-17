package com.system;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

public class Ptransfer extends JPanel implements ActionListener{
	
	Vector v = new Vector();
	Frame frame;
	SqlHelp sp=null;
	ResultSet rs=null;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3,jp4;
	JTextField jft1,jft2,jft3;
	JLabel jl1,jl2,jl3;
	String user;
	public Ptransfer(String user,Frame frame) {
		this.frame=frame;
		this.user=user;
		jb1 = new JButton("转账");
		jb1.addActionListener(this);
		jb2 = new JButton("清除");
		jb2.addActionListener(this);
		
		jl1 = new JLabel("余       额");
		jl2 = new JLabel("转账账户");
		jl3 = new JLabel("转账数额");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		jft1 = new JTextField(10);
		jft1.setEditable(false);
		jft2 = new JTextField(10);
		jft3 = new JTextField(10);
	
		sp = new SqlHelp();
		v.add(user);
		
		this.upDate();
		
		jp1.add(jl1);
		jp1.add(jft1);
		jp2.add(jl2);
		jp2.add(jft2);
		jp3.add(jl3);
		jp3.add(jft3);
		jp4.add(jb1);
		jp4.add(jb2);
		this.setLayout(new GridLayout(4,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			String mGoto = jft2.getText().trim();
			String num =jft3.getText().trim();
			if(mGoto.length()==0){
				JOptionPane.showMessageDialog(this, "请输入转账账户");
			}else if(num.length()==0)
			{
				JOptionPane.showMessageDialog(this, "请输入转账金额");
			}else{
				PasswdOk yes = new PasswdOk(frame,user);
				if(yes.getFlag())
				{
					String sql="EXECUTE PTransfer ?, ?, ?";
					v.add(mGoto);
					v.add(num);
					if(!sp.sqlUpdate(sql, v))
					{
						JOptionPane.showMessageDialog(this, "转账失败");
						v.remove(2);
						v.remove(1);
					}else{
						v.remove(2);
						v.remove(1);
						this.upDate();
					}
					
				}
				this.Clear();
			}
			
			
		}else if(e.getSource()==jb2)
		{
			this.Clear();
		}
	}
	public void upDate()
	{
		//查询余额
		String sql ="select A.balance from  account AS A  " +
					"where A.account_number = ? ";
				   	
		rs = sp.sqlQuery(sql, v);
		int balance=0;
		try {
			if(rs.next())
			{	
				balance= rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
				try {
					if(rs!=null) rs.close();
					sp.sqlClose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		jft1.setText(String.valueOf(balance));
	}
	public void Clear()
	{
		jft2.setText(null);
		jft3.setText(null);
	}

}

