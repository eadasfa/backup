/*
 * ���һ�������ѧ������ϵͳ
 */
package com.test2;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

import java.util.*;

public class StuManage extends JFrame implements ActionListener{

	//����ؼ�
	StuModel sm=null;
	JPanel jp1,jp2;
	JTable jt;
	public JTable getJt() {
		return jt;
	}
	public void setJt(JTable jt) {
		this.jt = jt;
	}
	JButton jb1,jb2,jb3,jb4;
	JScrollPane jsp;
	JLabel jl1;
	JTextField jtf;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuManage test = new StuManage();

	}
	public StuManage(){
		/************************
		 *****jp1��ӿؼ�***********
		 ***********************/
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1 = new JLabel("����������");
		//�Ѹ����ؼ�����JP1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		/************************
		 *****jp2��ӿؼ�***********
		 ***********************/
		jp2 = new JPanel();
		jb2 = new JButton("���");
		jb2.addActionListener(this);
		jb3 = new JButton("�޸�");
		jb3.addActionListener(this);
		jb4 = new JButton("ɾ��");
		jb4.addActionListener(this);
		//�Ѹ�����ť���뵽jp2
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		
	    //����һ������ģ��
		StuModel sm = new StuModel();
		
		jt = new JTable(sm);
		jsp = new JScrollPane(jt);
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(600,400);
		this.setTitle("ѧ������ϵͳ");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//��ѯ
		if(e.getSource()==jb1)
		{
			//��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ
			String name = this.jtf.getText().trim();
			if(name.length()==0)
			{
				sm = new StuModel();
			}else{
				//дһ��SQL���
				Vector seek = new Vector();
				seek.add(name);
				//�����µ�����ģ�Ͳ�����
				sm = new StuModel("Query",seek);
			}
			//����JTable
			jt.setModel(sm);
		}
		//���
		else if(e.getSource()==jb2)
		{
			StuAddDialog sad = new StuAddDialog(this,"���ѧ��",true);
			sm = new StuModel();
			jt.setModel(sm);
		}
		//�޸�
		else if(e.getSource()==jb3)
		{
			//kΪѡ�е��кţ���ʼΪ0
			int k =jt.getSelectedRow(); 
			if(k==-1){
				JOptionPane.showMessageDialog
				(this, "��ѡ�������޸ĵļ�¼", "error",JOptionPane.WARNING_MESSAGE); 
			}else
			{
				String id = (String)jt.getValueAt(k, 0);
				StuUpdateDialog sud= new StuUpdateDialog(this,"�޸���Ϣ",true,id);
				sm = new StuModel();
				jt.setModel(sm);
			}
		}
		//ɾ��
		else if(e.getSource()==jb4)
		{
			//kΪѡ�е��кţ���ʼΪ0,��ûѡ������-1
			int k =jt.getSelectedRow(); 
			
			if(k==-1){
				JOptionPane.showMessageDialog
				(this, "��ѡ������ɾ���ļ�¼", "error",JOptionPane.WARNING_MESSAGE); 
			}else
			{
				String id = (String)jt.getValueAt(k, 0);
				Vector seek = new Vector();
				seek.add(id);
				//�����µ�����ģ�Ͳ�����
				sm = new StuModel("Delete",seek);
				//����JTable
				jt.setModel(sm);
			}
			
		}
		
	}

}
