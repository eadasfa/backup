/*
 * 这是系统管理员，经理，主管界面
 * 完成界面，从上到下，从左到右
 */
package com.mhl.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.mhl.tools.*;
public class Windows1 extends JFrame implements ActionListener,MouseListener{
	
	Container ct;
	//标题
	Image titelIcon;
	//状态栏背景
	Image timeBG;
	JMenuBar jmb;
	//一级菜单
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	//二级菜单
	JMenuItem jmm1,jmm2,jmm3,jmm4,jmm5;
	//图标
	ImageIcon jmm1_1,jmm1_2,jmm1_3,jmm1_4,jmm1_5;
	//工具栏
	JToolBar jtb;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//定义需要的5个面板
	JPanel jp1,jp2,jp3,jp4,jp5;
	//显示当前时间
	JLabel timeNow;
	//javax.swing.timer 可以定时触发Action时间，我们可以利用它完成一些事
	javax.swing.Timer t;
	ImagePanel jp1_imgPanel;
	JLabel jp1_lab1,jp1_lab2,jp1_lab3,jp1_lab4,jp1_lab5,jp1_lab6,jp1_lab7,jp1_lab8;
	//给jp2面板定义JLabel
	JLabel jp2_lab1,jp2_lab2;
	//拆分窗口
	JSplitPane jsp1;
	CardLayout cardp3 = new CardLayout();
	CardLayout	cardp2 = new CardLayout();
	//鼠标光标  
	Cursor cursor =null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Windows1 wd = new Windows1();
	}
	public Windows1() {
		
		//鼠标光标  
		cursor = new Cursor(Cursor.HAND_CURSOR);
		ct = this.getContentPane();
		//菜单栏
		this.setJMenubar();
		//工具栏
		this.setJToolbar();
		//中间
		this.setCenter();
		//状态栏
		this.setStatebar();
		//设置大小
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		//设置窗口图片
		
		this.setIconImage(titelIcon);
		this.setTitle("满汉楼餐饮管理系统");
		this.setSize(w,h-25);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("当前时间:"+Calendar.getInstance().getTime().toLocaleString()+" ");
		if(e.getSource()==jb10)
		{
			System.exit(0);
		}
		
	}
	//菜单栏
	public void setJMenubar()
	{
		try {
			titelIcon =ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//创建图标
		jmm1_1 = new ImageIcon("image/login_b.jpg");
		jmm1_2 = new ImageIcon("image/hr_b.jpg");
		jmm1_3 = new ImageIcon("image/pc_b.jpg");
		jmm1_4 = new ImageIcon("image/p1_wnll.jpg");
		jmm1_5 = new ImageIcon("image/info_b.jpg");
		//创建一级菜单
		jm1 = new JMenu("系统管理");
		jm1.setFont(Mytools.f1);
			//创建jm1的一级菜单
		
			jmm1 = new JMenuItem("切换用户",jmm1_1);
			jmm2 = new JMenuItem("切换到收款界面",jmm1_2);
			jmm3 = new JMenuItem("登陆管理",jmm1_3);
			jmm4 = new JMenuItem("万年历",jmm1_4);
			jmm5 = new JMenuItem("退出",jmm1_5);
			jm1.add(jmm1);
			jm1.add(jmm2);
			jm1.add(jmm3);
			jm1.add(jmm4);
			jm1.add(jmm5);
			
		jm2 = new JMenu("人事管理");
		jm2.setFont(Mytools.f1);
			//创建jm2的一级菜单
		jm3 = new JMenu("菜单管理");
		jm3.setFont(Mytools.f1);
			//创建jm3的一级菜单
		jm4 = new JMenu("报表统计");
		jm4.setFont(Mytools.f1);
			//创建jm4的一级菜单
		jm5 = new JMenu("成本及库房");
		jm5.setFont(Mytools.f1);
			//创建jm5的一级菜单
		jm6 = new JMenu("帮助");
		jm6.setFont(Mytools.f1);
		//把一级菜单加入到JMenuBar
		
		jmb = new JMenuBar();
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm6);
		//把JMenuBar添加到JFrame
		this.setJMenuBar(jmb);
	}
	//工具栏
	public void setJToolbar()
	{
		jtb = new JToolBar();
		jb1 = new JButton(new ImageIcon("image/tool_2.png"));
		jb2 = new JButton(new ImageIcon("image/tool_1.jpg"));
		jb3 = new JButton(new ImageIcon("image/tool_3.jpg"));
		jb4 = new JButton(new ImageIcon("image/earth.jpg"));
		jb5 = new JButton(new ImageIcon("image/uDisk.jpg"));
		jb6 = new JButton(new ImageIcon("image/fish.jpg"));
		jb7 = new JButton(new ImageIcon("image/cuke.jpg"));
		jb8 = new JButton(new ImageIcon("image/butterfly.jpg"));
		jb9 = new JButton(new ImageIcon("image/robot.jpg"));
		jb10 = new JButton(new ImageIcon("image/tool_10.jpg"));
		jb10.addActionListener(this);
		jtb.add(jb1);
		jtb.add(jb2);
		jtb.add(jb3);
		jtb.add(jb4);
		jtb.add(jb5);
		jtb.add(jb6);
		jtb.add(jb7);
		jtb.add(jb8);
		jtb.add(jb9);
		jtb.add(jb10);
		jtb.setFloatable(false);
		ct.add(jtb,"North");
		
	}
	//状态栏
	public void setStatebar()
	{
		//处理JP5面板
		jp5 = new JPanel();
		jp5.setLayout(new BorderLayout());
		//创建Timer
		t = new Timer(1000,this);//每个1秒触发ActionEvent
		//启动定时器
		t.start();
		//显示当前时间
		timeNow = new JLabel("当前时间:"+Calendar.getInstance().getTime().toLocaleString()+" ");
		timeNow.setFont(Mytools.f2);
		try {
			timeBG = ImageIO.read(new File("image/time_bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImagePanel ip1 = new ImagePanel(timeBG);
		ip1.setLayout(new BorderLayout());
		ip1.add(timeNow,"East");
		jp5.add(ip1);
		ct.add(jp5,"South");
	}
	public void setCenter()
	{
		//jp1的设置
		//8个标签
		this.setJp1();
		//面板
		//p4的设置
			jp4 = new JPanel(new BorderLayout());
			//jp2的设置
			jp2 = new JPanel(cardp2);
				jp2_lab1  = new JLabel(new ImageIcon("image/p2_left.jpg"));
				jp2_lab1.addMouseListener(this);
				jp2_lab1.setCursor(cursor);
				jp2_lab2  = new JLabel(new ImageIcon("image/p2_right.jpg"));
				jp2_lab2.addMouseListener(this);
				jp2_lab2.setCursor(cursor);
				//把lab1 lab2加入JP2
				jp2.add(jp2_lab1,"0");
				jp2.add(jp2_lab2,"1");
			/**********************
			 * 对Jp3的设置  
			 * 对七个按钮所对应的面板的切换
			 **********************/
			this.setJp3();
				
			//把jp2jp3加入jp4
			jp4.add(jp2,"West");
			jp4.add(jp3,"Center");
		//拆分窗口
		jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp1,jp4);
		//指定左边的面板占多大
		jsp1.setDividerLocation(120);
		//把边界线设为0
		jsp1.setDividerSize(0);
		
		ct.add(jsp1,"Center");
		
	}
	public void setJp1()
	{
		jp1 =new JPanel();
		Image p1_bg=null;
		try {
			p1_bg = ImageIO.read(new File("image/jp1_bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/******************************
		 * 第1个lab
		 ****************************/
		this.jp1_imgPanel = new ImagePanel(p1_bg);
		this.jp1_imgPanel.setLayout(new GridLayout(8,1));
		//第一个lab
		jp1_lab1 = new JLabel(new ImageIcon("image/black.png"));
		jp1_imgPanel .add(jp1_lab1);
		
		/***************************
		 * 第2个lab
		 **************************/
		jp1_lab2 = new JLabel("人 事 管 理",new ImageIcon("image/p1_wnll.jpg"),0);
		jp1_lab2.setFont(Mytools.f3);
		//指向时鼠标变形
		jp1_lab2.setCursor(cursor);
		//让该label不可用
		jp1_lab2.setEnabled(false);
		//注册监听
		jp1_lab2.addMouseListener(this);
		//加入
		jp1_imgPanel.add(jp1_lab2);
		
		
		/**************************
		 * 第3个lab
		 **************************/
		jp1_lab3 = new JLabel("登 录 管 理",new ImageIcon("image/pc_b.jpg"),0);
		jp1_lab3.setFont(Mytools.f3);
		//指向时鼠标变形
		jp1_lab3.setCursor(cursor);
		//让该label不可用
		jp1_lab3.setEnabled(false);
		//注册监听
		jp1_lab3.addMouseListener(this);
		//加入
		jp1_imgPanel.add(jp1_lab3);
		
		/**************************
		 * 第4个lab
		 *************************/
		jp1_lab4 = new JLabel("菜 谱 价 格",new ImageIcon("image/p1_cpjg.jpg"),0);
		jp1_lab4.setFont(Mytools.f3);
		//指向时鼠标变形
		jp1_lab4.setCursor(cursor);
		//让该label不可用
		jp1_lab4.setEnabled(false);
		//注册监听
		jp1_lab4.addMouseListener(this);
		//加入
		jp1_imgPanel.add(jp1_lab4);
		
		/***************************
		 * 第5个lab
		 *************************/
		jp1_lab5 = new JLabel("报 表 统 计",new ImageIcon("image/p1_bbtj.jpg"),0);
		jp1_lab5.setFont(Mytools.f3);
		//指向时鼠标变形
		jp1_lab5.setCursor(cursor);
		//让该label不可用
		jp1_lab5.setEnabled(false);
		//注册监听
		jp1_lab5.addMouseListener(this);
		//加入
		jp1_imgPanel.add(jp1_lab5);
		
		/*****************************
		 * 第6个lab
		 ****************************/
		jp1_lab6 = new JLabel("成本及库房",new ImageIcon("image/p1_cb.jpg"),0);
		jp1_lab6.setFont(Mytools.f3);
		//指向时鼠标变形
		jp1_lab6.setCursor(cursor);
		//让该label不可用
		jp1_lab6.setEnabled(false);
		//注册监听
		jp1_lab6.addMouseListener(this);
		//加入
		jp1_imgPanel.add(jp1_lab6);
		
		/*****************************
		 * 第7个lab
		 ****************************/
		jp1_lab7 = new JLabel("系 统 设 置",new ImageIcon("image/p1_xtsz.jpg"),0);
		jp1_lab7.setFont(Mytools.f3);
		//指向时鼠标变形
		jp1_lab7.setCursor(cursor);
		//让该label不可用
		jp1_lab7.setEnabled(false);
		//注册监听
		jp1_lab7.addMouseListener(this);
		//加入
		jp1_imgPanel.add(jp1_lab7);
		
		/*******************************
		 * 第8个lab
		 ******************************/
		jp1_lab8 = new JLabel("动 画 帮 助",new ImageIcon("image/p1_dhbz.jpg"),0);
		jp1_lab8.setFont(Mytools.f3);
		//指向时鼠标变形
		jp1_lab8.setCursor(cursor);
		//让该label不可用
		jp1_lab8.setEnabled(false);
		//注册监听
		jp1_lab8.addMouseListener(this);
		//加入
		jp1_imgPanel.add(jp1_lab8);
		
		
		jp1.setLayout(new BorderLayout());
		jp1.add(this.jp1_imgPanel,"Center");
	}
	//面板切换
	public void setJp3()
	{
		
		this.cardp3 = new CardLayout();
		jp3 = new JPanel(cardp3);
		//先给jp3加入一个主界面面板
		Image zhu_image=null;
		try {
			zhu_image = ImageIO.read(new File("image/00.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImagePanel ip = new ImagePanel(zhu_image);
		jp3.add(ip,"0");
		
		/**********************
		 * 对七个按钮所对应的面板的切换
		 **********************/
		
		//创建EmpInfo (JPanel) 
		//1.人事管理
		EmpInfo info = new EmpInfo();
		jp3.add(info,"1");
		JLabel rs0  = new JLabel(new ImageIcon("image/dlgl.jpg"));
		jp3.add(rs0,"2");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//判断用户点击哪个LAbel
		//人事管理
		if(e.getSource()==this.jp1_lab2)
		{
			this.cardp3.show(jp3, "1");
		}else if(e.getSource()==this.jp1_lab3)
		{
			this.cardp3.show(jp3, "2");
		}else if(e.getSource()==this.jp2_lab1)
		{
			//把显示各种操作的界面
			jsp1.setDividerLocation(0);
			//换箭头
			this.cardp2.show(jp2, "1");
		}else if(e.getSource()==this.jp2_lab2)
		{
			//把显示各种操作的界面
			jsp1.setDividerLocation(120);
			//换箭头
			this.cardp2.show(jp2, "0");
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//如果用户选中了某个操作的JLabel 则高亮
		if(e.getSource()==this.jp1_lab2)
		{
			this.jp1_lab2.setEnabled(true);
		}else if(e.getSource()==this.jp1_lab3)
		{
			this.jp1_lab3.setEnabled(true);
		}else if(e.getSource()==this.jp1_lab4)
		{
			this.jp1_lab4.setEnabled(true);
		}else if(e.getSource()==this.jp1_lab5)
		{
			this.jp1_lab5.setEnabled(true);
		}else if(e.getSource()==this.jp1_lab6)
		{
			this.jp1_lab6.setEnabled(true);
		}else if(e.getSource()==this.jp1_lab7)
		{
			this.jp1_lab7.setEnabled(true);
		}else if(e.getSource()==this.jp1_lab8)
		{
			this.jp1_lab8.setEnabled(true);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//如果用户选中了某个操作的JLabel 则禁用
		if(e.getSource()==this.jp1_lab2)
		{
			this.jp1_lab2.setEnabled(false);
		}else if(e.getSource()==this.jp1_lab3)
		{
			this.jp1_lab3.setEnabled(false);
		}else if(e.getSource()==this.jp1_lab4)
		{
			this.jp1_lab4.setEnabled(false);
		}else if(e.getSource()==this.jp1_lab5)
		{
			this.jp1_lab5.setEnabled(false);
		}else if(e.getSource()==this.jp1_lab6)
		{
			this.jp1_lab6.setEnabled(false);
		}else if(e.getSource()==this.jp1_lab7)
		{
			this.jp1_lab7.setEnabled(false);
		}else if(e.getSource()==this.jp1_lab8)
		{
			this.jp1_lab8.setEnabled(false);
		}
	}
}













