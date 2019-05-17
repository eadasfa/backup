/**
 * qq客户端登录界面
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
	// 定义北部需要的组件
		JLabel jl;
		// 定义中部需要的组件
		//有三个JPanel,有一个叫选项卡窗口管理
		JTabbedPane jtp;
		JPanel jp2,jp3,jp4;
		JLabel jp2_jl1,jp2_jl2,jp2_jl3,jp2_jl4;
		JButton jp2_jb1;
		JTextField jp2_jtf;
		JPasswordField jp2_jpf;
		JCheckBox jp2_jcb1,jp2_jcb2;
		// 定义南部需要的组件
		JPanel jp1;
		JButton jp1_jb1,jp1_jb2,jp1_jb3;
		Frame owner;
		public loginPanel(Frame owner) {
			this.owner=owner;
			this.setLayout(new BorderLayout());
			//处理北部
			jl = new JLabel(new ImageIcon("image/tou.gif"));
			//处理中部
			jp2 = new JPanel(new GridLayout(3,3));
			jp2.addKeyListener(this);
			jp2_jl1 = new JLabel("QQ号码",JLabel.CENTER);
			jp2_jl2 = new JLabel("QQ密码",JLabel.CENTER);
			jp2_jl3 = new JLabel("忘记密码");
			jp2_jl3.setForeground(Color.BLUE);
			jp2_jl4 = new JLabel("申请密码保护");
			jp2_jb1 = new JButton(new ImageIcon("image/clear.gif"));
			jp2_jtf = new JTextField();
			jp2_jtf.addKeyListener(this);
			jp2_jpf = new JPasswordField();
			jp2_jpf.addKeyListener(this);
			jp2_jcb1 = new JCheckBox("隐身登陆");
			jp2_jcb2 = new JCheckBox("记住密码");
			//把控件按顺序加入到jp2
			jp2.add(jp2_jl1);
			jp2.add(jp2_jtf);
			jp2.add(jp2_jb1);
			jp2.add(jp2_jl2);
			jp2.add(jp2_jpf);
			jp2.add(jp2_jl3);
			jp2.add(jp2_jcb1);
			jp2.add(jp2_jcb2);
			jp2.add(jp2_jl4);
			//创建选项卡窗口
			jtp = new JTabbedPane();
			jp3= new JPanel();
			jp3.addKeyListener(this);
			jp4 = new JPanel();
			jp4.addKeyListener(this);
			jtp.add(jp2,"QQ号码");
			jtp.add(jp3,"手机号码");
			jtp.add(jp4,"电子邮件");
			//处理南部
			jp1 = new JPanel();
			jp1_jb1 = new JButton(new ImageIcon("image/denglu.gif"));
			//响应用户登录
			jp1_jb1.addActionListener(this);
			jp1_jb2 = new JButton(new ImageIcon("image/quxiao.gif"));
			jp1_jb3 = new JButton(new ImageIcon("image/xiangdao.gif"));
			//响应用户注册
			jp1_jb3.addActionListener(this);
			//将按钮加入jp1
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
				//登录
				this.login();
			}
			if(e.getSource()==jp1_jb3)
			{
				//响应注册
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
		 * 登录
		 */
		public void login()
		{
			QqClientUser qqClientUser = new QqClientUser();
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			if(qqClientUser.checkUser(u))
			{
				//发送返回在线好友的包
				try {
					//把创建列表的语句提前
					QqFriendList qqlist = new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqlist);
					
					ObjectOutputStream oos = new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					Message m = new Message();
					
					m.setMesType(MessageType.message_get_onLineFriend);
					//指明我要这个号的好友
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//同时关闭登陆界面
				this.owner.dispose();
			}else
			{
				JOptionPane.showMessageDialog(this, "登录失败");
			}
		}	
}
