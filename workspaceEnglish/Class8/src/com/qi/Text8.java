/*
 * ���ֲ��ֹ�������ʹ��
 * JPanel:���������Ƕ�������
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
		
		jl1 = new JLabel("��ļ���");
		jl2 = new JLabel("���εص�");
		
		String[]jg={"����","�Ϻ�","���","����"};
		jcb1=new JComboBox(jg);
		String[]dd={"��կ��","�찲��","�ʹ�","����"};
		jlist=new JList(dd);
		jsp=new JScrollPane(jlist);
		//������ϣ����ʾ���ٸ�ѡ��
		jlist.setVisibleRowCount(2);
		
		//���ò���
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







