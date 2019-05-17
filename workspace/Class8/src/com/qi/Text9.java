package com.qi;
import java.awt.*;
import javax.swing.*;

public class Text9 extends JFrame{

	JSplitPane jsp;
	JList jlist;
	JLabel jl1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text9 t=new Text9();
	}
	public Text9()
	{
		
		String[]word={"boy","girl","bird"};
		jlist = new JList(word);
		jl1 = new JLabel(new ImageIcon("image/abc.jpg"));
		//可拆分窗格
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jlist,jl1);
		
		jsp.setOneTouchExpandable(true);
		//设置布局
		
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.jsp.setDividerLocation(0.2);
	}

}
