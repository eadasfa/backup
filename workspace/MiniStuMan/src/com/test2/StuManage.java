/*
 * 完成一个迷你的学生管理系统
 */
package com.test2;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import java.util.*;

public class StuManage extends JFrame implements ActionListener{

	//定义控件
	StuModel sm=null;
	JPanel jp1,jp2;
	JTable jt;
	public JTable getJt() {
		return jt;
	}
	public void setJt(JTable jt) {
		this.jt = jt;
	}
	JButton jb1,jb2,jb3,jb4;
	JScrollPane jsp;
	JLabel jl1;
	JTextField jtf;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuManage test = new StuManage();

	}
	public StuManage(){
		/************************
		 *****jp1添加控件***********
		 ***********************/
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("查询");
		jb1.addActionListener(this);
		jl1 = new JLabel("请输入名字");
		//把各个控件加入JP1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		/************************
		 *****jp2添加控件***********
		 ***********************/
		jp2 = new JPanel();
		jb2 = new JButton("添加");
		jb2.addActionListener(this);
		jb3 = new JButton("修改");
		jb3.addActionListener(this);
		jb4 = new JButton("删除");
		jb4.addActionListener(this);
		//把各个按钮加入到jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		
	    //创建一个数据模型
		StuModel sm = new StuModel();
		
		jt = new JTable(sm);
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(600,400);
		this.setTitle("学生管理系统");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//查询
		if(e.getSource()==jb1)
		{
			//因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
			String name = this.jtf.getText().trim();
			if(name.length()==0)
			{
				sm = new StuModel();
			}else{
				//写一个SQL语句
				Vector seek = new Vector();
				seek.add(name);
				//构建新的数据模型并更新
				sm = new StuModel("Query",seek);
			}
			//更新JTable
			jt.setModel(sm);
		}
		//添加
		else if(e.getSource()==jb2)
		{
			StuAddDialog sad = new StuAddDialog(this,"添加学生",true);
			sm = new StuModel();
			jt.setModel(sm);
		}
		//修改
		else if(e.getSource()==jb3)
		{
			//k为选中的行号，开始为0
			int k =jt.getSelectedRow(); 
			if(k==-1){
				JOptionPane.showMessageDialog
				(this, "请选中您想修改的记录", "error",JOptionPane.WARNING_MESSAGE); 
			}else
			{
				String id = (String)jt.getValueAt(k, 0);
				StuUpdateDialog sud= new StuUpdateDialog(this,"修改信息",true,id);
				sm = new StuModel();
				jt.setModel(sm);
			}
		}
		//删除
		else if(e.getSource()==jb4)
		{
			//k为选中的行号，开始为0,若没选定返回-1
			int k =jt.getSelectedRow(); 
			
			if(k==-1){
				JOptionPane.showMessageDialog
				(this, "请选中您想删除的记录", "error",JOptionPane.WARNING_MESSAGE); 
			}else
			{
				String id = (String)jt.getValueAt(k, 0);
				Vector seek = new Vector();
				seek.add(id);
				//构建新的数据模型并更新
				sm = new StuModel("Delete",seek);
				//更新JTable
				jt.setModel(sm);
			}
			
		}
		
	}

}
