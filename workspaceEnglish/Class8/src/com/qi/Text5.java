/*
 * ���ֲ��ֹ�������ʹ��
 * JPanel:���������Ƕ�������
 */
package com.qi;
import java.awt.*;
import javax.swing.*;
public class Text5 extends JFrame{

	JPanel jp1,jp2;
	JButton bt1,bt2,bt3,bt4,bt5,bt6;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text5 t=new Text5();
	}
	public Text5()
	{
		//�������
		//JPanel Ĭ��FlowLayout
		jp1=new JPanel();
		jp2=new JPanel();
		bt1 = new JButton("����");
		bt2 = new JButton("ƻ��");
		bt3 = new JButton("��֦");
		bt4 = new JButton("����");
		bt5 = new JButton("����");
		bt6 = new JButton("�㽶");
		
		//���ò���
		//���JPanel
		jp1.add(bt1);
		jp1.add(bt2);
		jp2.add(bt3);
		jp2.add(bt4);
		jp2.add(bt5);
		//��JPanel����JFrame
		this.add(jp1,BorderLayout.NORTH);
		this.add(bt6,BorderLayout.CENTER);
		this.add(jp2,BorderLayout.SOUTH);
		this.setSize(300,300);
		this.setTitle("FRUIT");
		this.setLocation(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}





