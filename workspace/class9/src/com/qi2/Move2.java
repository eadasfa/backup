/*
 * ���ܣ�ͨ���������ҿ���С��
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

	//����
	@Override
	//����ֵ�����
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	
	//��������
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
		//����repaint()�������ػ�
		this.repaint();
		
	}

	@Override
	
	//�����ͷ�
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

