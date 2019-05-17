/* 边界布局
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

public class Text2 extends JFrame{

	JButton jb1,jb2,jb3,jb4,jb5;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text2 t1 = new Text2();
		
	}
	//构造函数
	public Text2()
	{
		// JFrame是一个顶层容器（可以添加其他swing组件的类）
		//JFrame jf = new JFrame();
		//创建一个按钮
		JButton jb1=new JButton("中");
		JButton jb2=new JButton("北");
		JButton jb3=new JButton("东");
		JButton jb4=new JButton("南");
		JButton jb5=new JButton("西");
		//添加JButton组件
		this.add(jb1,BorderLayout.CENTER);
		this.add(jb2,BorderLayout.NORTH);
		this.add(jb3,BorderLayout.EAST);
		this.add(jb4,BorderLayout.SOUTH);
		this.add(jb5,BorderLayout.WEST);
		//给窗体设置标题
		this.setTitle("边界布局");
				 
		//设置大小,按像素计算（密度单位）
		this.setSize(300,200);
		
		//设置初始位置
		this.setLocation(300,300);
		//关闭后退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示
		this.setVisible(true);
	}

}