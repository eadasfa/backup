/**
 * �ҵĺ����б�İ���ˣ�������
 */
package com.qq.client.view;
import com.qq.client.tools.*;
import com.qq.common.Message;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Vector;
public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	Cursor cursor ;
	//�����һ�ſ�Ƭ(�ҵĺ���)
		JPanel jphy1,jphy2,jphy3;
		JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4;
		JScrollPane jsp1;
	//����ڶ��ſ�Ƭ��İ���ˣ�
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	//������JFrame����CardLayout
	CardLayout cl ;
	final String ownerId;
	//�ҵĺ���
	//JLabel []jbls;
	Vector<JLabel> jbls;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqFriendList qq = new QqFriendList("3");
	
	}

	public QqFriendList(String ownerId) {
		this.ownerId =ownerId;
		cursor = new Cursor(Cursor.HAND_CURSOR);
		cl = new CardLayout();
		this.setLayout(cl);
		//�����һ�ſ�Ƭ(�ҵĺ���)
		this.setCardOne();
		//����ڶ��ſ�Ƭ(İ����)
		this.setCardTwo();
		//��ʾ�Լ��ı��
		this.setTitle(ownerId);
		this.setSize(160,400);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new closeWindow(this.ownerId));
		
	}
	public void setCardOne()
	{  
		jphy_jb1 = new JButton("�ҵĺ���");
		jphy_jb2 = new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("������");
		jphy_jb4 = new JButton("��Ӻ���");
		jphy_jb4.addActionListener(this);
		//�����һ�ſ�Ƭ����ʾ�����б�
		jphy1 = new JPanel(new BorderLayout());
		//�ٶ���50�ĺ���
		jphy2 = new JPanel(new GridLayout(50,1,4,4));
		//��jphy2,��ʼ��50������
	//	jbls = new JLabel[50];
//		for(int i=0;i<jbls.length;i++)
//		{
//			jbls[i] = new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
//			jphy2.add(jbls[i]);
//			jbls[i].setCursor(cursor);
//			if(!(jbls[i].getText()).equals(this.ownerId))
//			{
//				jbls[i].setEnabled(false);
//			}
//			jbls[i].addMouseListener(this);
//		}
		jphy3 = new JPanel(new GridLayout(3,1));
		//��������ť���뵽jphy3��
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy3.add(jphy_jb4);
		jsp1 = new JScrollPane(jphy2);
		
		
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		this.add(jphy1,"1");
	}
	public void setCardTwo()
	{
		jpmsr_jb1 = new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("İ����");
		jpmsr_jb3 = new JButton("������");
		//�����һ�ſ�Ƭ����ʾ�����б�
		jpmsr1 = new JPanel(new BorderLayout());
		//�ٶ���50�ĺ���
		jpmsr2 = new JPanel(new GridLayout(20,1,4,4));
		//��jphy2,��ʼ��20������
		JLabel []jbls2 = new JLabel[20];
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i] = new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
			jbls2[i].addMouseListener(this);
		}
		jpmsr3 = new JPanel(new GridLayout(2,1));
		//��������ť���뵽jphy3��
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		jsp2 = new JScrollPane(jpmsr2);
		
		
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		this.add(jpmsr1,"2");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//��������İ���˰�ť����ʾ�ڶ��ſ�Ƭ
		if(e.getSource()==jphy_jb2)
		{
			cl.show(this.getContentPane(), "2");
		}else if(e.getSource()==jpmsr_jb1)
		{
			cl.show(this.getContentPane(), "1");
		}else if(e.getSource()==jphy_jb4)
		{
			new QqAddFriends(this, "��Ӻ���", false,this.ownerId);
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//��Ӧ�û�˫���¼������õ����ѱ��
		if(e.getClickCount()==2)
		{
			//�õ��ú��ѵı��
			String friendNo = ((JLabel)e.getSource()).getText();
			QqChat qqChat =new QqChat(this.ownerId,friendNo);
			//�����������뵽QqChat������
			ManageQqChat.addQqChat(this.ownerId+" "+friendNo, qqChat);
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1 = (JLabel)e.getSource();
		j1.setForeground(Color.black);
	}
	//�������ߺ���
	public void updateFriend(Message m)
	{
		if(!m.isStage()){
			jbls = new Vector<JLabel>();
			JLabel jl1 =null;
			Vector qqfriend = m.getVector();
			for(int i=0;i<qqfriend.size();i++)
			{
				String []ss = qqfriend.get(i).toString().split(" ");
				System.out.println(ss[0]+" "+ss[1]);
				jl1= new JLabel(ss[0]+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
				jbls.add(jl1);
				jphy2.add(jl1);
				jl1.setCursor(cursor);
				
				//false��length==5����������falseʱ
				if(ss[1].length()==5)
				{
					jl1.setEnabled(false);
				}
				jl1.addMouseListener(this);
			}
		}else
		{
			//ˢ�º���
			System.out.println("ok");
			for(int i=0;i<jbls.size();i++)
			{
				if(((jbls.get(i)).getText()).equals(m.getCon()))
				{
					(jbls.get(i)).setEnabled(true);
				}
			}
		}
	}
	class closeWindow extends WindowAdapter{
		String uid;
		
		public closeWindow(String uid) {
			super();
			this.uid = uid;
			// TODO Auto-generated constructor stub
		}

		public void windowClosing(WindowEvent e) {
			
			//�õ��ͻ����������������
			ClientConServerThread ccst=ManageClientConServerThread.getClientConServerThread(this.uid);
			if(ccst!=null)
			{
				//����������ӣ�����������Ϳͻ���Ҫ�رյ���Ϣ
				ccst.sendMessageCloseClient();
			}
			System.exit(0);
		}
	
	}
	 
}
