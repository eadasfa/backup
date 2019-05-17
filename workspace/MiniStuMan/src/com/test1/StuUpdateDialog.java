
package com.test1;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StuUpdateDialog extends JDialog implements ActionListener
{	
	
	JLabel jl1,jl11,jl2,jl3,jl4,jl5,jl6;
	JTextField jtf2,jtf5,jtf6;
	JRadioButton rbt1,rbt2;
	ButtonGroup bg;
	JComboBox jcb;
	JScrollPane jsp;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
	//owner 它的父窗口
	//title 窗口名
	//modal 指定是模式窗口(不可以干别的)，还是非模式窗口
	
	public StuUpdateDialog(Frame owner,String title,boolean modal,String id)
	{
		super(owner,title,modal); //调用父类构造方法，达到模式对话框效果
		
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jp7 = new JPanel();
		
		jl1 = new JLabel("      学号");
		jl11 = new JLabel(id);
		jl2 = new JLabel("      姓名");
		jl3 = new JLabel("      性别");
		jl4 = new JLabel("      年龄");
		jl5 = new JLabel("      籍贯");
		jl6 = new JLabel("      院系");
		
		
		
		
		rbt1 = new JRadioButton("男");
		rbt2 = new JRadioButton("女");
		bg=new ButtonGroup();
		bg.add(rbt1);
		bg.add(rbt2);
		
		String []age_str = new String[101] ;
		age_str[0] = "选择年龄";
		for(int i=1;i<101;i++)
			age_str[i] = (i)+"";
		jcb = new JComboBox(age_str);
		jsp = new JScrollPane(jcb);
		
		this.getRow(id);
		jb1 = new JButton("确定");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.addActionListener(this);
		
		jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp1.add(jl1);
		jp1.add(jl11);
		jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp2.add(jl2);
		jp2.add(jtf2);
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp3.add(jl3);
		jp3.add(rbt1);
		jp3.add(rbt2);
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp4.add(jl4);
		jp4.add(jsp);
		jp5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp5.add(jl5);
		jp5.add(jtf5);
		jp6.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp6.add(jl6);
		jp6.add(jtf6);
		jp7.add(jb1);
		jp7.add(jb2);
		
		this.setLayout(new GridLayout(7,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jp7);
		this.setSize(200,300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			StuModel sm=null;
			String id = this.jl11.getText().trim();
			String name = this.jtf2.getText().trim();
			int age = this.jcb.getSelectedIndex();
			String adress = this.jtf5.getText().trim();
			String major = this.jtf6.getText().trim();
			String sex = null;
			if(this.rbt1.isSelected())	sex = "男";
				else if(this.rbt2.isSelected()) sex = "女";
			if(name.length()==0||adress.length()==0||
					major.length()==0||sex.length()==0)
			{
				JOptionPane.showMessageDialog
					(this, "请将信息填写完整", "error",JOptionPane.WARNING_MESSAGE); 
			}else if(age==0)
			{
				JOptionPane.showMessageDialog
				(this, "请选择年龄", "error",JOptionPane.WARNING_MESSAGE);
			}else
			{
				//写一个SQL语句
				String sql = "update test1 set name = '"+name+"',sex='"+sex+"',age="+age+
						",adress='"+adress+"',major='"+major+"' where id ='" +id+"'";
				//构建新的数据模型并更新
				sm = new StuModel("Update",sql);
				this.dispose();
			}			
		}else if(e.getSource()==jb2)
		{
			this.dispose();
		}
		
	}
	public void getRow(String id)
	{
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs =null;
		try{		
			//加载驱动
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			//得到连接
			ct=DriverManager.getConnection
					("jdbc:microsoft:sqlserver://127.0.0.1:1433;databaseName=aaaaaa","sa","       ");
			//判断目的
			
			ps=ct.prepareStatement("select * from test1 where id ='"+id+"'");
			rs=ps.executeQuery();
			if(rs.next()){
				jtf2 = new JTextField(rs.getString(2),10);
				jtf5 = new JTextField(rs.getString(5),10);
				jtf6 = new JTextField(rs.getString(6),10);
				if(rs.getString(3)=="男")
					this.rbt1.setSelected(true);
				else
					this.rbt2.setSelected(true);
				this.jcb.setSelectedIndex(rs.getInt(4));
			}		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null)  ps.close();
				if(ct!=null)  ct.close();
				if(rs!=null)  rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
	}
	
}