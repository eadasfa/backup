/*
 * ��ʽ����
 * 1.�̳�JFrame
 * 2.�������
 * 3.�������
 * 4.������
 * 5.�Դ�������
 * 6.��ʾ����
 */
package com.qi;
import java.awt.*;
import javax.swing.*;

public class Text3 extends JFrame{

	JButton jb1,jb2,jb3,jb4,jb5,jb6;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text3 t1 = new Text3();
		
	}
	//���캯��
	public Text3()
	{
		// JFrame��һ�����������������������swing������ࣩ
		//JFrame jf = new JFrame();
		//����һ����ť
		JButton jb1=new JButton("1");
		JButton jb2=new JButton("2");
		JButton jb3=new JButton("3");
		JButton jb4=new JButton("4");
		JButton jb5=new JButton("5");
		JButton jb6=new JButton("6");
		//���JButton���
		this.add(jb1);
		this.add(jb2);
		this.add(jb3);
		this.add(jb4);
		this.add(jb5);
		this.add(jb6);
		//���ò��ֹ�������Ĭ�Ͼ��ж��룩
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//���������ñ���
		this.setTitle("��ʽ����");
				 
		//���ô�С,�����ؼ��㣨�ܶȵ�λ��
		this.setSize(200,200);
		//��ֹ�û��ı䴰���С
		this.setResizable(false);
		
		//���ó�ʼλ��
		this.setLocation(300,300);
		//�رպ��˳�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʾ
		this.setVisible(true);
	}

}