package com.qi;
import java.awt.*;
import javax.swing.*;
public class Draw extends JFrame{

	MyPanel mp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Draw d = new Draw();
	}
	public Draw()
	{
		mp = new MyPanel();
		this.add(mp);
		this.setSize(300,300);
		this.setLocation(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
class MyPanel extends JPanel
{
	public void paint(Graphics g)
	{
		System.out.println("被调用");
		//调用这句话  用父类初始化
		super.paint(g);
		//画圆
		g.drawOval(10, 10, 30, 30);
	}
	
}
