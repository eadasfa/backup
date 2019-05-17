/*
 * 功能：坦克大战
 * 1.画出坦克
 */
import java.awt.*;
import javax.swing.*;

public class MyTankGame extends JFrame {
	
	MyPanel mp;
	public static void main(String[]args)
	{
		MyTankGame mg=new MyTankGame();
	}
	public MyTankGame()
	{
		mp = new MyPanel();
		this.add(mp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
class MyPanel extends JPanel
{
	//定义一个坦克
	Hero hero=null;
	public MyPanel()
	{
		hero = new Hero(100,100);
		
	}
	//重建paint
	public void paint(Graphics g)
	{
		//初始化
		super.paint(g);
		g.fillRect(0, 0,400,300);
		
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
		
	}
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{  
		//判断什么类型坦克
	
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		//判断方向
		switch(direct)
		{
		//向上
		case 0:
			
			//画坦克(到时再封装成一个函数)
			//1.画出左面的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//画出右边的矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//画出圆
			g.fillOval(x+4, y+10, 10, 10);
			//画线
			g.drawLine(x+10, y+15, x+10, y);
			
			break;
		}
	}
	
}
//坦克类
class Tank
{
	//表示坦克的横坐标
	int x=0 ;
	//纵坐标
	int y=0 ; 
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
}
//我的坦克
class Hero extends Tank
{
	public Hero(int x,int y)
	{
		super(x,y);
	}
}















