/*��½����
 * �ı���          �����                  ��ǩ���
 * JTextField JPasswordField JLable
 */
package com.qi;
import java.awt.*;
import javax.swing.*;

public class Text6 extends JFrame{
	
	JPanel jp1,jp2,jp3;
	JLabel jlb1,jlb2;
	JButton jb1,jb2;
	JTextField jtf1;
	JPasswordField jpf1;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text6 t6= new Text6();
	}
	public Text6()
	{
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jlb1 = new JLabel("�û���");
		jlb2 = new JLabel("��    ��");
		jb1=new JButton("��¼");
		jb2=new JButton("ȡ��");
		
		jtf1=new JTextField(10);
		jpf1=new JPasswordField(10);
		
		//���ò���
		this.setLayout(new GridLayout(3,1));
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		jp2.add(jlb2);
		jp2.add(jpf1);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.setTitle("��");
		this.setSize(300,150);
		this.setLocation(300,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}
