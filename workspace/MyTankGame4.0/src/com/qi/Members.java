
package com.qi;
import java.awt.*;


import java.util.*;
import javax.swing.*;
import java.awt.event.*;
class Tank
{	
	boolean isLive = true;
	private int x=0;
	private int y=0;
	//0  up  1 down  2 left 3right
	private int direct=0;
	//speed
	private int speed = 1;
	public Tank(int x,int y,int direct,int speed){
		this.x = x;
		this.y = y;
		
		this.direct = direct;
		this.speed = speed;
	}
	//�ж��Լ�ǰ��һ���Ƿ�����̹���ص�
	public boolean isDouble(Tank et)
	{
		if(this.getDirect()==0)
		{
			if(et.getDirect()==0||et.getDirect()==1)
			{
				if(this.getX()-10<et.getX()+10&&
				   this.getX()-10>et.getX()-10&&
				   this.getY()-16<et.getY()+15&&
				   this.getY()-16>et.getY()-15)
				{
					return false;
				}
			}else if(et.getDirect()==2||et.getDirect()==3)
			{
				if(this.getX()-10<et.getX()+15&&
				   this.getX()-10>et.getX()-15&&
				   this.getY()-16<et.getY()+10&&
				   this.getY()-16>et.getY()-10)
				{
					return false;
				}
			}
		}else if(this.getDirect()==1)
		{
			if(et.getDirect()==0||et.getDirect()==1)
			{
				if(this.getX()-10<et.getX()+10&&
				   this.getX()-10>et.getX()-10&&
				   this.getY()+16<et.getY()+15&&
				   this.getY()+16>et.getY()-15)
				{
					return false;
				}
			}else if(et.getDirect()==2||et.getDirect()==3)
			{
				if(this.getX()-10<et.getX()+15&&
				   this.getX()-10>et.getX()-15&&
				   this.getY()+16<et.getY()+10&&
				   this.getY()+16>et.getY()-10)
				{
					return false;
				}
			}
		}else if(this.getDirect()==2)
		{
			if(et.getDirect()==0||et.getDirect()==1)
			{
				if(this.getY()-10<et.getY()+15&&
				   this.getY()-10>et.getY()-15&&
				   this.getX()-16<et.getX()+10&&
				   this.getX()-16>et.getX()-10)
				{
					return false;
				}
			}else if(et.getDirect()==2||et.getDirect()==3)
			{
				if(this.getY()-10<et.getY()+10&&
				   this.getY()-10>et.getY()-10&&
				   this.getX()-16<et.getX()+15&&
				   this.getX()-16>et.getX()-15)
				{
					return false;
				}
			}
		}else if(this.getDirect()==3)
		{
			if(et.getDirect()==0||et.getDirect()==1)
			{
				if(this.getY()-10<et.getY()+15&&
				   this.getY()-10>et.getY()-15&&
				   this.getX()+16<et.getX()+10&&
				   this.getX()+16>et.getX()-10)
				{
					return false;
				}
			}else if(et.getDirect()==2||et.getDirect()==3)
			{
				if(this.getY()-10<et.getY()+10&&
				   this.getY()-10>et.getY()-10&&
				   this.getX()+16<et.getX()+15&&
				   this.getX()+16>et.getX()-15)
				{
					return false;
				}
			}
		}
		return true;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
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
	
	public void moveUp()
	{
		y-=speed;
	}
	public void moveDown()
	{
		y+=speed;
	}
	public void moveLeft()
	{
		x-=speed;
	}
	public void moveRight()
	{
		x+=speed;
	}
	
}
class Hero extends Tank
{

	Vector<Bullet> ss = new Vector<Bullet>();

	Bullet s=null;
	public Hero(int x,int y, int direct,int speed){
		super(x, y,direct,speed);
		
	}
	
