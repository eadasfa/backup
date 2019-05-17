package com.qi.fiveinrow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * 棋盘
 * @author Administrator
 *
 */
public class ChessBoard extends JPanel implements MouseListener,MouseMotionListener
{
	private static final long serialVersionUID = -3032472182143269773L;
	private String state = Tools.PREPARING;
	Image chessboardimage = null;
	Vector<Chess> chesses=null;
	/**
	 * value 为每个点对应的全职
	 * 
	 */
	int [][]value = new int[15][15];
	
	/** 记录棋盘棋子:0没棋子  1	为白棋 2 为黑棋 */
	int [][]record = new int[15][15];
	/**
	 * x,y 为鼠标进入方形框
	 */
	private int x=0,y=0;
	//谁先下棋
	String advancer = Tools.PLAYER;
	
	Player player = null;
	Player computer = null;
	double length = Tools.LENGTH; //单格长度
	private int n=0;
	public  ChessBoard()
	{
		value[7][7]=1;
		//若电脑先下
		player = new Player(Tools.BLACK);
		computer = new Player(Tools.WHITE);
		chesses = new Vector<Chess>();
		try {
			
//			chessboardimage = ImageIO.read(
//					new File("images/qipan2.png"));
			//用以下方法调用src中的图片
			//ClassLoader.getSystemResourceAsStream("images/qipan2.png")
			chessboardimage = ImageIO.read(
					(ClassLoader.getSystemResourceAsStream("images/qipan2.png"))); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public void paint(Graphics g)
	{
		
		g.drawImage(chessboardimage,0,0,
				Tools.CHESSBOARD_WIDTH,Tools.CHESSBOARD_HEIGHT,null);
		
		this.drawFour(g);
		this.drawchesses(g);
	}
	public void mouseClicked(MouseEvent e) {
		if(this.state.equals(Tools.STARTING)
				&&record[this.x][this.y]==0)
		{
			//玩家下棋
			if(this.playerPutChess())
			{
				//如果玩家胜利  playerPutChess()返回true
				JOptionPane.showMessageDialog(this, "You win!");
				this.state=Tools.PREPARING;
			}
			
			//电脑下棋   
			if(this.computerPutChess())
			{
				//如果电脑胜利  computerPutChess()返回true
				JOptionPane.showMessageDialog(this, "You fail!");
			}
				
		}
	}
	public void newGame()
	{
		this.state = Tools.STARTING;
		this.chesses.clear();
		player.chesses.clear();
		computer.chesses.clear();
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				value[i][j]=0;
				record[i][j]=0;
				if(i==7&&j==7)
					value[i][j]=1;
			}
		}
		this.state = Tools.PREPARING;
		this.repaint();
	}
	public void startGame()
	{
		this.state = Tools.STARTING;
		if(this.advancer.equals(Tools.COMPUTER))
		{
			this.computerPutChess();
			this.repaint();
		}
	}
	//改变先手
	public String changeAdvancer()
	{
		if(this.state.equals(Tools.PREPARING))
		{
			if(this.advancer.equals(Tools.PLAYER))
			{
				this.advancer=Tools.COMPUTER;
				this.computer.color = Tools.BLACK;
				this.player.color = Tools.WHITE;
			}else
			{
				this.advancer=Tools.PLAYER;
				this.player.color = Tools.WHITE;
			}
		}
		return this.advancer;
	}
	public void huiqi()
	{
		int i=this.chesses.size();
	
		Chess chess = this.chesses.get(i-1);
		int x0 = chess.getX();
		int y0 = chess.getY();
		record[x0][y0]=0;
		
		this.chesses.remove(i-1);
		
		chess = this.chesses.get(i-2);
		int x1 = chess.getX();
		int y1 = chess.getY();
		record[x1][y1]=0;
		
		this.chesses.remove(i-2);
		
		this.changeCloseValue(x0, y0);
		this.changeCloseValue(x1, y1);
			
		if(this.chesses.size()!=0)
			this.state=Tools.STARTING;
		this.repaint();
		
	}
	public boolean playerPutChess()
	{
		
		if(this.state.equals(Tools.STARTING))
		{
			Chess chess = new Chess(this.x, this.y,this.player.color);
			this.chesses.add(chess);
			this.player.addChess(chess);
			Boolean t=this.isWin(this.x, this.y, player.color);
			this.record[this.x][this.y]=Integer.parseInt(this.player.color);//2代表（x,y）处为黑棋
			this.changeCloseValue(this.x, this.y);
			value[x][y]=-1;
			if(this.state.equals(Tools.STARTING))
			{
				this.repaint();
				if(t)
					this.state=Tools.PREPARING;
			}
			return t;
		}else 
			return false;
	}
	//电脑下棋
	public boolean computerPutChess()
	{
		if(this.state.equals(Tools.STARTING))
		{
			int max=0;
			int m,n;
			Vector<Object> q = new Vector<Object>();
			//寻找权值最大的点
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<15;j++)
				{
					if(max<value[i][j])
					{	
						max = value[i][j];
						m=i;n=j;
						q.clear();
						q.add(i*100+j);
					}
					else if(max==value[i][j])
					{
						q.add(i*100+j);
					}
				}
			}
			int radom;
			//随机选取值最大的其中一个
			while(true)
			{
				radom = (int)(Math.random()*q.size());
				if(radom!=q.size())
					break;
			}
			radom = (Integer) q.get(radom);
			m=radom/100;n=radom%100;
			Chess chess = new Chess(m, n, computer.color);
			this.chesses.add(chess);
			this.computer.addChess(chess);
			boolean t = this.isWin(m, n, computer.color);
			this.record[m][n]=Integer.parseInt(this.computer.color);//1代表（x,y）处为白棋
			this.changeCloseValue(m, n);
			value[m][n]=-1;
			if(this.state.equals(Tools.STARTING))
			{
				this.repaint();
				if(t)
					this.state=Tools.PREPARING;
			}
			return t;
		}
		else 
			return false;
	}
	
	
	public void drawFour(Graphics g)
	{
		if(this.record[this.x][this.y]==0)
		{
			g.setColor(Color.red);
			//左上
			g.drawLine((int)(30+length*this.x)-15, (int)(30+length*this.y)-15,
					(int)(30+length*this.x)-15, (int)(30+length*y)-4);
			g.drawLine((int)(30+length*this.x)-15, (int)(30+length*this.y)-15,
					(int)(30+length*this.x)-4, (int)(30+length*this.y)-15);
			//左下
			g.drawLine((int)(30+length*this.x)-15, (int)(30+length*this.y)+8,
					(int)(30+length*this.x)-15, (int)(30+length*this.y)+20);
			g.drawLine((int)(30+length*this.x)-15, (int)(30+length*this.y)+20,
					(int)(30+length*this.x)-4, (int)(30+length*this.y)+20);
			//右上
			g.drawLine((int)(30+length*this.x)+15, (int)(30+length*this.y)-15,
					(int)(30+length*this.x)+15, (int)(30+length*this.y)-4);
			g.drawLine((int)(30+length*this.x)+15, (int)(30+length*this.y)-15,
					(int)(30+length*this.x)+4, (int)(30+length*this.y)-15);
			//右下
			g.drawLine((int)(30+length*this.x)+15, (int)(30+length*this.y)+20,
					(int)(30+length*this.x)+15, (int)(30+length*this.y)+8);
			g.drawLine((int)(30+length*this.x)+15, (int)(30+length*this.y)+20,
					(int)(30+length*this.x)+4, (int)(30+length*this.y)+20);
			
			g.setColor(Color.black);
		}
	}
	public void drawchesses(Graphics g)
	{
		for(int i=0;i<this.chesses.size();i++)
		{
			Chess chess = this.chesses.get(i);
			g.drawImage(chess.image, chess.getX0()-15,
					chess.getY0()-15, 30, 30,null);
			if(i==this.chesses.size()-1)
			{
				g.setColor(Color.red);
				g.fillOval(chess.getX0()-8, chess.getY0()-8, 16, 16);
				g.setColor(Color.white);
				g.fillOval(chess.getX0()-4, chess.getY0()-4, 8, 8);
				g.setColor(Color.black);
			}
		}
	}
	public void mousePressed(MouseEvent e) {
	
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}
	public void mouseDragged(MouseEvent e) {
		
	}
	/**
	 * 鼠标位置转换为棋盘坐标
	 */
	public void mouseMoved(MouseEvent e) {

		double x0 = (e.getX()-30)/length;
		if(x0>(int)x0+0.5)
		{
			x = (int)x0+1;
		}else
		{
			x = (int)x0;
		}
		double y0 = (e.getY()-30)/length;
		if(y0>(int)y0+0.5)
		{
			y = (int)y0+1;
		}else
		{
			y = (int)y0;
		}
		
		if(y>14)	y=14;
		if(x>14)	x=14;
		this.repaint();
	}
	
	public void changeCloseValue(int x,int y)
	{
		int x0=x-4,y0=y;
		while(true)
		{
			if(x0>=0)
			{
				if(record[x0][y0]==0)
				{
					value[x0][y0] = this.getValue(x0, y0);
				}
			}
			x0++;
			if(x0>x+4||x0>14)
			{
				break;
			}
		}
		x0 = x; y0 = y-4;
		while(true)
		{
			if(y0>=0)
			{
				if(record[x0][y0]==0)
				{
					value[x0][y0] = this.getValue(x0, y0);
				}
			}
			y0++;
			if(y0>y+4||y0>14)
			{
				break;
			}
		}
		x0 = x-4; y0 = y-4;
		while(true)
		{
			if(x0>=0&&y0>=0&&x0<=14&&y0<=14)
			{
				if(record[x0][y0]==0)
				{
					value[x0][y0] = this.getValue(x0, y0);
				}
			}
			x0++;y0++;
			if(y0>y+4||y0>14||x0>x+4||x>14)
			{
				break;
			}
		}
		x0 = x-4; y0 = y+4;
		while(true)
		{
			if(x0>=0&&y0>=0&&x0<=14&&y0<=14)
			{
				if(record[x0][y0]==0)
				{
					value[x0][y0] = this.getValue(x0, y0);
				}
			}
			x0++;y0--;
			if(y0<y-4||y0<0||x0>x+4||x>14)
			{
				break;
			}
		}
	}
	public boolean isWin(int x,int y,String color)
	{
		boolean flag=false;
		int a1[] = this.sunLineRow(x, y, color);
		if(a1[0]==5)
			flag=true;
		else
		{
			int b1[] = this.sunLineColumn(x, y, color);
			if(b1[0]==5)
				flag=true;
			else
			{
				int c1[] = this.moonLineLeftUp(x, y, color);
				if(c1[0]==5)
					flag=true;
				else
				{
					int d1[] = this.moonLineLeftDown(x, y, color);
					if(d1[0]==5)
						flag=true;
				}
			}
		}
		
		return flag;
	}
	/**
	 * 权值算法
	 * @param x
	 * @param y
	 * @return
	 */
	public int getValue(int x,int y)
	{
		int num=0;
		int a1[] = this.sunLineRow(x, y, computer.color);
		int b1[] = this.sunLineColumn(x, y, computer.color);
		int c1[] = this.moonLineLeftUp(x, y, computer.color);
		int d1[] = this.moonLineLeftDown(x, y, computer.color);
		Vector<int[]> m = new Vector<int[]>();
		m.add(a1);m.add(b1);m.add(c1);m.add(d1);
		this.n=0;
		for(int i=0;i<m.size();i++)
		{
			int t[]=m.get(i);
			num = num+this.isFive(t, computer.color)
					 +this.isFour(t, computer.color)
					 +this.isThree(t, computer.color)
					 +this.isTwo(t, computer.color);
		}
		if(n>=2)
		{
			num+=50000;
		}
		int a2[]=this.sunLineRow(x, y, player.color);		
		int b2[] = this.sunLineColumn(x, y, player.color);		
		int c2[] = this.moonLineLeftUp(x, y, player.color);
		int d2[] = this.moonLineLeftDown(x, y,  player.color);
		m.clear();
		m.add(a2);m.add(b2);m.add(c2);m.add(d2);
		this.n=0;
		for(int i=0;i<m.size();i++)
		{
			int t[]=m.get(i);
			num = num+this.isFive(t, computer.color)
					 +this.isFour(t, computer.color)
					 +this.isThree(t, computer.color)
					 +this.isTwo(t, computer.color);
		}
		if(n>=2)
		{
			num+=40000;
		}
		return num;
	}
	
	/**
	 * a[0] 代表相同颜色棋子数
	 * a[1] 上边，左边 ，左上，左下空格数
	 * a[2] 下边右边，右上，右下空格数
	 * @param x 
	 * @param y
	 * @param color 棋子颜色
	 * @return 返回a[]的数组
	 */
	public int [] sunLineRow(int x,int y,String color1)
	{
		
		int a [] =new int[3];
		a[0] =0;  //代表相同颜色棋子数
		a[1] = 0;//上边，左边 ，左上，左下空格数
		a[2]=0; //下边右边，右上，右下空格数
		int color = Integer.parseInt(color1);	
		record[x][y] = color;
		int x0=x-4,y0=y;
		boolean flag = false;
		while(true)
		{
			if(x0>=0&&x0<=14)
			{
				if(record[x0][y0]==color)
				{
					a[0]++;
				}
				else
				{
					if(x0<x)
					{
						a[0]=0;
					}else if(x0>x)
					{
						//此时（x0,y0）已经是最左边与color相同的棋子
						//判断左右空格
						/*左边*/
						for(int i=x0-a[0]-1;i>=0;i--)
						{
							if(record[i][y0]==0)
							{
								a[1]++;
							}else{
								break;
							}
						}
						/*右边*/
						for(int i=x0;i<=14;i++)
						{
							if(record[i][y0]==0)
							{
								a[2]++;
							}else{
								break;
							}
						}
						flag = true;
					}
				}
			}
			x0++;
			if(x0>14||x0>x+4||flag)
			{
				break;
			}
		}
		
		record[x][y]=0;
		
		return a;	
	}
	public int [] sunLineColumn(int x,int y,String color1)
	{
		int a [] =new int[3];
		a[0] =0;  //代表相同颜色棋子数
		a[1] = 0;//上边，左边 ，左上，左下空格数
		a[2]=0; //下边右边，右上，右下空格数
		int color = Integer.parseInt(color1);	
		record[x][y] = color;
		int x0=x,y0=y-4;
		boolean flag = false;
		while(true)
		{
			if(y0>=0&&y0<=14)
			{
				if(record[x0][y0]==color)
				{
					a[0]++;
				}
				else
				{
					if(y0<y)
					{
						a[0]=0;
					}else if(y0>y)
					{
						//此时（x0,y0）已经是最左边与color相同的棋子
						//判断左右空格
						/*左边*/
						for(int i=y0-a[0]-1;i>=0;i--)
						{
							if(record[x0][i]==0)
							{
								a[1]++;
							}else{
								break;
							}
						}
						/*右边*/
						for(int i=y0;i<=14;i++)
						{
							if(record[x0][i]==0)
							{
								a[2]++;
							}else{
								break;
							}
						}
						flag = true;
					}
				}
			}
			y0++;
			if(y0>14||y0>y+4||flag)
			{
				break;
			}
		}
		record[x][y]=0;
		return a;
	}
	public int [] moonLineLeftUp(int x,int y,String color1)
	{
		int a [] =new int[3];
		a[0] =0;  //代表相同颜色棋子数
		a[1] = 0;//上边，左边 ，左上，左下空格数
		a[2]=0; //下边右边，右上，右下空格数
		int color = Integer.parseInt(color1);	
		record[x][y] = color;
		int x0=x-4,y0=y-4;
		boolean flag = false;
		while(true)
		{
			if(y0>=0&&y0<=14&&x0>=0&&x0<=14)
			{
				if(record[x0][y0]==color)
				{
					a[0]++;
				}
				else
				{
					if(y0<y&&x0<x)
					{
						a[0]=0;
					}else if(y0>y&&x0>x)
					{
						//此时（x0,y0）已经是最左边与color相同的棋子
						//判断左右空格
						/*左边*/
						int i,j;
						for(i=x0-a[0]-1,j=y0-a[0]-1;i>=0&&j>=0;i--,j--)
						{
							if(record[i][j]==0)
							{
								a[1]++;
							}else{
								break;
							}
						}
						/*右边*/
						for(i=x0,j=y0;i<=14&&j<=14;i++,j++)
						{
							if(record[i][j]==0)
							{
								a[2]++;
							}else{
								break;
							}
						}
						flag = true;
					}
				}
			}
			x0++;
			y0++;
			if(y0>14||y0>y+4||flag||x0>14||x0>x+4)
			{
				break;
			}
		}
		record[x][y]=0;
		return a;
	}
	public int [] moonLineLeftDown(int x,int y,String color1)
	{
		int a [] =new int[3];
		a[0] =0;  //代表相同颜色棋子数
		a[1] = 0;//上边，左边 ，左上，左下空格数
		a[2]=0; //下边右边，右上，右下空格数
		int color = Integer.parseInt(color1);	
		record[x][y] = color;
		int x0=x-4,y0=y+4;
		boolean flag = false;
		while(true)
		{
			if(y0>=0&&y0<=14&&x0>=0&&x0<=14)
			{
				if(record[x0][y0]==color)
				{
					a[0]++;
				}
				else
				{
					if(y0>y&&x0<x)
					{
						a[0]=0;
					}else if(y0<y&&x0>x)
					{
						//此时（x0,y0）已经是最左边与color相同的棋子
						//判断左右空格
						/*左边*/
						int i,j;
						for(i=x0-a[0]-1,j=y0+a[0]+1;i>=0&&j<=14;i--,j++)
						{
							if(record[i][j]==0)
							{
								a[1]++;
							}else{
								break;
							}
						}
						/*右边*/
						for(i=x0,j=y0;i<=14&&j>=0;i++,j--)
						{
							if(record[i][j]==0)
							{
								a[2]++;
							}else{
								break;
							}
						}
						flag = true;
					}
				}
			}
			x0++;
			y0--;
			if(y0<0||y0<y-4||flag||x0>14||x0>x+4)
			{
				break;
			}
		}
		record[x][y]=0;
		return a;
	}
	public int isFive(int a[],String color)
	{
		int value=0;
		if(a[0]>=5)
		{
			if(color.equals(computer.color))
				value=1000000;
			else if(color.equals(player.color))
				value=500000;
		}
		return value;
	}
	public int isFour(int a[],String color)
	{
		int value=0;
		if(a[0]==4)
		{
			//活4
			if(a[1]>0&&a[2]>0)
			{	
				if(color.equals(computer.color))
					value = 200000;
				else if(color.equals(player.color))
					value = 130000;
			}
			//冲4
			else if((a[1]>0&&a[2]==0)||(a[1]==0&&a[2]>0))
			{
					value = 14000;
					n++;
			}
			//死4
			else if(a[1]==0&&a[2]==0)
			{
				value = 0;
			}
		}
		return value;
	}
	public int isThree(int a[],String color)
	{
		int value = 0;
		if(a[0]==3)	
		{
			//活3
			if(a[1]>0&&a[2]>0&&(a[1]+a[2]>=3))
			{
				if(color.equals(computer.color))
					value = 14000;
				else if(color.equals(player.color))
					value = 10000;
				n++;
			}
			//眠3
			else if((a[1]==0&&a[2]>=2)||(a[1]>=2&&a[2]==0))
			{
				value = 3000;
			}
			else 
				value = 0;
		}
		return value;
	}
	public int isTwo(int a[],String color)
	{
		int value = 0;
		if(a[0]==2)
		{
			//活2
			if(a[1]>0&&a[2]>0&&(a[1]+a[2]>=4))
			{
				value = 1000;
			}
			//眠2
			else if((a[1]==0&&a[2]>=3)&&a[2]==0&&a[1]>=3)
			{
				value = 500;
			}
			//死2
			else if(a[1]+a[2]<=2)
			{
				value = 0;
			}
		}
		return value;
	}
}

class Player
{
	String color = Tools.BLACK;
	Vector<Chess> chesses = null;
	public Player(String color)
	{
		this.color = color;
		chesses = new Vector<Chess>();
	}
	public void addChess(Chess chess)
	{
		this.chesses.add(chess);
	}
}
