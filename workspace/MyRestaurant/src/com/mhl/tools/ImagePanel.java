package com.mhl.tools;

import java.awt.*;

import javax.swing.*;

public class ImagePanel extends JPanel{

	Image im;
	//���캯��ָ��Panel��С
	public ImagePanel(Image im) {
		//ϣ�����Ĵ�С����Ӧ
		this.im=im;
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h);
	}
	public void paintComponent(Graphics g)
	{
		//����
		super.paintComponents(g);
		g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}

}
