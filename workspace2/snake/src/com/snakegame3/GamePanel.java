package com.snakegame3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener,Runnable,KeyListener{
	//调用颜色的数据常量
	private int ii=0;
	//游戏状态
	private String state = Tools.PREPARED;
	//边界起点
	private int x0=Tools.x0;
	private int y0=Tools.y0;
	int t =0;//改变方向后行走步数
	/**
	 * 休眠时间
	 */
	private int sleeptime=500;
	private Block bl = null;
	private Snake snake = null;
	JButton bt1 = null;//开始
	JButton bt2 = null;//暂停
	JLabel jl = null;	//长度
	JLabel dqcd = null;
	//计时
	JLabel time = null,t2;
	int hour=0,min=0,sec=0;
	JFrame jf=null;
	public GamePanel(JFrame jf) {
		this.jf = jf;
		//布局方式设置为空
		this.setLayout(null);
		//创建一条snake
		snake = new Snake();
		//产生一个block
		this.process_a_block();
		//注释
		dqcd = new JLabel("当前长度：");
		dqcd.setFont(new Font("宋体", Font.BOLD, 18));
		dqcd.setBounds(680, 30, 100, 20);
		this.add(dqcd);
		jl = new JLabel(this.snake.getSize()+"");
		jl.setForeground(Color.red);
		jl.setFont(Tools.font);
		this.add(jl);
		jl.setBounds(700, 50, 50, 30);
		//时间
		t2 = new JLabel("所花时间:");
		t2.setFont(Tools.font);
		t2.setBounds(680, 80, 100, 20);
		this.add(t2);
		//每隔1s触发Actionlistener
		javax.swing.Timer t = new Timer(1000,this);
		t.start();
		
		time = new JLabel("  00:00:00");
		time.setBounds(680, 110, 100, 20);
		this.add(time);
		bt1 = new JButton("开始");
		bt2 = new JButton("暂停");
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt1.setBounds(10, 8, 100, 30);
		bt2.setBounds(110, 8, 100, 30);
		this.add(bt1);
		this.add(bt2);

	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		//画出边界
	
		g.fillRect(x0, y0, Tools.GAME_PANEL_WIDTH, Tools.GAME_PANEL_HEIGHT);
		
		//画出“食物”
		this.drawBlock(g,bl,Color.CYAN);
		//画出snake
		this.drawSnake(g);
	}
	
	@Override
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(sleeptime);
				if(!this.state.equals(Tools.DEATH))
				{
					snake.move();
					t++;
				}
				if(this.snake.eatFood(bl))
				{
					new PlaySounds("eat.wav").start();
					this.process_a_block();
					jl.setText(this.snake.getSize()+"");
				}
				this.repaint();
				if((!this.state.equals(Tools.DEATH))&&this.isSnakeDead())
				{
					this.state = Tools.DEATH;
					new PlaySounds("over.wav").start();
					JOptionPane.showMessageDialog(this, "失败！");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	//画方块
	public void drawBlock(Graphics g,Block bk,Color color)
	{
		g.setColor(color);
		g.fillOval(bk.getX(), bk.getY(), bk.getWidth(), bk.getWidth());
		g.setColor(Color.black);
	}
	//随即产生一个block
	public void process_a_block()
	{	
		int x=x0,y=y0;
		while(true)
		{
			// true 代表x，y可以用
			boolean t= true;
			x=20*(int)(Math.random()*(Tools.GAME_PANEL_WIDTH/20-1))+x0;
			y=20*(int)(Math.random()*(Tools.GAME_PANEL_HEIGHT/20-1))+y0;
			//判断产生的block与snake是否重叠
			for(int i=0;i<this.snake.getSize();i++)
			{
				Block bb = this.snake.bk.get(i);
				if(bb.getX()==x&&bb.getY()==y)
				{
					t = false;
					break;
				}
			}
			if(t)
			{
				break;
			}
		}
		bl = new Block(x,y);
	}	
	//画snake
	public void drawSnake(Graphics g)
	{
		for(int i=this.snake.getSize()-1;i>=0;i--)
		{
			
			Block bk = this.snake.bk.get(i);
			if(i==0){
				this.drawBlock(g, bk,Color.red);
				if(this.snake.getDirect()=="R")
				{
					g.fillOval(bk.getX()+12, bk.getY()+4, 4, 4);
					g.fillOval(bk.getX()+12, bk.getY()+12, 4, 4);
				}else if(this.snake.getDirect()=="L")
				{
					g.fillOval(bk.getX()+4, bk.getY()+4, 4, 4);
					g.fillOval(bk.getX()+4, bk.getY()+12, 4, 4);
				}else if(this.snake.getDirect()=="U")
				{
					g.fillOval(bk.getX()+4, bk.getY()+4, 4, 4);
					g.fillOval(bk.getX()+12, bk.getY()+4, 4, 4);
				}else if(this.snake.getDirect()=="D")
				{
					g.fillOval(bk.getX()+4, bk.getY()+12, 4, 4);
					g.fillOval(bk.getX()+12, bk.getY()+12, 4, 4);
				}
				continue;
			}
			this.drawBlock(g, bk,Tools.colors().get(ii));
			this.ii=(this.ii+1)%Tools.colors().size();
		}
	}
	/**
	 * timeChange为true表示每秒增加一秒
	 *  		  false为时间初始化
	 * @param timeChage
	 */
	public void changeTime(boolean timeChage)
	{
		if(timeChage)
		{
			this.sec++;//增加一秒
			if(this.sec==60)
			{
				this.sec = 0;
				this.min++;//增加一分
				if(this.sec==60)
				{
					this.min=0;
					this.hour++;//增加一小时
				}
			}
		}else
		{
			this.sec=0;this.min=0;this.hour=0;
		}
		String s1=""+this.sec,s2=""+this.min,s3=""+this.hour;
		if(this.sec<10)
		{
			s1="0"+this.sec;
		}
		if(this.min<10)
		{
			s2="0"+this.min;
		}
		if(this.hour<10)
		{
			s3="0"+this.hour;
		}
		this.time.setText("  "+s3+":"+s2+":"+s1);
	}
	/**
	 * 判断蛇是否死亡
	 * @return
	 */
	public boolean isSnakeDead()
	{
		boolean flag = false;
		if(this.snake.getHeadX()>x0+Tools.GAME_PANEL_WIDTH-20||
				this.snake.getHeadX()<x0||
				this.snake.getHeadY()>Tools.GAME_PANEL_HEIGHT+y0-20||
				this.snake.getHeadY()<y0)
		{
			flag = true;
		}else
		{
			for(int i=1;i<this.snake.getSize();i++)
			{
				Block bb = this.snake.bk.get(i);
				if(this.snake.getHeadX()==bb.getX()&&
						this.snake.getHeadY()==bb.getY())
				{
					flag = true;
					break;
				}
			}
		}
		
		return flag;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(this.snake.getSpeed()>0)
		{
			if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				if(!this.snake.getDirect().equals("L"))
				{
					if(t!=0)
					{
						this.snake.setDirect("R");
						this.sleeptime = 100;
						t=0;
					}
				}
			}
			else if(e.getKeyCode()==KeyEvent.VK_LEFT)
			{
				if(!this.snake.getDirect().equals("R"))
				{
					if(t!=0)
					{
						this.snake.setDirect("L");
						this.sleeptime = 100;
						t=0;
					}
				}
			}
			else if(e.getKeyCode()==KeyEvent.VK_UP)
			{
				if(!this.snake.getDirect().equals("D"))
				{
					if(t!=0)
					{
						this.snake.setDirect("U");
						this.sleeptime = 100;
						t=0;
					}
					
				}
			}
			else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			{
				if(!this.snake.getDirect().equals("U"))
				{
					if(t!=0)
					{
						this.snake.setDirect("D");
						this.sleeptime = 100;
						t=0;
					}
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
			this.sleeptime = 500;
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.state.equals(Tools.RUNNING))
		{
			this.changeTime(true);
		}
		// 开始
		if(e.getSource()==bt1)
		{
			if(this.state.equals(Tools.PREPARED))
			{
				this.snake.setSpeed(Tools.SPEED);
				this.state = Tools.RUNNING;
				this.bt1.setText("新游戏");
			}
			else if(this.state.equals(Tools.RUNNING)
					||this.state.equals(Tools.PAUSE)
					||this.state.equals(Tools.DEATH))
			{
				
				this.snake = new Snake();
				this.process_a_block();
				jl.setText(this.snake.getSize()+"");
				this.bt1.setText("开始");
				this.changeTime(false);//时间初始化
				this.state = Tools.PREPARED;
			}
		}
		else if(e.getSource()==bt2)
		{
			if(this.state.equals(Tools.RUNNING))
			{
				this.snake.setSpeed(0);	
				this.state = Tools.PAUSE;
				this.bt2.setText("重新开始");
				
			}
			else if(this.state.equals(Tools.PAUSE))
			{
				this.snake.setSpeed(Tools.SPEED);
				this.state = Tools.RUNNING;
				this.bt2.setText("暂停");
			}
		}
		//将焦点集中在顶层容器上
		jf.requestFocusInWindow();
	}
	
}
/**
 * 组成贪吃蛇的“块”
 * @author Administrator
 *
 */
