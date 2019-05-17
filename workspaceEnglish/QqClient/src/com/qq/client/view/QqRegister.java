package com.qq.client.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.Mytools;
import com.qq.common.Message;
import com.qq.common.MessageType;


public class QqRegister extends JFrame implements ActionListener{
	JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7,jp8,jp9;
	JLabel jp1_jl1;
	JLabel jp2_jl1,jp2_jl2,jp2_jl3,jp2_jl4,jp2_jl5,jp2_jl6;
	JTextField jp2_jtf1;
	JPasswordField jp2_jpf2,jp2_jpf3;
	
	JRadioButton br1,br2;
	ButtonGroup bg1;
	
	JComboBox jcb1,jcb2,jcb3,jcb4,jcb5,jcb6;
	JScrollPane js1,js2,js3,js4,js5,js6;
	Calendar now ;
	JButton jb1,jb2;
	public static void main(String[] args) {
		new QqRegister();
	}
	public QqRegister() {
		this.setLayout(new BorderLayout());
		//jp1
		this.setJp1();
		//jp2
		this.setJp2();
		
		
		jp6 = new JPanel();
		jb1 = new JButton("ȷ��ע��");
		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��ע��");
		jb2.addActionListener(this);
		jp6.add(jb1);
		jp6.add(jb2);
		this.add(jp6,"South");
		
		this.setSize(680,480);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1)
		{
			//ȷ��ע��
			String nickName = jp2_jtf1.getText();
			String passWd1 = String.valueOf(jp2_jpf2.getPassword());
			String passWd2 = String.valueOf(jp2_jpf3.getPassword());		
			String sex=null;
			if(this.br1.isSelected()){	
				sex = "��";
			}
			else if(this.br2.isSelected()) {
				sex = "Ů";
			}
			String year = jcb1.getSelectedItem().toString();
			String month = jcb2.getSelectedItem().toString();
			String day = jcb3.getSelectedItem().toString();
			String country = jcb4.getSelectedItem().toString();
			String province = jcb5.getSelectedItem().toString();
			String city = jcb6.getSelectedItem().toString();
		
			if(nickName.equals(""))
			{
				JOptionPane.showMessageDialog(this, "����д�ǳ�");
			}
			else if(!passWd1.equals(passWd2)||passWd1.equals(""))
			{
				JOptionPane.showMessageDialog(this, "�������벻ƥ�������Ϊ��");
			}
			else if(sex==null)
			{
				JOptionPane.showMessageDialog(this, "��ѡ���Ա�");
			}
			else if(year.equals("��")||month.equals("��")||day.equals("��"))
			{
				JOptionPane.showMessageDialog(this, "��ѡ������");
			}
			
			else if(country.equals("����")||province.equals("ʡ��")||city.equals("����"))
			{
				JOptionPane.showMessageDialog(this, "��ѡ������");
			}
			else {
				String qqnumber="  ";
				
				String []paras = {nickName,passWd1,sex,year,month,day,country,province,city,qqnumber};
				Message m = new Message();
				m.setMesType(MessageType.message_register);
				m.setUserRegister(paras);
				QqClientUser qqClientUser = new QqClientUser();
				qqnumber = qqClientUser.sendRegisterMessage(m);
				if(!qqnumber.equals(""))
				{
					new QqRegisterSuccess(this, "QQע��", true, qqnumber);
					this.dispose();
				}else
				{
					JOptionPane.showMessageDialog(this, "ע��ʧ��");
				}
				
			}
			
			
		}else if(e.getSource()==jb2)
		{
			//ȡ��ע��
			this.dispose();
		}
		
	}
	public void setJp1()
	{
		jp1 = new JPanel();
		jp1_jl1 = new JLabel(new ImageIcon("image/zhuce.png"));
		jp1.add(jp1_jl1);
		this.add(jp1,"North");
	}
	public void setJp2()
	{
		//�ǳ�
		jp2 = new JPanel();
		jp2.setLayout(null);
		jp2_jl1 = new JLabel("�ǳ�");
		jp2_jl1.setFont(Mytools.f1);
		jp2_jtf1  =  new JTextField(10);
		jp2_jl1.setBounds(190, 30, 60, 40);
		jp2_jtf1.setBounds(250, 40, 250, 22);
		jp2.add(jp2_jl1);
		jp2.add(jp2_jtf1);
		//����
		jp2_jl2 = new JLabel("����");
		jp2_jl2.setFont(Mytools.f1);
		jp2_jpf2 = new JPasswordField(10);
		jp2_jl2.setBounds(190, 70, 60, 40);
		jp2_jpf2.setBounds(250, 80,250, 22);
		jp2.add(jp2_jl2);
		jp2.add(jp2_jpf2);
		//ȷ������
		jp2_jl3 = new JLabel("ȷ������");
		jp2_jpf3 = new JPasswordField(10);
		jp2_jl3.setFont(Mytools.f1);
		jp2_jl3.setBounds(150, 110, 80, 40);
		jp2_jpf3.setBounds(250, 120, 250, 22);
		jp2.add(jp2_jl3);
		jp2.add(jp2_jpf3);
		//�Ա�
		jp2_jl4 = new JLabel("�Ա�");
		jp2_jl4.setFont(Mytools.f1);
		jp2_jl4.setBounds(190, 150, 60, 40);
		br1 = new JRadioButton("��");
		br1.setFont(Mytools.f1);
		br2 = new JRadioButton("Ů");
		br2.setFont(Mytools.f1);
		bg1 = new ButtonGroup();
		bg1.add(br1);
		bg1.add(br2);
		jp2.add(jp2_jl4);
		jp3 = new JPanel();
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp3.add(br1);
		jp3.add(br2);
		jp3.setBounds(250, 150, 250, 28);
		jp2.add(jp3);
		//����
		jp2_jl5 = new JLabel("����");
		jp2_jl5.setFont(Mytools.f1);
		jp2_jl5.setBounds(190, 190, 60, 40);
		jp2.add(jp2_jl5);
		now = Calendar.getInstance();
		//��
		int times1 = now.get(Calendar.YEAR)-1900+2;
		String[] year = new String[times1];
		year[0]="��";
		for(int i=1;i<times1;i++)
		{
			year[i] = String.valueOf(now.get(Calendar.YEAR)-i+1);
		}
		jcb1 = new JComboBox(year);
		js1 = new JScrollPane(jcb1);
		//��
		String[] month = new String[13];
		month[0]="��";
		for(int i=1;i<13;i++)
		{
			month[i]=String.valueOf(i);
		}
		jcb2 = new JComboBox(month);
		js2 = new JScrollPane(jcb2);
		//��
		String[]day = new String[32];
		day[0]="��";
		for(int i=1;i<32;i++)
		{
			day[i]=String.valueOf(i);
		}
		jcb3 = new JComboBox(day);
		js3 = new JScrollPane(jcb3);
		jp4 = new JPanel();
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp4.add(js1);
		jp4.add(js2);
		jp4.add(js3);
		jp4.setBounds(250, 190, 250, 35);
		jp2.add(jp4);
		//���ڵ�
		jp2_jl6 = new JLabel("���ڵ�");
		jp2_jl6.setFont(Mytools.f1);
		jp2_jl6.setBounds(170, 230, 60, 40);
		jp2.add(jp2_jl6);
		
		String []country={"����","�й�"};
		jcb4 = new JComboBox(country);
		js4 = new JScrollPane(jcb4);
		
		String []province={"ʡ��","����","ɽ��"};
		jcb5 = new JComboBox(province);
		js5 = new JScrollPane(jcb5);
		
		String []city={"����","����","����"};
		jcb6 = new JComboBox(city);
		js6 = new JScrollPane(jcb6);
		
		jp5 = new JPanel();
		jp5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp5.add(js4);
		jp5.add(js5);
		jp5.add(js6);
		jp5.setBounds(250, 230, 250, 35);
		jp2.add(jp5);
		
		
		this.add(jp2,"Center");
	}


	
}
