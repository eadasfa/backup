/*
 * 1.画出一个能用键盘移动的坦克
 * 2.画出敌人坦克
 * 3.画出子弹
 * 4.画出能动的子弹
 * 5.当子弹碰到敌人坦克时，敌人炸毁（爆炸效果）
 * 6.防止敌人坦克重叠运动
 *   6.1决定把判断是否碰撞的函数写到enemytank
 * 7.可以分关
 *   7.1做一个开始的panelll
 *   7.2闪烁
 * 8.可以在玩时暂停和继续
 *    当用户点击暂停时，子弹速度、
 *    坦克速度设为0，并让坦克方向不变
 * 9可以记录成绩
 *  9.1用文件流
 *  9.2单写一个纪录类，完成对玩家的记录
 *  9.3先完成保存共击毁多少敌人坦克
 * 10.java操作声音
 */
package com.qi;
import java.awt.*; 
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
public class MoveTank extends JFrame implements ActionListener{

	MyPanel2 mp=null;
	//定义一个开始面板、
	MyStartPanel msp = null;
	//做出我需要的菜单
	
	JMenuBar jmb = null;
	//开始游戏
	JMenu jm1 = null;
	JMenuItem jmi1=null;
	//退出系统
	JMenuItem jmi2 =null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoveTank mt=new MoveTank();
	}

	//构造函数
	public MoveTank()
	{
		//panel大小
		
		//mp = new MyPanel2();
		//  启动线程
		//Thread t=new Thread(mp);
		//t.start();
		//this.add(mp);
		//注册监听
		//this.addKeyListener(mp);
		//创建菜单及选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏（G）");
		//设置快捷方式
		jm1.setMnemonic('G');
		jmi1 = new JMenuItem("开始新游戏（N）");
		jmi2 = new JMenuItem("退出游戏（E）");
		jmi1.addActionListener(this);
		jmi2.setActionCommand("exit");
		//设置快捷方式
		jmi2.setMnemonic('E');
		//对JMI1响应
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jmb.add(jm1);
		
		msp = new MyStartPanel();
		this.add(msp);
		Thread t=new Thread(msp);
		t.start();
		
		this.setJMenuBar(jmb);
		this.setSize(600,500);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//对用户不同的点击做出不同处理
		if(arg0.getActionCommand().equals("newgame")){
			//panel大小
			
			mp = new MyPanel2();
			//  启动线程
			Thread t=new Thread(mp);
			t.start();
			//先删除就得面板
			this.remove(msp);
			this.add(mp);
			//注册监听
			this.addKeyListener(mp);
			//刷新，显示
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}
//		else if(arg0.getActionCommand().equals("exit")){
//			//用户点击退出
//			//保存击毁数量
//			Recorder.keepRecording();
//			System.exit(0);
//		}
	}
}
class MyStartPanel extends JPanel implements Runnable
{
	int times=0;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//提示信息
		//设置字体
		if(times%2==0){
			g.setColor(Color.yellow);
			Font myFont = new Font("华文新魏",Font.BOLD ,30);
			g.setFont(myFont);
			g.drawString("stage:1", 150, 125);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			
			//重画
			this.repaint();
		}
	}
}
class MyPanel2 extends JPanel implements KeyListener,Runnable
{
	
	
	Hero tk = null;
	//num 为敌人坦克 数量
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
	public MyPanel2(){

		
		tk = new Hero(200,170,0,5);

		//初始化敌人坦克
		for(int i=0;i<this.num;i++)
		{
			//创建敌人坦克
			EnemyTank em = new EnemyTank((i+1)*50,15,1,1);
			this.enemy.add(em);
			//启动敌人坦克
			Thread t = new Thread(em);
			//给敌人添加子弹
			Bullet s = new Bullet(em.getX()-2,em.getY()+16,1);
			//加入坦克
			em.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			t.start();
		}
		//三张图片组成一个炸弹
		try{
			//测试文件是否存在！
//			 File file=new File("bomb_2.gif");
//             	System.out.println(file.exists());
             //文件前不加路径只有在同一工程下
			image1=ImageIO.read(new File("bomb_1.gif"));
			image2=ImageIO.read(new File("bomb_2.gif"));
			image3=ImageIO.read(new File("bomb_3.gif"));
		} catch(Exception e)
		{
			e.printStackTrace();
		}
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	public void paint(Graphics g){

		super.paint(g);
		
		g.fillRect(0, 0, 400, 300);
		
		//画出自己坦克
		if(tk.isLive)
		{
			this.drawTank(tk.getX(), tk.getY(), g, 0, this.tk.getDirect());
		}
		
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
		    g.setColor(Color.CYAN);
			if(!s.isLive)
			{
				tk.ss.remove(s);
			}else if(s!=null)
			{
				g.drawOval(s.getX(), s.getY(), 4, 4);
			}
			
		}
	}
	//判断敌人的子弹是否击中我
		public void hitMe()
		{
			for(int i=0;i<enemy.size();i++)
			{
				EnemyTank et = enemy.get(i);
				for(int j=0;j<et.ss.size();j++)
				{
					Bullet s=et.ss.get(j);
					if(s.isLive)
					{
						if(tk.isLive)
						{
							this.hitTank(s, tk);
						}
					}
				}
			}
		}
	//判断自己子弹是否击中敌人
	public void hitEnemy()
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
	// 判断子弹是否击中坦克
	public void hitTank(Bullet s,Tank et)
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
				et.setX(-30);
				et.setY(-30);
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
				et.setX(-30);
				et.setY(-30);
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
				//判断自己坦克与其他坦克是否重叠
				
				this.tk.setDirect(1);
				boolean isdouble=true;
				for(int i=0;i<enemy.size();i++){
					EnemyTank et=enemy.get(i);
					if(!tk.isDouble(et))
						isdouble = false;
				}
				if(isdouble)
				{
					this.tk.moveDown();
				}
			}else if(e.getKeyCode()==KeyEvent.VK_W)
			{
//				this.x0 = tk.getX()+13;
//				this.y0 = tk.getY();
				this.tk.setDirect(0);
				boolean isdouble=true;
				for(int i=0;i<enemy.size();i++){
					EnemyTank et=enemy.get(i);
					if(!tk.isDouble(et))
						isdouble = false;
				}
				if(isdouble)
				{
					this.tk.moveUp();
				}
			}else if(e.getKeyCode()==KeyEvent.VK_A)
			{
//				this.x0 = tk.getX();
//				this.y0 = tk.getY()+13;
				
				this.tk.setDirect(2);
				boolean isdouble=true;
				for(int i=0;i<enemy.size();i++){
					EnemyTank et=enemy.get(i);
					if(!tk.isDouble(et))
						isdouble = false;
				}
				if(isdouble)
				{
					this.tk.moveLeft();
				}
			}else if(e.getKeyCode()==KeyEvent.VK_D)
			{
//				this.x0 = tk.getX()+30;
//				this.y0 = tk.getY()+13;
				this.tk.setDirect(3);
				boolean isdouble=true;
				for(int i=0;i<enemy.size();i++){
					EnemyTank et=enemy.get(i);
					if(!tk.isDouble(et))
						isdouble = false;
				}
				if(isdouble)
				{
					this.tk.moveRight();
				}
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
			this.hitEnemy();
			this.hitMe();
			//重绘
			this.repaint();
			
		}
	}
}







