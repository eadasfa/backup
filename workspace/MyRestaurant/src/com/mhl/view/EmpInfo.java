/**
 * 这是用于显示人事管理的界面
 */
package com.mhl.view;
import com.mhl.model.*;
import javax.swing.*;

import com.mhl.tools.Mytools;

import java.awt.*;
import java.awt.event.*;

public class EmpInfo extends JPanel implements ActionListener{
	//定义组件
	JPanel p1,p2,p3,p4,p5;
	JLabel p1_lab1,p3_lab1;
	JTextField p1_jtf1;
	JButton p1_jb1,p4_jb1,p4_jb2,p4_jb3,p4_jb4;
	JTable jtable;
	JScrollPane jsp;
	EmpModel em=null;
	//构造函数
	public EmpInfo() {
		this.setLayout(new BorderLayout());
		//创建组件
		/***********************
		 * North
		 ********************/
		p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1_lab1 = new JLabel("请输入姓名（员工号或职位）");;
		p1_lab1.setFont(Mytools.f2);
		p1_jtf1 = new JTextField(20);
		p1_jb1 = new JButton("查询");
		p1_jb1.setFont(Mytools.f3);
		p1.add(p1_lab1);
		p1.add(p1_jtf1);
		p1.add(p1_jb1);
		/***********************
		 * Center
		 ********************/
		em = new EmpModel();
		String paras[] = {"1"};
		em.query("select empid ,empname, sex ,zhiwei from rszl where 1 = ?", paras);
		jtable = new JTable(em);
		p2 = new JPanel(new BorderLayout());
		jsp = new JScrollPane(jtable);
		p2.add(jsp,"Center");
		/***********************
		 * South
		 ********************/
		p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3_lab1 = new JLabel("总共是"+jtable.getRowCount()+"条记录");
		p3.add(p3_lab1);
		p4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p4_jb1 = new JButton("详细信息");
		p4_jb2 = new JButton("添加");
		p4_jb3 = new JButton("修改");
		p4_jb4 = new JButton("删除");
		p4_jb4.addActionListener(this);
		p4.add(p4_jb1);
		p4.add(p4_jb2);
		p4.add(p4_jb3);
		p4.add(p4_jb4);
		
		p5 = new JPanel(new BorderLayout());
		p5.add(p3,"West");
		p5.add(p4,"East");
		//把p1放入
		this.add(p1,"North");
		//p2
		this.add(p2,"Center");
		//p5放入
		this.add(p5,"South");
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(this.p4_jb4==e.getSource())
		{
			int selRowNum = jtable.getSelectedRow();
			em = new EmpModel();
			String empNo = String.valueOf(jtable.getValueAt(selRowNum, 0));
			if(em.delEmpById(empNo))
			{
				JOptionPane.showMessageDialog(this, "删除成功");
				String paras[] = {"1"};
				String sql="select empid ,empname, sex ,zhiwei from rszl where 1 = ?";
				em.query(sql, paras);
				jtable.setModel(em);
			}
			else{
				JOptionPane.showMessageDialog(this, "删除失败");
			}
		}
	}

}
