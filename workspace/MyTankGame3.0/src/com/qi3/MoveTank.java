/*
 * 1.画出一个能用键盘移动的坦克
 * 2.画出敌人坦克
 * 3.画出子弹
 * 4.画出能动的子弹
 * 5.当子弹碰到敌人坦克时，敌人炸毁（爆炸效果）
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
		//panel大小
		int x=600;
		int y=500;
		mp = new MyPanel2(x,y);
		//  启动线程
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
	//窗体大小
	int x0=400;
	int y0=300;
	
	Hero tk = null;
	private int num = 3;
	Vector<EnemyTank> enemy = new Vector<EnemyTank>();
	//定义三张图片
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

		//初始化敌人坦克
		for(int i=0;i<this.num;i++)
		{
			//创建敌人坦克
			EnemyTank em = new EnemyTank((i+1)*50,15,1,1,x0,y0);
			this.enemy.add(em);
			//启动敌人坦克
			Thread t = new Thread(em);
			//给敌人添加子弹
			Bullet s = new Bullet(em.getX()-2,em.getY()+16,1,this.x0,this.y0);
			//加入坦克
			em.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			t.start();
		}
		//三张图片组成一个炸弹
		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	public void paint(Graphics g){

		super.paint(g);
		
		g.fillRect(0, 0, x0, y0);
		
		//画出自己坦克
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
			//让 b 的life减小
			b.lifeDown();
			//如果炸弹生命为零就将该炸弹删除
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		//画出敌人坦克
		for(int i=0;i<this.enemy.size();i++)
		{
			EnemyTank em = this.enemy.get(i);
			if(em.isLive)
			{
				this.drawTank(em.getX(), em.getY(), g, 1, em.getDirect());
				//在画出敌人子弹
				for(int j=0;j<em.ss.size();j++)
				{
					
					//取出子弹
					
					Bullet enemyBullet=em.ss.get(j);
					if(enemyBullet.isLive)
					{
						g.drawOval(enemyBullet.getX(), enemyBullet.getY(), 4, 4);
					}else
					{
						//如果敌人坦克死亡就删除
						em.ss.remove(enemyBullet);
					}
				}
			}	
		}
		
		//画出自己子弹
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
	//判断自己子弹是否击中敌人
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
		//如果敌人坦克向上或向下
		case 0:
		case 1:
			if(s.getX()<et.getX()+10&&
			   s.getX()>et.getX()-10&&
			   s.getY()<et.getY()+15&&
			   s.getY()>et.getY()-15)
				//击中
				//子弹死亡  
			{
				s.isLive=false;
			    //敌方死亡
				et.isLive=false;
				//创建一个炸弹
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
				//创建一个炸弹
				Bomb b=new Bomb(et.getX()-15,et.getY()-10);
				bombs.add(b);
			}
			break;
		}
		
		
	}

	//画坦克
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
			//画坦克(到时再封装成一个函数)
			//1.画出左面的矩形
			g.fill3DRect(x-10, y-15, 5, 30,false);
			//画出右边的矩形
			g.fill3DRect(x+5, y-15, 5, 30,false);
			//画出中间矩形
			g.fill3DRect(x-5, y-10, 10, 20,false);
			//画出圆
			g.fillOval(x-5, y-5, 10, 10);
			//画线
			g.drawLine(x, y, x, y-15);
			break;
		//down
		case 1:
			//画坦克(到时再封装成一个函数)
			//1.画出左面的矩形
			g.fill3DRect(x-10, y-15, 5, 30,false);
			//画出右边的矩形
			g.fill3DRect(x+5, y-15,5, 30,false);
			//画出中间矩形
			g.fill3DRect(x-5, y-10, 10, 20,false);
			//画出圆
			g.fillOval(x-5, y-5, 10, 10);
			//画线
			g.drawLine(x, y, x, y+15);
			break;
		//left
		case 2:
			//画坦克(到时再封装成一个函数)
			//1.画出左面的矩形
			g.fill3DRect(x-15, y-10, 30, 5,false);
			//画出右边的矩形
			g.fill3DRect(x-15, y+5, 30, 5,false);
			//画出中间矩形
			g.fill3DRect(x-10, y-5, 20, 10,false);
			//画出圆
			g.fillOval(x-5, y-5, 10, 10);
			//画线
			g.drawLine(x, y, x-15, y);
			break;
		//right
		case 3:
			//画坦克(到时再封装成一个函数)
			//1.画出左面的矩形
			g.fill3DRect(x-15, y-10, 30, 5,false);
			//画出右边的矩形
			g.fill3DRect(x-15, y+5, 30, 5,false);
			//画出中间矩形
			g.fill3DRect(x-10, y-5, 20, 10,false);
			//画出圆
			g.fillOval(x-5, y-5, 10, 10);
			//画线
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
			//tank改变速度
			if(e.getKeyCode()==KeyEvent.VK_UP){
				int i = this.tk.getSpeed();
				this.tk.setSpeed(i+1);
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				int i = this.tk.getSpeed();
				if(i-1>=0)
					this.tk.setSpeed(i-1);
			}
			//按下J发子弹
			if(e.getKeyCode()==KeyEvent.VK_J)
			{
				if(tk.ss.size()<15)
				{
					this.tk.shotEnemy();
				}
			}
			//改变子弹速度
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
			//调用repaint()函数，重画
			this.repaint();
		
			
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {

		// TODO Auto-generated method stub
		//每隔100ms重绘
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//判断是否击中
			this.isHitEnemy();
			
			
			//重绘
			this.repaint();
			
		}
	}
}







