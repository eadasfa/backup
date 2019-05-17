package com.qi.fiveinrow;

import java.awt.Image;
import javax.imageio.ImageIO;

/**
 * Æå×Ó
 * @author Administrator
 *
 */
public class Chess {

	Image image= null;
	int x=-100;
	
	int y=-100;
	String color=Tools.BLACK;
	public Chess(int x,int y,String color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
		try {
			if(this.color.equals(Tools.BLACK))
			{
				image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/black.jpg"));
			}
				else if(this.color.equals(Tools.WHITE))
			{
				image = ImageIO.read(ClassLoader.getSystemResourceAsStream("images/white.jpg"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public int getX0() {
		return (int)(x*Tools.LENGTH+30);
	}
	
	public int getY0() {
		return (int)(y*Tools.LENGTH+30);
	}
}
