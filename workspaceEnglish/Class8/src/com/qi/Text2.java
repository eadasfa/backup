/* �߽粼��
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

public class Text2 extends JFrame{

	JButton jb1,jb2,jb3,jb4,jb5;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text2 t1 = new Text2();
		
	}
	//���캯��
	public Text2()
	{
		// JFrame��һ�����������������������swing������ࣩ
		//JFrame jf = new JFrame();
		//����һ����ť
		JButton jb1=new JButton("��");
		JButton jb2=new JButton("��");
		JButton jb3=new JButton("��");
		JButton jb4=new JButton("��");
		JButton jb5=new JButton("��");
		//���JButton���
		this.add(jb1,BorderLayout.CENTER);
		this.add(jb2,BorderLayout.NORTH);
		this.add(jb3,BorderLayout.EAST);
		this.add(jb4,BorderLayout.SOUTH);
		this.add(jb5,BorderLayout.WEST);
		//���������ñ���
		this.setTitle("�߽粼��");
				 
		//���ô�С,�����ؼ��㣨�ܶȵ�λ��
		this.setSize(300,200);
		
		//���ó�ʼλ��
		this.setLocation(300,300);
		//�رպ��˳�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʾ
		this.setVisible(true);
	}

}