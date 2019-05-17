/*
 * �ҵļ��±�������+���ܣ�
 */
package com.text;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;
public class TextBook extends JFrame implements ActionListener{

	//�������
	JTextArea jta=null;
	//�˵���
	JMenuBar jmb=null;
	//һ��menu
	JMenu jm1=null;
	//����JMenuItem
	JMenuItem jmi1=null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TextBook t = new TextBook();
	}
	public TextBook()
	{
		//����jta
		jta = new JTextArea();
		//jta�ŵ�JFrame
		this.add(jta);
		jmb = new JMenuBar();
		jm1 = new JMenu("�ļ�");
		//�������Ƿ�
		jm1.setMnemonic('F');
		jmi1 = new JMenuItem("��",new ImageIcon("qq.gif"));
		//ע�����
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		JMenuItem jmi2 = new JMenuItem("����");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		//����
		this.setJMenuBar(jmb);
		//��jm1����jmb
		jmb.add(jm1);
		//��item����menu
		jm1.add(jmi1);
		jm1.add(jmi2);
	
//		this.add(jmb);
		//�ŵ�JFrame
		this.add(jta);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�ж��Ǹ��˵���ѡ��
		if(e.getActionCommand().equals("open"))
		{
			System.out.println("��");
		    //JFileChooser
			//�ļ�ѡ�����
			JFileChooser jfc1=new JFileChooser();
			//��������
			jfc1.setDialogTitle("��ѡ���ļ�......");
			//ʹ��Ĭ�Ϸ�ʽ
			jfc1.showOpenDialog(null);
			//��ʾ
			jfc1.setVisible(true);
			//�õ��û�ѡ����ļ�ȫ·��
			String filename=jfc1.getSelectedFile().getAbsolutePath();
		//	System.out.println(filename);
			FileReader fr = null;
			BufferedReader br=null;
			try {
				fr=new FileReader(filename);
				br=new BufferedReader(fr);
				//���ļ��ж�ȡ����ʾ��jta
				String s="";
				String allCon="";
				while((s=br.readLine())!=null)
				{
					allCon+=s+"\r\n";			
				}
				//�����jta
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
			//���ֱ���Ի���
			JFileChooser jfc=new JFileChooser();
			jfc.setDialogTitle("���Ϊ...");
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			//�õ��û�ϣ�����ļ����浽�δ�
			String file = jfc.getSelectedFile().getAbsolutePath();
			//׼��д�뵽ָ���ļ�
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
