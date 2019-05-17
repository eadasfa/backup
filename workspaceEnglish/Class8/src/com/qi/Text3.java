/*
 * 流式布局
 * 1.继承JFrame
 * 2.定义组件
 * 3.创建组件
 * 4.添加组件
 * 5.对窗体设置
 * 6.显示窗体
 */
package com.qi;
import java.awt.*;
import javax.swing.*;

public class Text3 extends JFrame{

	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text3 t1 = new Text3();
		
	}
	//构造函数
	public Text3()
	{
		// JFrame是一个顶层容器（可以添加其他swing组件的类）
		//JFrame jf = new JFrame();
		//创建一个按钮
		JButton jb1=new JButton("1");
		JButton jb2=new JButton("2");
		JButton jb3=new JButton("3");
		JButton jb4=new JButton("4");
		JButton jb5=new JButton("5");
		JButton jb6=new JButton("6");
		//添加JButton组件
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
		this.add(jb5);
		this.add(jb6);
		//设置布局管理器（默认居中对齐）
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//给窗体设置标题
		this.setTitle("流式布局");
				 
		//设置大小,按像素计算（密度单位）
		this.setSize(200,200);
		//禁止用户改变窗体大小
		this.setResizable(false);
		
		//设置初始位置
		this.setLocation(300,300);
		//关闭后退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示
		this.setVisible(true);
	}

}