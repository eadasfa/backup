/*
 * 1.����һ�����ü����ƶ���̹��
 * 2.��������̹��
 * 3.�����ӵ�
 * 4.�����ܶ����ӵ�
 * 5.���ӵ���������̹��ʱ������ը�٣���ըЧ����
 */
package com.qi3;
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
		//panel��С
		int x=600;
		int y=500;
		mp = new MyPanel2(x,y);
		//  �����߳�
		Thread t=new Thread(mp);
		t.start();
		this.add(mp);
		this.addKeyListener(mp);
		this.setSize(x,y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
class MyPanel2 extends JPanel implements KeyListener,Runnable
{
	//�����С
	int x0=400;
	int y0=300;
	
	Hero tk = null;
	private int num = 3;
	Vector<EnemyTank> enemy = new Vector<EnemyTank>();
	//��������ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	Vector<Bomb> bombs = new Vector<Bomb>();
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public MyPanel2(int x0,int y0){

		this.x0=x0;
		this.y0=y0;

		tk = new Hero(100,100,0,5,x0,y0);

		//��ʼ������̹��
		for(int i=0;i<this.num;i++)
		{
			//��������̹��
			EnemyTank em = new EnemyTank((i+1)*50,15,1,1,x0,y0);
			this.enemy.add(em);
			//��������̹��
			Thread t = new Thread(em);
			//����������ӵ�
			Bullet s = new Bullet(em.getX()-2,em.getY()+16,1,this.x0,this.y0);
			//����̹��
			em.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			t.start();
		}
		//����ͼƬ���һ��ը��
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	public void paint(Graphics g){

		super.paint(g);
		
		g.fillRect(0, 0, x0, y0);
		
		//�����Լ�̹��
		this.drawTank(tk.getX(), tk.getY(), g, 0, this.tk.getDirect());
		
		for(int i=0;i<bombs.size();i++)
		{   
			Bomb b=bombs.get(i);
			if(b.life>6)
			{
				
				g.drawImage(image1, b.getX(), b.getY(), 30, 30,this);
			}else if(b.life>3)
			{
			
				g.drawImage(image2, b.getX(), b.getY(), 30, 30,this);
			}else
			{
				
				g.drawImage(image3, b.getX(), b.getY(), 30, 30,this);
			}
			//�� b ��life��С
			b.lifeDown();
			//���ը������Ϊ��ͽ���ը��ɾ��
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		//��������̹��
		for(int i=0;i<this.enemy.size();i++)
		{
			EnemyTank em = this.enemy.get(i);
			if(em.isLive)
			{
				this.drawTank(em.getX(), em.getY(), g, 1, em.getDirect());
				//�ڻ��������ӵ�
				for(int j=0;j<em.ss.size();j++)
				{
					
					//ȡ���ӵ�
					
					Bullet enemyBullet=em.ss.get(j);
					if(enemyBullet.isLive)
					{
						g.drawOval(enemyBullet.getX(), enemyBullet.getY(), 4, 4);
					}else
					{
						//�������̹��������ɾ��
						em.ss.remove(enemyBullet);
					}
				}
			}	
		}
		
		//�����Լ��ӵ�
		for(int i=0;i<tk.ss.size();i++)
		{
			Bullet s = tk.ss.get(i);
			if(!s.isLive)
			{
				tk.ss.remove(s);
			}else if(s!=null)
			{
				g.drawOval(s.getX(), s.getY(), 4, 4);
			}
			
		}
	}
	//�ж��Լ��ӵ��Ƿ���е���
	public void isHitEnemy()
	{
		for(int i=0;i<tk.ss.size();i++)
		{
			Bullet s=tk.ss.get(i);
			if(s.isLive)
			{
				for(int j=0;j<enemy.size();j++)
				{
					EnemyTank et=enemy.get(j);
					if(et.isLive)
					{
						this.hitTank(s, et);
					}
				}
			}
		}
	}
	public void hitTank(Bullet s,EnemyTank et)
	{
		switch(et.getDirect())
		{
		//�������̹�����ϻ�����
		case 0:
		case 1:
			if(s.getX()<et.getX()+10&&
			   s.getX()>et.getX()-10&&
			   s.getY()<et.getY()+15&&
			   s.getY()>et.getY()-15)
				//����
				//�ӵ�����  
			{
				s.isLive=false;
			    //�з�����
				et.isLive=false;
				//����һ��ը��
				Bomb b=new Bomb(et.getX()-10,et.getY()-15);
				bombs.add(b);	
			}
			break;
		case 2:
		case 3:
			if(s.getX()<et.getX()+15&&
			s.getX()>et.getX()-15&&
			s.getY()<et.getY()+10&&
			s.getY()>et.getY()-10)
			{
				s.isLive=false;
				et.isLive=false;
				//����һ��ը��
				Bomb b=new Bomb(et.getX()-15,et.getY()-10);
				bombs.add(b);
			}
			break;
		}
		
		
	}

	//��̹��
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
				if(tk.ss.size()<15)
				{
					this.tk.shotEnemy();
				}
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
			//�ж��Ƿ����
			this.isHitEnemy();
			
			
			//�ػ�
			this.repaint();
			
		}
	}
}







