package com.qi;
import java.awt.*;

import javax.swing.*;
import javax.swing.*;
public class text extends JFrame{

	JButton jb1=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		text t1 = new text();
		
	}
	//���캯��
	public text()
	{
		// JFrame��һ�����������������������swing������ࣩ
		//JFrame jf = new JFrame();
		//����һ����ť
		JButton jb1=new JButton("��ť");
		//���JButton���
		this.add(jb1);// �м���ʾ
		
		//���������ñ���
		this.setTitle("Hello,world");
				 
		//���ô�С,�����ؼ��㣨�ܶȵ�λ��
		this.setSize(200,200);
		
		//���ó�ʼλ��
		this.setLocation(300,300);
		//�رպ��˳�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʾ
		this.setVisible(true);
	}

}

