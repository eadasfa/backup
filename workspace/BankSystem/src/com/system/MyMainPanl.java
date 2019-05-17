package com.system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

class MyMainPanel extends JPanel implements Runnable
{
	
	int times=0;
	public void paint(Graphics g)
	{
		int x=200;
		int y=130;
		super.paint(g);
		
		//提示信息
		//设置字体
		if(times%2==0){
			g.setColor(Color.BLUE);
			Font myFont = new Font("华文新魏",Font.BOLD ,30);
			g.setFont(myFont);
			g.drawString("HELLO", x, y);
		}
		if(times%3==0){
			g.setColor(Color.RED);
			Font myFont = new Font("华文新魏",Font.BOLD ,30);
			g.setFont(myFont);
			g.drawString("HELLO", x, y);
		}
		if(times%5==0){
			g.setColor(Color.GRAY);
			Font myFont = new Font("华文新魏",Font.BOLD ,30);
			g.setFont(myFont);
			g.drawString("HELLO", x, y);
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