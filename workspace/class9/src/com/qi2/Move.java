/*
e * �¼��������
 */
package com.qi2;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//�ӿ� ActionListener ����ʵ�����з���
public class Move extends JFrame implements ActionListener{

	//MyPanel mp = null;
	Panel mp = null;
	JButton jb1,jb2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Move m=new Move();
	}
	public Move()
	{
		mp=new Panel();
	//	mp=new MyPanel();
		jb1 = new JButton("��ɫ");
		jb2 = new JButton("��ɫ");
		
		this.add(jb1,BorderLayout.NORTH);
		mp.setBackground(Color.BLUE);
		this.add(mp);
		this.add(jb2,BorderLayout.SOUTH);
		
		Cat cat = new Cat();
		//ע�����
		jb1.addActionListener(this);
		jb1.addActionListener(cat);
		//ָ��action����
		jb1.setActionCommand("black");
		
		jb2.addActionListener(this);
		jb2.setActionCommand("red");
		
		this.setSize(200,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	//����
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�ж����Ǹ���ť�����
		if(e.getActionCommand().equals("black"))
		{
			System.out.println("��ɫ");
			mp.setBackground(Color.BLACK);
		}else if(e.getActionCommand().equals("red"))
		{
			System.out.println("��ɫ");
			mp.setBackground(Color.RED);
		}else
		{
			System.out.println("��֪��");
		}
	}

}
/*class MyPanel extends JPanel
{
	public void paint(Graphics g)
	{
		super.paint(g);
		
	}
}*/
class Cat implements ActionListener
{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand().equals("black"))
		{
			System.out.println("cat");
		}
	}
	
}
