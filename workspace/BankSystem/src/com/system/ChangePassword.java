package com.system;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ChangePassword extends JPanel implements ActionListener{

	Vector v,v0 ;
	Frame frame;
	SqlHelp sp=null;
	ResultSet rs=null;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3,jp4;
	JPasswordField jft1,jft2,jft3;
	JLabel jl1,jl2,jl3;
	String user;
	Check check;
	public ChangePassword(String user) {
		this.user=user;
		v = new Vector();
		v0 = new Vector();
		jb1 = new JButton("ȷ��");
		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��");
		jb2.addActionListener(this);
		
		jl1 = new JLabel("ԭ����");
		jl2 = new JLabel("������");
		jl3 = new JLabel("������");
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		jft1 = new JPasswordField(10);
		jft2 = new JPasswordField(10);
		jft3 = new JPasswordField(10);
	
		sp = new SqlHelp();
		v.add(user);
		
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
			String passwd0 = String.valueOf(jft1.getPassword());
			String passwd1 = String.valueOf(jft2.getPassword());
			String passwd2 = String.valueOf(jft3.getPassword());
			check = new Check();
			v.add(passwd0);
			if(!check.pass(v))
			{
				JOptionPane.showMessageDialog(this, "�������");
				v.remove(1);
				this.Clear();
			}else if(!passwd1.equals(passwd2))
			{
				JOptionPane.showMessageDialog(this, "�����벻ƥ��");
				v.remove(1);
				this.Clear();
			}else{
				v0.add(passwd1);
				v0.add(user);
				String sql="update login set passwd = ? where account_number = ? ";
				
				if(sp.sqlUpdate(sql, v0)){
					JOptionPane.showMessageDialog(this, "�����޸ĳɹ�");
				}else{
					JOptionPane.showMessageDialog(this, "�����޸�ʧ��");
				}
				this.Clear();
				v.remove(1);
				v0.remove(1);
				v0.remove(0);
			}
			
			
		}else if(e.getSource()==jb2)
		{
			this.Clear();
		}
	}
	public void Clear()
	{
		jft1.setText(null);
		jft2.setText(null);
		jft3.setText(null);
	}
}
