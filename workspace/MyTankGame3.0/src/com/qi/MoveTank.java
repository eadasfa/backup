/*
 * ����һ�����ü����ƶ���̹��
 *
 */
package com.qi;
import java.awt.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class MoveTank extends JFrame{

	MyPanel2 mp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoveTank mt=new MoveTank();
	}
	public MoveTank()
	{
		mp = new MyPanel2();
		//  �����߳�
		Thread t=new Thread(mp);
		t.start();
		this.add(mp);
		this.addKeyListener(mp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
class MyPanel2 extends JPanel implements KeyListener,Runnable
{
	Hero tk = null;
	private int num = 3;
	Vector<EnemyTank> enemy = new Vector<EnemyTank>();
		
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public MyPanel2(){
		tk = new Hero(100,100,0,1);

		for(int i=0;i<this.num;i++)
		{
			EnemyTank em = new EnemyTank((i+1)*50,0,1,1);
			this.enemy.add(em);
		}
	}
	public void paint(Graphics g){
		super.paint(g);
		
		g.fillRect(0, 0, 400, 300);
		
		//�����Լ�̹��
		this.drawTank(tk.getX(), tk.getY(), g, 0, this.tk.getDirect());
		
		//��������̹��
		for(int i=0;i<this.enemy.size();i++)
		{
			EnemyTank em = (EnemyTank)this.enemy.get(i);
			this.drawTank(em.getX(), em.getY(), g, 1, em.getDirect());
		}
		
		//�����ӵ�
		if(tk.s!=null&&tk.s.isLive)
		{
			g.drawOval(this.tk.s.getX(), this.tk.s.getY(), 4, 4);
		}
	}
	public void drawTank(int x,int y,Graphics g,int type,int direct)
	{
		switch(type)
		{
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
		}
		switch(direct)
		{
		//up
		case 0:
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x-10, y-15, 5, 30,false);
			//�����ұߵľ���
			g.fill3DRect(x+5, y-15, 5, 30,false);
			//�����м����
			g.fill3DRect(x-5, y-10, 10, 20,false);
			//����Բ
			g.fillOval(x-5, y-5, 10, 10);
			//����
			g.drawLine(x, y, x, y-15);
			break;
		//down
		case 1:
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x-10, y-15, 5, 30,false);
			//�����ұߵľ���
			g.fill3DRect(x+5, y-15,5, 30,false);
			//�����м����
			g.fill3DRect(x-5, y-10, 10, 20,false);
			//����Բ
			g.fillOval(x-5, y-5, 10, 10);
			//����
			g.drawLine(x, y, x, y+15);
			break;
		//left
		case 2:
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x-15, y-10, 30, 5,false);
			//�����ұߵľ���
			g.fill3DRect(x-15, y+5, 30, 5,false);
			//�����м����
			g.fill3DRect(x-10, y-5, 20, 10,false);
			//����Բ
			g.fillOval(x-5, y-5, 10, 10);
			//����
			g.drawLine(x, y, x-15, y);
			break;
		//right
		case 3:
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x-15, y-10, 30, 5,false);
			//�����ұߵľ���
			g.fill3DRect(x-15, y+5, 30, 5,false);
			//�����м����
			g.fill3DRect(x-10, y-5, 20, 10,false);
			//����Բ
			g.fillOval(x-5, y-5, 10, 10);
			//����
			g.drawLine(x, y, x+15, y);
			break;
		}
	}
	
	
	//@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
			if(e.getKeyCode()==KeyEvent.VK_S)
			{
//				this.x0 = tk.getX()+13;
//				this.y0 = tk.getY()+30;
				this.tk.setDirect(1);
				this.tk.moveDown();
			}else if(e.getKeyCode()==KeyEvent.VK_W)
			{
//				this.x0 = tk.getX()+13;
//				this.y0 = tk.getY();
				this.tk.setDirect(0);
				this.tk.moveUp();
			}else if(e.getKeyCode()==KeyEvent.VK_A)
			{
//				this.x0 = tk.getX();
//				this.y0 = tk.getY()+13;
				this.tk.setDirect(2);
				this.tk.moveLeft();
			}else if(e.getKeyCode()==KeyEvent.VK_D)
			{
//				this.x0 = tk.getX()+30;
//				this.y0 = tk.getY()+13;
				this.tk.setDirect(3);
				this.tk.moveRight();
			}
			//tank�ı��ٶ�
			if(e.getKeyCode()==KeyEvent.VK_UP){
				int i = this.tk.getSpeed();
				this.tk.setSpeed(i+1);
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				int i = this.tk.getSpeed();
				if(i-1>=0)
					this.tk.setSpeed(i-1);
			}
			//����J���ӵ�
			if(e.getKeyCode()==KeyEvent.VK_J)
			{
				this.tk.shotEnemy();
			}
			//�ı��ӵ��ٶ�
			if(this.tk.s!=null&&this.tk.s.isLive)
			{
				if(e.getKeyCode()==KeyEvent.VK_LEFT){
					
					this.tk.s.setSpeed(this.tk.s.getSpeed()+1);
				}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					int i = this.tk.s.getSpeed();
					if(i-1>0)
						this.tk.s.setSpeed(i-1);
				}
			}
			//����repaint()�������ػ�
			this.repaint();
		
			
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//ÿ��100ms�ػ�
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.repaint();
			
		}
	}
}







