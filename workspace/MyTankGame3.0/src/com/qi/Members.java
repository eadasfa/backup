
package com.qi;
import java.awt.*;

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
class Tank
{
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
			break;
		//DOWN
		case 1:
			s = new Bullet(this.getX()-2,this.getY()+16,1);
			break;
		//left
		case 2:
			s = new Bullet(this.getX()-16,this.getY()-2,2);
			break;
		//right
		case 3:
			s = new Bullet(this.getX()+16,this.getY()-2,3);
			break;
		}
		//启动子弹
		Thread t=new Thread(s);
		t.start();
	}
	
}
class EnemyTank extends Tank
{
	public EnemyTank(int x,int y, int direct,int speed){
		super(x, y,direct,speed);
	}
}
class Bullet implements Runnable
{
	private int x=0;
	private int y=0;
	private int derect;
	private int speed=1;
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
			//子弹何时死亡
			System.out.println("x="+this.getX()+"   y="+this.getY());
			//判断子弹是否碰到边缘
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