	public void shotEnemy()
	{
		
		switch(this.getDirect())
		{
		//UP
		case 0:
			s = new Bullet(this.getX()-2,this.getY()-16,0);
			ss.add(s);
			break;
		//DOWN
		case 1:
			s = new Bullet(this.getX()-2,this.getY()+16,1);
			ss.add(s);
			break;
		//left
		case 2:
			s = new Bullet(this.getX()-16,this.getY()-2,2);
			ss.add(s);
			break;
		//right
		case 3:
			s = new Bullet(this.getX()+16,this.getY()-2,3);
			ss.add(s);
			break;
		}
		//�����ӵ�
		Thread t=new Thread(s);
		t.start();
	}
	
}
class EnemyTank extends Tank implements Runnable
{

	
	int times=0;
	//������������ŵ���tank
	Vector<Bullet> ss = new Vector<Bullet>();
	//��������ӵ����մ����뼺���ӵ�����
	Bullet s=null;
	public EnemyTank(int x,int y, int direct,int speed){
		super(x, y,direct,speed);
		
	}
	
	public void run() {
		while(true)
		{	
			switch(this.getDirect())
			{
			case 0:
				//˵��̹�����������ƶ�
				for(int i=0;i<30;i++)
				{
					if(this.getY()>15)
					{
						this.setY(this.getY()-1);
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 1:
				//˵��̹�����������ƶ�
				
				for(int i=0;i<30;i++)
				{
					if(this.getY()<300-15)
					{
						this.setY(this.getY()+1);
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 2:
				//˵��̹�����������ƶ�
				for(int i=0;i<30;i++)
				{
					if(this.getX()>15)
					{
						this.setX(this.getX()-1);
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3:
				//˵��̹�����������ƶ�
				for(int i=0;i<30;i++)
				{
					if(this.getX()<400-15)
					{
						this.setX(this.getX()+1);
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
			times++;
			//�ж��Ƿ��̹�˼��µ��ӵ�
			if(times%2==0)
			{
				
				if(ss.size()<5)
				{
					
					Bullet s = null;
					switch(this.getDirect())
					{
					case 0:
						
						s = new Bullet(this.getX()-2,this.getY()-16,0);
						this.ss.add(s);
						break;
					//DOWN
					case 1:
						s = new Bullet(this.getX()-2,this.getY()+16,1);
						this.ss.add(s);
						break;
					//left
					case 2:
					    s = new Bullet(this.getX()-16,this.getY()-2,2);
					    this.ss.add(s);
					    break;
					//right
					case 3:
						s = new Bullet(this.getX()+16,this.getY()-2,3);
						this.ss.add(s);
						break;
					}
					//�����ӵ��߳�
					Thread t = new Thread(s);
					t.start();
				}
			}
			
			//��̹���������һ���µķ���
			this.setDirect((int)(Math.random()*4));
			//�жϵ����Ƿ�����
			if(this.isLive==false)
			{
				break;
			}
		}
		
	}
}
class Bullet implements Runnable
{
	
	
	private int x=0;
	private int y=0;
	private int derect;
	private int speed=2;
	boolean isLive=true;
	public Bullet(int x,int y,int direct)
	{
		this.x = x;
		this.y = y;
		
		this.derect = direct;
	}
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(this.getDerect())
			{
			case 0:
				y-=this.getSpeed();
				break;
			case 1:
				y+=this.getSpeed();
				break;
			case 2:
				x-=this.getSpeed();
				break;
			case 3:
				x+=this.getSpeed();
				break;
			}
			//�ӵ���ʱ����
			//�ж��ӵ��Ƿ�������Ե
			if(x<0||x>400||y<0||y>300)
			{
				this.isLive=false;
				break;
			}
		}
		
	}
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
	public int getDerect() {
		return derect;
	}
	public void setDerect(int derect) {
		this.derect = derect;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
class Bomb
{
	private int x;
	private int y;
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//��������
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}
		else
		{
			this.isLive=false;
		}
	}
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
	
}





