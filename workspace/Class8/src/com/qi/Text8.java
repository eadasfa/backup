/*
 * 多种布局管理器的使用
 * JPanel:面板组件，非顶层容器
 */
package com.qi;
import java.awt.*;
import javax.swing.*;
public class Text8 extends JFrame{

	JPanel jp1,jp2;
	JLabel jl1,jl2;
	JComboBox jcb1;
	JList jlist;
	JScrollPane jsp;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text8 t8=new Text8();
	}
	public Text8()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		
		jl1 = new JLabel("你的籍贯");
		jl2 = new JLabel("旅游地点");
		
		String[]jg={"北京","上海","天津","火星"};
		jcb1=new JComboBox(jg);
		String[]dd={"九寨沟","天安门","故宫","长城"};
		jlist=new JList(dd);
		jsp=new JScrollPane(jlist);
		//设置你希望显示多少个选项
		jlist.setVisibleRowCount(2);
		
		//设置布局
		this.setLayout(new GridLayout(3,1));
		jp1.add(jl1);
		jp1.add(jcb1);
		jp2.add(jl2);
		jp2.add(jsp);
		this.add(jp1);
		this.add(jp2);
		this.setSize(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}







