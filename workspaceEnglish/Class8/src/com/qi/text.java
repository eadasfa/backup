package com.qi;
import java.awt.*;

import javax.swing.*;
import javax.swing.*;
public class text extends JFrame{

	JButton jb1=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		text t1 = new text();
		
	}
	//构造函数
	public text()
	{
		// JFrame是一个顶层容器（可以添加其他swing组件的类）
		//JFrame jf = new JFrame();
		//创建一个按钮
		JButton jb1=new JButton("按钮");
		//添加JButton组件
		this.add(jb1);// 中间显示
		
		//给窗体设置标题
		this.setTitle("Hello,world");
				 
		//设置大小,按像素计算（密度单位）
		this.setSize(200,200);
		
		//设置初始位置
		this.setLocation(300,300);
		//关闭后退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示
		this.setVisible(true);
	}

}

