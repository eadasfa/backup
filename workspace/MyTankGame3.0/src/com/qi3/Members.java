
package com.qi3;
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
	private int x0=400;
	private int y0=300;
	Vector<Bullet> ss = new Vector<Bullet>();
	Bullet s=null;
	public Hero(int x,int y, int direct,int speed,int x0,int y0){
		super(x, y,direct,speed);
		this.x0=x0;
		this.y0=y0;
	}
	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setYo(int y0) {
		this.y0 = y0;
	}
	public void shotEnemy()
	{
		
		switch(this.getDirect())
		{
		//UP
		case 0:
			s = new Bullet(this.getX()-2,this.getY()-16,0,this.getY0(),this.getY0());
			ss.add(s);
			break;
		//DOWN
		case 1:
			s = new Bullet(this.getX()-2,this.getY()+16,1,this.getY0(),this.getY0());
			ss.add(s);
			break;
		//left
		case 2:
			s = new Bullet(this.getX()-16,this.getY()-2,2,this.getY0(),this.getY0());
			ss.add(s);
			break;
		//right
		case 3:
			s = new Bullet(this.getX()+16,this.getY()-2,3,this.getY0(),this.getY0());
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
	int x0=400;
	int y0=300;
	boolean isLive=true;
	int times=0;
	//������������ŵ���tank
	Vector<Bullet> ss = new Vector<Bullet>();
	//��������ӵ����մ����뼺���ӵ�����
	Bullet s=null;
	public EnemyTank(int x,int y, int direct,int speed,int x0,int y0){
		super(x, y,direct,speed);
		this.x0=x0;
		this.y0=y0;
	}
	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setYo(int y0) {
		this.y0 = y0;
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
					if(this.getY()<this.getY0()-15)
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
					if(this.getX()<this.getX0()-15)
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
						
						s = new Bullet(this.getX()-2,this.getY()-16,0,this.getY0(),this.getY0());
						this.ss.add(s);
						break;
					//DOWN
					case 1:
						s = new Bullet(this.getX()-2,this.getY()+16,1,this.getY0(),this.getY0());
						this.ss.add(s);
						break;
					//left
					case 2:
					    s = new Bullet(this.getX()-16,this.getY()-2,2,this.getY0(),this.getY0());
					    this.ss.add(s);
					    break;
					//right
					case 3:
						s = new Bullet(this.getX()+16,this.getY()-2,3,this.getY0(),this.getY0());
						this.ss.add(s);
						break;
					}
					//�����ӵ��س�
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
	//���Χ
	private int x0=400;
	private int y0=300;
	
	private int x=0;
	private int y=0;
	private int derect;
	private int speed=2;
	boolean isLive=true;
	public Bullet(int x,int y,int direct,int x0,int y0)
	{
		this.x = x;
		this.y = y;
		this.x0 = x0;
		this.y0 = y0;
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
			if(x<0||x>this.getX0()||y<0||y>this.getY0())
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
	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setYo(int y0) {
		this.y0 = y0;
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





