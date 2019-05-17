/*
 * 我的记事本（界面+功能）
 */
package com.text;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;
public class TextBook extends JFrame implements ActionListener{

	//定义组件
	JTextArea jta=null;
	//菜单条
	JMenuBar jmb=null;
	//一级menu
	JMenu jm1=null;
	//定义JMenuItem
	JMenuItem jmi1=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextBook t = new TextBook();
	}
	public TextBook()
	{
		//创建jta
		jta = new JTextArea();
		//jta放到JFrame
		this.add(jta);
		jmb = new JMenuBar();
		jm1 = new JMenu("文件");
		//设置助记符
		jm1.setMnemonic('F');
		jmi1 = new JMenuItem("打开",new ImageIcon("qq.gif"));
		//注册监听
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		JMenuItem jmi2 = new JMenuItem("保存");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		//加入
		this.setJMenuBar(jmb);
		//把jm1放入jmb
		jmb.add(jm1);
		//把item放入menu
		jm1.add(jmi1);
		jm1.add(jmi2);
	
//		this.add(jmb);
		//放到JFrame
		this.add(jta);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//判断那个菜单被选中
		if(e.getActionCommand().equals("open"))
		{
			System.out.println("打开");
		    //JFileChooser
			//文件选择组件
			JFileChooser jfc1=new JFileChooser();
			//设置名字
			jfc1.setDialogTitle("请选择文件......");
			//使用默认方式
			jfc1.showOpenDialog(null);
			//显示
			jfc1.setVisible(true);
			//得到用户选择的文件全路径
			String filename=jfc1.getSelectedFile().getAbsolutePath();
		//	System.out.println(filename);
			FileReader fr = null;
			BufferedReader br=null;
			try {
				fr=new FileReader(filename);
				br=new BufferedReader(fr);
				//从文件中读取并显示到jta
				String s="";
				String allCon="";
				while((s=br.readLine())!=null)
				{
					allCon+=s+"\r\n";			
				}
				//输出到jta
				jta.setText(allCon);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					fr.close();
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}else if(e.getActionCommand().equals("save"))
		{
			//出现保存对话框
			JFileChooser jfc=new JFileChooser();
			jfc.setDialogTitle("另存为...");
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			//得到用户希望把文件保存到何处
			String file = jfc.getSelectedFile().getAbsolutePath();
			//准备写入到指定文件
			FileWriter fw = null;
			BufferedWriter bw =null;
			try {
				fw=new FileWriter(file);
				bw= new BufferedWriter(fw);
				bw.write(this.jta.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					fw.close();
					bw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
