package com.text;

import javax.swing.*;
import java.awt.*;

public class Test0 extends JFrame{
	JList jlist;	//�б��
	JComboBox jcb;	//������
	JPanel jp1, jp2;	//���
	JLabel jlb1, jlb2;
	JScrollPane jsp;	//�����ؼ�
	
	//���캯��
	public Test0(){
		jp1 = new JPanel();
		jp2 = new JPanel();
		
		jlb1 = new JLabel("���ˮƽ��");
		String str1[] = {"�޿�", "����", "һ��", "����"};
		jcb = new JComboBox(str1);
		
		jlb2 = new JLabel("ѡ��Ӣ�ۣ�");
		String str2[] = {"����", "��ϣ", "��Ī", "����", "����", "����"};
		jlist = new JList(str2);
		jlist.setVisibleRowCount(2);//Ĭ����ʾ����
		jsp = new JScrollPane(jlist);
		
		jp1.add(jlb1);
		jp1.add(jcb);
		
		jp2.add(jlb2);
		jp2.add(jsp);
		
			//���񲼾�2��һ��
		this.setLayout(new GridLayout(2, 1));
		
		this.add(jp1);
		this.add(jp2);
		
		this.setSize(200,200);
		this.setTitle("�����ʾ");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Test0 test0 = new Test0();

	}

}