/*
 * QQ聊天界面
 */
package com.qi;
import java.awt.*;
import javax.swing.*;
public class Text10 extends JFrame{

	JPanel jp1;
	JTextArea jta;
	JComboBox jcb;
	JTextField jtf;
	JButton jb;
	JScrollPane jlp;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Text10 t= new Text10();
	}
	public Text10()
	{
		
		jp1 = new JPanel();
		//jlp = new JScrollPane();//滚动的
		jta = new JTextArea();	
		jlp = new JScrollPane(jta);//是TEXTAREA滚动
		String [] chatter ={"布什","拉登"};
		jtf = new JTextField(10);
		jcb = new JComboBox(chatter);
		jb = new JButton("发送");
		
		jp1.add(jcb);
		jp1.add(jtf);
		jp1.add(jb);
		this.add(jlp);
		this.add(jp1,BorderLayout.SOUTH);
		
		//属性
		this.setIconImage((new ImageIcon("image\\abc.jpg")).getImage());

		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
