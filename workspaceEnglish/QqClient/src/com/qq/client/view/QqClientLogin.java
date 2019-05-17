/**
 * qq�ͻ��˵�¼����
 * 
 */
package com.qq.client.view;
import com.qq.common.*;

import java.io.*;
import java.awt.*;

import javax.swing.*;
import com.qq.client.model.*;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.*;
import java.awt.Event.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
public class QqClientLogin extends JFrame{

	loginPanel lp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqClientLogin qq = new QqClientLogin();
	}

	public QqClientLogin() {
		
		lp = new loginPanel(this);
		
		this.add(lp,"Center");

		this.setSize(350,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
	}		
}
class loginPanel extends JPanel implements KeyListener,ActionListener{
	// ���山����Ҫ�����
		JLabel jl;
		// �����в���Ҫ�����
		//������JPanel,��һ����ѡ����ڹ���
		JTabbedPane jtp;
		JPanel jp2,jp3,jp4;
		JLabel jp2_jl1,jp2_jl2,jp2_jl3,jp2_jl4;
		JButton jp2_jb1;
		JTextField jp2_jtf;
		JPasswordField jp2_jpf;
		JCheckBox jp2_jcb1,jp2_jcb2;
		// �����ϲ���Ҫ�����
		JPanel jp1;
		JButton jp1_jb1,jp1_jb2,jp1_jb3;
		Frame owner;
		public loginPanel(Frame owner) {
			this.owner=owner;
			this.setLayout(new BorderLayout());
			//������
			jl = new JLabel(new ImageIcon("image/tou.gif"));
			//�����в�
			jp2 = new JPanel(new GridLayout(3,3));
			jp2.addKeyListener(this);
			jp2_jl1 = new JLabel("QQ����",JLabel.CENTER);
			jp2_jl2 = new JLabel("QQ����",JLabel.CENTER);
			jp2_jl3 = new JLabel("��������");
			jp2_jl3.setForeground(Color.BLUE);
			jp2_jl4 = new JLabel("�������뱣��");
			jp2_jb1 = new JButton(new ImageIcon("image/clear.gif"));
			jp2_jtf = new JTextField();
			jp2_jtf.addKeyListener(this);
			jp2_jpf = new JPasswordField();
			jp2_jpf.addKeyListener(this);
			jp2_jcb1 = new JCheckBox("�����½");
			jp2_jcb2 = new JCheckBox("��ס����");
			//�ѿؼ���˳����뵽jp2
			jp2.add(jp2_jl1);
			jp2.add(jp2_jtf);
			jp2.add(jp2_jb1);
			jp2.add(jp2_jl2);
			jp2.add(jp2_jpf);
			jp2.add(jp2_jl3);
			jp2.add(jp2_jcb1);
			jp2.add(jp2_jcb2);
			jp2.add(jp2_jl4);
			//����ѡ�����
			jtp = new JTabbedPane();
			jp3= new JPanel();
			jp3.addKeyListener(this);
			jp4 = new JPanel();
			jp4.addKeyListener(this);
			jtp.add(jp2,"QQ����");
			jtp.add(jp3,"�ֻ�����");
			jtp.add(jp4,"�����ʼ�");
			//�����ϲ�
			jp1 = new JPanel();
			jp1_jb1 = new JButton(new ImageIcon("image/denglu.gif"));
			//��Ӧ�û���¼
			jp1_jb1.addActionListener(this);
			jp1_jb2 = new JButton(new ImageIcon("image/quxiao.gif"));
			jp1_jb3 = new JButton(new ImageIcon("image/xiangdao.gif"));
			//��Ӧ�û�ע��
			jp1_jb3.addActionListener(this);
			//����ť����jp1
			jp1.add(jp1_jb1);
			jp1.add(jp1_jb2);
			jp1.add(jp1_jb3);
			jp1.addKeyListener(this);
			this.add(jl,"North");
			this.add(jtp,"Center");
			this.add(jp1,"South");
			this.setFocusable(true);
			addKeyListener(this);
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource()==jp1_jb1)
			{
				//��¼
				this.login();
			}
			if(e.getSource()==jp1_jb3)
			{
				//��Ӧע��
				new QqRegister();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				this.login();
				
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * ��¼
		 */
		public void login()
		{
			QqClientUser qqClientUser = new QqClientUser();
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			if(qqClientUser.checkUser(u))
			{
				//���ͷ������ߺ��ѵİ�
				try {
					//�Ѵ����б�������ǰ
					QqFriendList qqlist = new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqlist);
					
					ObjectOutputStream oos = new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					Message m = new Message();
					
					m.setMesType(MessageType.message_get_onLineFriend);
					//ָ����Ҫ����ŵĺ���
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//ͬʱ�رյ�½����
				this.owner.dispose();
			}else
			{
				JOptionPane.showMessageDialog(this, "��¼ʧ��");
			}
		}	
}
