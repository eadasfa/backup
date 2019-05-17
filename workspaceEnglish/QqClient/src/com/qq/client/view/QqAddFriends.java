package com.qq.client.view;

import java.awt.*;
import java.awt.GraphicsConfiguration;

import javax.swing.JDialog;

public class QqAddFriends extends JDialog{

//	public static void main(String[] args) {
//		new QqAddFriends(new Frame(), null, false, "221541");
//	}
	public QqAddFriends(Frame owner, String title,boolean modal,String QqNumber) 
	{
		super(owner, title, modal );
		this.setSize(400,300);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	public void setAddFriendCard()
	{
		
	}
	
}
