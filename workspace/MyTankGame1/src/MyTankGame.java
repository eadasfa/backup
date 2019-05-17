/*
 * ���ܣ�̹�˴�ս
 * 1.����̹��
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
	//����һ��̹��
	Hero hero=null;
	public MyPanel()
	{
		hero = new Hero(100,100);
		
	}
	//�ؽ�paint
	public void paint(Graphics g)
	{
		//��ʼ��
		super.paint(g);
		g.fillRect(0, 0,400,300);
		
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
		
	}
	public void drawTank(int x,int y,Graphics g,int direct,int type)
	{  
		//�ж�ʲô����̹��
	
		switch(type)
		{
		case 0:
			g.setColor(Color.cyan);
			break;
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		//�жϷ���
		switch(direct)
		{
		//����
		case 0:
			
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x, y, 5, 30,false);
			//�����ұߵľ���
			g.fill3DRect(x+15, y, 5, 30,false);
			//�����м����
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//����Բ
			g.fillOval(x+4, y+10, 10, 10);
			//����
			g.drawLine(x+10, y+15, x+10, y);
			
			break;
		}
	}
	
}
//̹����
class Tank
{
	//��ʾ̹�˵ĺ�����
	int x=0 ;
	//������
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
//�ҵ�̹��
class Hero extends Tank
{
	public Hero(int x,int y)
	{
		super(x,y);
	}
}















