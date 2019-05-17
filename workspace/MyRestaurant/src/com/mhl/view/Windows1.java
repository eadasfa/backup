/*
 * ����ϵͳ����Ա���������ܽ���
 * ��ɽ��棬���ϵ��£�������
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
	//����
	Image titelIcon;
	//״̬������
	Image timeBG;
	JMenuBar jmb;
	//һ���˵�
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	//�����˵�
	JMenuItem jmm1,jmm2,jmm3,jmm4,jmm5;
	//ͼ��
	ImageIcon jmm1_1,jmm1_2,jmm1_3,jmm1_4,jmm1_5;
	//������
	JToolBar jtb;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//������Ҫ��5�����
	JPanel jp1,jp2,jp3,jp4,jp5;
	//��ʾ��ǰʱ��
	JLabel timeNow;
	//javax.swing.timer ���Զ�ʱ����Actionʱ�䣬���ǿ������������һЩ��
	javax.swing.Timer t;
	ImagePanel jp1_imgPanel;
	JLabel jp1_lab1,jp1_lab2,jp1_lab3,jp1_lab4,jp1_lab5,jp1_lab6,jp1_lab7,jp1_lab8;
	//��jp2��嶨��JLabel
	JLabel jp2_lab1,jp2_lab2;
	//��ִ���
	JSplitPane jsp1;
	CardLayout cardp3 = new CardLayout();
	CardLayout	cardp2 = new CardLayout();
	//�����  
	Cursor cursor =null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Windows1 wd = new Windows1();
	}
	public Windows1() {
		
		//�����  
		cursor = new Cursor(Cursor.HAND_CURSOR);
		ct = this.getContentPane();
		//�˵���
		this.setJMenubar();
		//������
		this.setJToolbar();
		//�м�
		this.setCenter();
		//״̬��
		this.setStatebar();
		//���ô�С
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		//���ô���ͼƬ
		
		this.setIconImage(titelIcon);
		this.setTitle("����¥��������ϵͳ");
		this.setSize(w,h-25);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.timeNow.setText("��ǰʱ��:"+Calendar.getInstance().getTime().toLocaleString()+" ");
		if(e.getSource()==jb10)
		{
			System.exit(0);
		}
		
	}
	//�˵���
	public void setJMenubar()
	{
		try {
			titelIcon =ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����ͼ��
		jmm1_1 = new ImageIcon("image/login_b.jpg");
		jmm1_2 = new ImageIcon("image/hr_b.jpg");
		jmm1_3 = new ImageIcon("image/pc_b.jpg");
		jmm1_4 = new ImageIcon("image/p1_wnll.jpg");
		jmm1_5 = new ImageIcon("image/info_b.jpg");
		//����һ���˵�
		jm1 = new JMenu("ϵͳ����");
		jm1.setFont(Mytools.f1);
			//����jm1��һ���˵�
		
			jmm1 = new JMenuItem("�л��û�",jmm1_1);
			jmm2 = new JMenuItem("�л����տ����",jmm1_2);
			jmm3 = new JMenuItem("��½����",jmm1_3);
			jmm4 = new JMenuItem("������",jmm1_4);
			jmm5 = new JMenuItem("�˳�",jmm1_5);
			jm1.add(jmm1);
			jm1.add(jmm2);
			jm1.add(jmm3);
			jm1.add(jmm4);
			jm1.add(jmm5);
			
		jm2 = new JMenu("���¹���");
		jm2.setFont(Mytools.f1);
			//����jm2��һ���˵�
		jm3 = new JMenu("�˵�����");
		jm3.setFont(Mytools.f1);
			//����jm3��һ���˵�
		jm4 = new JMenu("����ͳ��");
		jm4.setFont(Mytools.f1);
			//����jm4��һ���˵�
		jm5 = new JMenu("�ɱ����ⷿ");
		jm5.setFont(Mytools.f1);
			//����jm5��һ���˵�
		jm6 = new JMenu("����");
		jm6.setFont(Mytools.f1);
		//��һ���˵����뵽JMenuBar
		
		jmb = new JMenuBar();
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm6);
		//��JMenuBar��ӵ�JFrame
		this.setJMenuBar(jmb);
	}
	//������
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
	//״̬��
	public void setStatebar()
	{
		//����JP5���
		jp5 = new JPanel();
		jp5.setLayout(new BorderLayout());
		//����Timer
		t = new Timer(1000,this);//ÿ��1�봥��ActionEvent
		//������ʱ��
		t.start();
		//��ʾ��ǰʱ��
		timeNow = new JLabel("��ǰʱ��:"+Calendar.getInstance().getTime().toLocaleString()+" ");
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
		//jp1������
		//8����ǩ
		this.setJp1();
		//���
		//p4������
			jp4 = new JPanel(new BorderLayout());
			//jp2������
			jp2 = new JPanel(cardp2);
				jp2_lab1  = new JLabel(new ImageIcon("image/p2_left.jpg"));
				jp2_lab1.addMouseListener(this);
				jp2_lab1.setCursor(cursor);
				jp2_lab2  = new JLabel(new ImageIcon("image/p2_right.jpg"));
				jp2_lab2.addMouseListener(this);
				jp2_lab2.setCursor(cursor);
				//��lab1 lab2����JP2
				jp2.add(jp2_lab1,"0");
				jp2.add(jp2_lab2,"1");
			/**********************
			 * ��Jp3������  
			 * ���߸���ť����Ӧ�������л�
			 **********************/
			this.setJp3();
				
			//��jp2jp3����jp4
			jp4.add(jp2,"West");
			jp4.add(jp3,"Center");
		//��ִ���
		jsp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp1,jp4);
		//ָ����ߵ����ռ���
		jsp1.setDividerLocation(120);
		//�ѱ߽�����Ϊ0
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
		 * ��1��lab
		 ****************************/
		this.jp1_imgPanel = new ImagePanel(p1_bg);
		this.jp1_imgPanel.setLayout(new GridLayout(8,1));
		//��һ��lab
		jp1_lab1 = new JLabel(new ImageIcon("image/black.png"));
		jp1_imgPanel .add(jp1_lab1);
		
		/***************************
		 * ��2��lab
		 **************************/
		jp1_lab2 = new JLabel("�� �� �� ��",new ImageIcon("image/p1_wnll.jpg"),0);
		jp1_lab2.setFont(Mytools.f3);
		//ָ��ʱ������
		jp1_lab2.setCursor(cursor);
		//�ø�label������
		jp1_lab2.setEnabled(false);
		//ע�����
		jp1_lab2.addMouseListener(this);
		//����
		jp1_imgPanel.add(jp1_lab2);
		
		
		/**************************
		 * ��3��lab
		 **************************/
		jp1_lab3 = new JLabel("�� ¼ �� ��",new ImageIcon("image/pc_b.jpg"),0);
		jp1_lab3.setFont(Mytools.f3);
		//ָ��ʱ������
		jp1_lab3.setCursor(cursor);
		//�ø�label������
		jp1_lab3.setEnabled(false);
		//ע�����
		jp1_lab3.addMouseListener(this);
		//����
		jp1_imgPanel.add(jp1_lab3);
		
		/**************************
		 * ��4��lab
		 *************************/
		jp1_lab4 = new JLabel("�� �� �� ��",new ImageIcon("image/p1_cpjg.jpg"),0);
		jp1_lab4.setFont(Mytools.f3);
		//ָ��ʱ������
		jp1_lab4.setCursor(cursor);
		//�ø�label������
		jp1_lab4.setEnabled(false);
		//ע�����
		jp1_lab4.addMouseListener(this);
		//����
		jp1_imgPanel.add(jp1_lab4);
		
		/***************************
		 * ��5��lab
		 *************************/
		jp1_lab5 = new JLabel("�� �� ͳ ��",new ImageIcon("image/p1_bbtj.jpg"),0);
		jp1_lab5.setFont(Mytools.f3);
		//ָ��ʱ������
		jp1_lab5.setCursor(cursor);
		//�ø�label������
		jp1_lab5.setEnabled(false);
		//ע�����
		jp1_lab5.addMouseListener(this);
		//����
		jp1_imgPanel.add(jp1_lab5);
		
		/*****************************
		 * ��6��lab
		 ****************************/
		jp1_lab6 = new JLabel("�ɱ����ⷿ",new ImageIcon("image/p1_cb.jpg"),0);
		jp1_lab6.setFont(Mytools.f3);
		//ָ��ʱ������
		jp1_lab6.setCursor(cursor);
		//�ø�label������
		jp1_lab6.setEnabled(false);
		//ע�����
		jp1_lab6.addMouseListener(this);
		//����
		jp1_imgPanel.add(jp1_lab6);
		
		/*****************************
		 * ��7��lab
		 ****************************/
		jp1_lab7 = new JLabel("ϵ ͳ �� ��",new ImageIcon("image/p1_xtsz.jpg"),0);
		jp1_lab7.setFont(Mytools.f3);
		//ָ��ʱ������
		jp1_lab7.setCursor(cursor);
		//�ø�label������
		jp1_lab7.setEnabled(false);
		//ע�����
		jp1_lab7.addMouseListener(this);
		//����
		jp1_imgPanel.add(jp1_lab7);
		
		/*******************************
		 * ��8��lab
		 ******************************/
		jp1_lab8 = new JLabel("�� �� �� ��",new ImageIcon("image/p1_dhbz.jpg"),0);
		jp1_lab8.setFont(Mytools.f3);
		//ָ��ʱ������
		jp1_lab8.setCursor(cursor);
		//�ø�label������
		jp1_lab8.setEnabled(false);
		//ע�����
		jp1_lab8.addMouseListener(this);
		//����
		jp1_imgPanel.add(jp1_lab8);
		
		
		jp1.setLayout(new BorderLayout());
		jp1.add(this.jp1_imgPanel,"Center");
	}
	//����л�
	public void setJp3()
	{
		
		this.cardp3 = new CardLayout();
		jp3 = new JPanel(cardp3);
		//�ȸ�jp3����һ�����������
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
		 * ���߸���ť����Ӧ�������л�
		 **********************/
		
		//����EmpInfo (JPanel) 
		//1.���¹���
		EmpInfo info = new EmpInfo();
		jp3.add(info,"1");
		JLabel rs0  = new JLabel(new ImageIcon("image/dlgl.jpg"));
		jp3.add(rs0,"2");
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//�ж��û�����ĸ�LAbel
		//���¹���
		if(e.getSource()==this.jp1_lab2)
		{
			this.cardp3.show(jp3, "1");
		}else if(e.getSource()==this.jp1_lab3)
		{
			this.cardp3.show(jp3, "2");
		}else if(e.getSource()==this.jp2_lab1)
		{
			//����ʾ���ֲ����Ľ���
			jsp1.setDividerLocation(0);
			//����ͷ
			this.cardp2.show(jp2, "1");
		}else if(e.getSource()==this.jp2_lab2)
		{
			//����ʾ���ֲ����Ľ���
			jsp1.setDividerLocation(120);
			//����ͷ
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
		//����û�ѡ����ĳ��������JLabel �����
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
		//����û�ѡ����ĳ��������JLabel �����
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













