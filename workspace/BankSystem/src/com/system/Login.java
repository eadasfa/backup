package com.system;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

public class Login extends JFrame implements ActionListener{

	JButton jb1,jb2;
	JTextField jft;
	JPasswordField jpf;
	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Login login = new Login();
		Login wd = new Login();
	}
	public Login() {
		// TODO Auto-generated method stub
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		
	    jft = new JTextField(10);
		jpf = new JPasswordField(10);
		
		jl1 = new JLabel("用户名");
		jl2 = new JLabel("密    码");
		
		jb1 = new JButton("登录");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.addActionListener(this);
				
		jp1.add(jl1);
		jp1.add(jft);
		
		jp2.add(jl2);
		jp2.add(jpf);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.setLayout(new GridLayout(3,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			String user = jft.getText().trim();
			String passwd = String.valueOf(jpf.getPassword());
		//	System.out.println(passwd);
			if(user.length()==0){
				JOptionPane.showMessageDialog(this, "请输入用户名");
			}
			else if(passwd.length()==0){
				JOptionPane.showMessageDialog(this, "请输入密码");
			}else {
				Vector v = new Vector();
				v.add(user);
				v.add(passwd);
				ResultSet rs = null;
				SqlHelp sh = new SqlHelp();
				try {
					String sql = "select * from login where account_number = ? and passwd = ?";
					rs = sh.sqlQuery(sql, v);
					if(rs.next())
					{
						Windows wd = new Windows(user);
						
						this.dispose();
					}else 
					{
						JOptionPane.showMessageDialog(this, "密码错误或本账户不存在");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}finally{
					try {
						rs.close();
						sh.sqlClose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
							
		}else if(e.getSource()==jb2)
		{
			this.dispose();
		}
	}

}
