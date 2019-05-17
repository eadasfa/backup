package com.snakegame3;

public class Block{
	/*块的初始位置
	 *x，y代表块的左上角
	 */
	private int x=Tools.x0;
	private int y=Tools.y0;
	private int width = 20;
	
	
	public Block(int x0,int y0)
	{
		this.x=x0;
		this.y=y0;
		
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
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
}
