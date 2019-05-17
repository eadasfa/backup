package com.snakegame3;

import java.util.Vector;

public class Snake
{
	Vector<Block> bk = null;
	private int headX=Tools.x0+4*20;
	private int headY=Tools.y0;
	
	
	private int speed = 0;
	private String direct="R";//方向
	private int size = 0;//snake的长度
	
	public Snake()
	{
		int x = this.headX;
		int y = this.headY;
		bk = new Vector<Block>();
		
		for(int i=4;i>=0;i--)
		{
			this.size++;
			Block bl = new Block(x, y);
			bk.add(bl);
			x-=20;
		}
	}

	public void move()
	{
		if(this.speed!=0)
		{
			for(int i=this.size-1;i>=0;i--)
			{
				Block block = this.bk.get(i);
				//移动头部
				if(i==0)
				{
					if(this.direct.equals("R"))
					{
						this.headX+=this.speed;
					}else if(this.direct.equals("L"))
					{
						this.headX-=this.speed;
					}else if(this.direct.equals("U"))
					{
						this.headY-=this.speed;
					}else if(this.direct.equals("D"))
					{
						this.headY+=this.speed;
					}
					block.setX(this.headX);
					block.setY(this.headY);
				}
				else
				{
					//将snake的后一个块的位置变成前一个块
					Block block2 = this.bk.get(i-1);
					block.setX(block2.getX());
					block.setY(block2.getY());
				}
			}
			
		}
	}
	public boolean eatFood(Block bl)
	{
		boolean flag = false;
		
		if(this.headX==bl.getX()&&
					this.headY==bl.getY())
		{
			Block bl2=this.bk.get(this.size-1);
			bl.setX(bl2.getX());
			bl.setY(bl2.getY());
			this.bk.add(bl);
			this.size++;
			flag = true;
		}
		return flag;
	}
	public int getHeadX() {
		return headX;
	}

	public void setHeadX(int headX) {
		this.headX = headX;
	}

	public int getHeadY() {
		return headY;
	}

	public void setHeadY(int headY) {
		this.headY = headY;
	}
	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}
}












