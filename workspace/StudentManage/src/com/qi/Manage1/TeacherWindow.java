package com.qi.Manage1;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TeacherWindow extends JFrame implements ActionListener {

	
	public TeacherWindow() throws HeadlessException {

		this.setSize(1000,600);
		this.setLocation(100,90);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
