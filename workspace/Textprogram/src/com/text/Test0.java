package com.text;

import javax.swing.*;
import java.awt.*;

public class Test0 extends JFrame{
	JList jlist;	//列表框
	JComboBox jcb;	//下拉框
	JPanel jp1, jp2;	//面板
	JLabel jlb1, jlb2;
	JScrollPane jsp;	//滚动控件
	
	//构造函数
	public Test0(){
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		jlb1 = new JLabel("你的水平：");
		String str1[] = {"巨坑", "菜鸟", "一般", "大神"};
		jcb = new JComboBox(str1);
		
		jlb2 = new JLabel("选择英雄：");
		String str2[] = {"盖仑", "艾希", "提莫", "赵信", "李青", "安妮"};
		jlist = new JList(str2);
		jlist.setVisibleRowCount(2);//默认显示行数
		jsp = new JScrollPane(jlist);
		
		jp1.add(jlb1);
		jp1.add(jcb);
		
		jp2.add(jlb2);
		jp2.add(jsp);
		
			//网格布局2行一列
		this.setLayout(new GridLayout(2, 1));
		
		this.add(jp1);
		this.add(jp2);
		
		this.setSize(200,200);
		this.setTitle("组件演示");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Test0 test0 = new Test0();

	}

}