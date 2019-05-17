/*
 * 画出一个能用键盘移动的坦克
 */
package com.qi2;
import java.awt.*;
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
		this.add(mp);
		this.addKeyListener(mp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
class MyPanel2 extends JPanel implements KeyListener
{
	Hero tk = null;
	//方向
	private int direct=0;
//	//子弹轨迹
//	private int x0;
//	private int y0;
//	public int getX0() {
//		return x0;
//	}
//	public void setX0(int x0) {
//		this.x0 = x0;
//	}
//	public int getY0() {
//		return y0;
//	}
//	public void setY0(int y0) {
//		this.y0 = y0;
//	}
	
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public MyPanel2(){
		tk = new Hero(100,100);
//		this.x0 = tk.getX()+13;
//		this.y0 = tk.getY();
	}
	public void paint(Graphics g){
		super.paint(g);
		
		g.fillRect(0, 0, 400, 300);
		
		this.drawTank(tk.getX(), tk.getY(), g, 0, direct);
		
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
		//down
		case 1:
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
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		//left
		case 2:
			//画坦克(到时再封装成一个函数)
			//1.画出左面的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//画出右边的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//画出圆
			g.fillOval(x+10, y+4, 10, 10);
			//画线
			g.drawLine(x+15, y+10, x, y+10);
			break;
		//right
		case 3:
			//画坦克(到时再封装成一个函数)
			//1.画出左面的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//画出右边的矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//画出中间矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//画出圆
			g.fillOval(x+10, y+4, 10, 10);
			//画线
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
			if(e.getKeyCode()==KeyEvent.VK_DOWN)
			{
//				this.x0 = tk.getX()+13;
//				this.y0 = tk.getY()+30;
				this.setDirect(1);
				int k=tk.getY();
				tk.setY(k+3);
			}else if(e.getKeyCode()==KeyEvent.VK_UP)
			{
//				this.x0 = tk.getX()+13;
//				this.y0 = tk.getY();
				this.setDirect(0);
				int k=tk.getY();
				tk.setY(k-3);
			}else if(e.getKeyCode()==KeyEvent.VK_LEFT)
			{
//				this.x0 = tk.getX();
//				this.y0 = tk.getY()+13;
				this.setDirect(2);
				int k=tk.getX();
				tk.setX(k-3);
			}else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			{
//				this.x0 = tk.getX()+30;
//				this.y0 = tk.getY()+13;
				this.setDirect(3);
				int k=tk.getX();
				tk.setX(k+3);
			}
			//调用repaint()函数，重画
			this.repaint();
		
			
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
class Tank
{
	private int x=0;
	private int y=0;
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
	public Tank(int x,int y){
		this.x = x;
		this.y = y;
	}
		
}
class Hero extends Tank
{
	public Hero(int x,int y){
		super(x, y);
	}
}
class Bullet  implements KeyListener
{
	private int x=0;
	private int y=0;
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
	public Bullet(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public void drawBullet(int x,int y,Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x, y, 3, 3);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}






