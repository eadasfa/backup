/*
 * 多种布局管理器的使用
 * JPanel:面板组件，非顶层容器
 */
package com.qi;
import java.awt.*;
import javax.swing.*;
public class Text5 extends JFrame{

	JPanel jp1,jp2;
	JButton bt1,bt2,bt3,bt4,bt5,bt6;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text5 t=new Text5();
	}
	public Text5()
	{
		//创建组件
		//JPanel 默认FlowLayout
		jp1=new JPanel();
		jp2=new JPanel();
		bt1 = new JButton("西瓜");
		bt2 = new JButton("苹果");
		bt3 = new JButton("荔枝");
		bt4 = new JButton("葡萄");
		bt5 = new JButton("桔子");
		bt6 = new JButton("香蕉");
		
		//设置布局
		//添加JPanel
		jp1.add(bt1);
		jp1.add(bt2);
		jp2.add(bt3);
		jp2.add(bt4);
		jp2.add(bt5);
		//把JPanel加入JFrame
		this.add(jp1,BorderLayout.NORTH);
		this.add(bt6,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		this.setSize(300,300);
		this.setTitle("FRUIT");
		this.setLocation(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}





