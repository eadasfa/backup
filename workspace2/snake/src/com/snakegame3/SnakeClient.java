package com.snakegame3;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;




public class SnakeClient extends JFrame{ 
	
	private int HEIGHT=600;
	private int WIDTH =800;
	GamePanel gp = null;  //游戏面板
	
	public static void main(String[] args) {
		SnakeClient sc = new SnakeClient();
	}
	public SnakeClient()
	{
		
		try {
			Image image = ImageIO.read(new File("title.png"));
			this.setIconImage(image);
		} catch (Exception e) {
			// TODO: handle exception
		}
		gp = new GamePanel(this);
		
		Thread t = new Thread(gp);
		t.start();
		
		
		this.addKeyListener(gp);
		this.setLayout(new BorderLayout());//设置布局
		this.add(gp,"Center");
		
		
		this.setSize(WIDTH,HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setFocusable(true);
	}
	
}

