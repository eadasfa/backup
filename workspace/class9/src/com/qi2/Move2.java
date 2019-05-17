/*
 * 功能：通过上下左右控制小球
 */
package com.qi2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Move2 extends JFrame implements ActionListener{

	MyPanel mp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Move2 m2 = new Move2();
	}
	public Move2()
	{
		mp = new MyPanel();
	//	Container ct=this.getContentPane();
		
		this.add(mp);
		
		this.addKeyListener(mp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class MyPanel extends JPanel implements KeyListener
{
	int x=10;
	int y=10;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillOval(x, y, 10, 10);
	}

	//按键
	@Override
	//键的值被输出
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	
	//键被按下
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	//	System.out.println("Press  "+e.getKeyCode());
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			y+=10;
		}else if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			y-=10;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			x-=10;
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			x+=10;
		}
		//调用repaint()函数，重画
		this.repaint();
		
	}

	@Override
	
	//键被释放
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

