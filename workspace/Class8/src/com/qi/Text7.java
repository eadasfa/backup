/*
 *  复选框 和 单选框
 */
package com.qi;
import java.awt.*;
import javax.swing.*;
public class Text7 extends JFrame{

	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2;
	JButton jb1,jb2,jb3;
	JCheckBox jcb1,jcb2,jcb3;
	JRadioButton jrb1,jrb2;
	ButtonGroup bg;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text7 t7=new Text7();
	}
	public Text7()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jl1 = new JLabel("你喜欢的运动");
		jl2 = new JLabel("你的性别") ;
		jb1=new JButton("注册用户");
		jb2=new JButton("取消注册");
		
		jcb1=new JCheckBox("足球");
		jcb2=new JCheckBox("篮球");
		jcb3=new JCheckBox("乒乓球");
		
		jrb1=new JRadioButton("男");
		jrb2=new JRadioButton("女");
		//  一定把jrb1和jrb2放到BUTTONGROUP里
		
		bg=new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		//设置布局管理
		this.setLayout(new GridLayout(3,1));
		
		//添加组件
		jp1.add(jl1);
		jp1.add(jcb1);
		jp1.add(jcb2);
		jp1.add(jcb3);
		
		jp2.add(jl2);
		jp2.add(jrb1);
		jp2.add(jrb2);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setSize(300,150);
		this.setTitle("复选和单选");
		this.setLocation(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
