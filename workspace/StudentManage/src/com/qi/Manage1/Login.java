package com.qi.Manage1;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class Login extends JFrame implements ActionListener
{
	//��¼����
	private String loginType="stuLogin";
	JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	//��ǩ
	JLabel jl1=null;
	JLabel jl2=null;
	JLabel jl3=null;
	JLabel jl4=null;
	JLabel jl5=null;
	JLabel jl6=null;
	//��ť
	JButton jb1,jb2;
	//�����
	JTextField jtf;
	//�����
	JPasswordField jpf;
	
	JComboBox jcb=null;
	public Login(){
		
		//��
		jp1=new JPanel();
		jp2=new JPanel();
		
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		jp6=new JPanel();

		jl1=new JLabel("                     ��ӭ����ѧ������ϵͳ!");
		jl2=new JLabel("�û�����");
		jl3=new JLabel("��  ��  ��");
		jl4=new JLabel("��        ��");
		jl6=new JLabel("                     �û���¼");
		
		jtf = new JTextField(10);
		jpf = new JPasswordField(10);
		
		jb1 = new JButton("��¼");
		jb2 = new JButton("�˳�");
		
		
		String[] str = {"ѧ��","��ʦ","����Ա"};
		jcb = new JComboBox(str);
		jcb.addActionListener(this);
		
		ImageIcon image = new ImageIcon("image/login.jpg");
        image.setImage(image.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT));
		jl5=new JLabel(image);
		//��ť
		jb1=new JButton("��¼");
		jb2=new JButton("�˳�");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp3.add(jl2);
		jp3.add(jcb);
		
		jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp4.add(jl3);
		jp4.add(jtf);
		
		jp5.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp5.add(jl4);
		jp5.add(jpf);
		
		jp6.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp6.add(jb1);
		jp6.add(jb2);
		
		jp1.setLayout(new GridLayout(6,1));
		
		jp1.add(jl1);
		jp1.add(jl6);
		jp1.add(jp3);
		jp1.add(jp4);
		jp1.add(jp5);
		jp1.add(jp6);
		
		
		jp2.add(jl5);

		//���ò���
		this.setLayout(new GridLayout(1,2));
		
		this.add(jp1);
		this.add(jp2);
		//���ý���λ�ü���С
		this.setBounds(480, 225, 600, 300);
		//��ֹ�ı�����С
		this.setResizable(false);
		//���ñ���
		this.setTitle("ѧ������ϵͳ");
		//��ʾ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jcb)
		{
			int i = jcb.getSelectedIndex();
			switch(i){
				//0��ʾѧ��
				case 0:
					//1��ʾѧ��
					loginType = "stuLogin";
					break;
				//1��ʾ��ʦ
				case 1:
					loginType = "teaLogin";
					break;
				//2��ʾ����Ա
				case 2:
					loginType = "manLogin";
					break;
				
			}
		}
		if(e.getSource()==jb1){
			if(loginType==null){
				JOptionPane.showMessageDialog
				(this, "��ѡ���¼���", "error",JOptionPane.WARNING_MESSAGE); 
			}else if(loginType.equals("stuLogin")){
				StudentWindow stl = new StudentWindow();
				this.dispose();
			}else if(loginType.equals("teaLogin")){
				TeacherWindow tww= new TeacherWindow();
				this.dispose();
			}else if(loginType.equals("manLogin")){
				ManagerWindow mww= new ManagerWindow();
				this.dispose();
			}
			
		}
		else if(e.getSource()==jb2)
		{
			System.exit(0);
		}
	}
}
	