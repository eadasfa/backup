package com.qi;
import java.awt.*;
import javax.swing.*;
public class Text4 extends JFrame{

	/**
	 * @param args
	 */
	JButton jbs[]=new JButton[9];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text4 t2 = new Text4();
	}
	public Text4()
	{
		//创建组件
		for(int i=0;i<9;i++)
		{
			jbs[i] = new JButton(String.valueOf(i));
		}
		this.setLayout(new GridLayout(3,3,10,10));
		//添加组件
		for(int i=0;i<jbs.length;i++)
		{
			this.add(jbs[i]);
		}
		this.setTitle("网格布局");
		//设置大小
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

}
