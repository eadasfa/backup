package com.mhl.tools;

import java.awt.*;

import javax.swing.*;

public class ImagePanel extends JPanel{

	Image im;
	//构造函数指定Panel大小
	public ImagePanel(Image im) {
		//希望它的大小自适应
		this.im=im;
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h);
	}
	public void paintComponent(Graphics g)
	{
		//清屏
		super.paintComponents(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}

}
