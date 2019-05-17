/*
e * 事件处理机制
 */
package com.qi2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//接口 ActionListener 必须实现所有方法
public class Move extends JFrame implements ActionListener{

	//MyPanel mp = null;
	Panel mp = null;
	JButton jb1,jb2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Move m=new Move();
	}
	public Move()
	{
		mp=new Panel();
	//	mp=new MyPanel();
		jb1 = new JButton("黑色");
		jb2 = new JButton("红色");
		
		this.add(jb1,BorderLayout.NORTH);
		mp.setBackground(Color.BLUE);
		this.add(mp);
		this.add(jb2,BorderLayout.SOUTH);
		
		Cat cat = new Cat();
		//注册监听
		jb1.addActionListener(this);
		jb1.addActionListener(cat);
		//指定action命令
		jb1.setActionCommand("black");
		
		jb2.addActionListener(this);
		jb2.setActionCommand("red");
		
		this.setSize(200,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	//方法
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//判断是那个按钮被点击
		if(e.getActionCommand().equals("black"))
		{
			System.out.println("黑色");
			mp.setBackground(Color.BLACK);
		}else if(e.getActionCommand().equals("red"))
		{
			System.out.println("红色");
			mp.setBackground(Color.RED);
		}else
		{
			System.out.println("不知道");
		}
	}

}
/*class MyPanel extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		
	}
}*/
class Cat implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("black"))
		{
			System.out.println("cat");
		}
	}
	
}
