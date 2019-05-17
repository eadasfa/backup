package com.qi.sudo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Format;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class Sudo extends JFrame implements ActionListener{
	SudoPanel sp = null;
	JButton jb = null;
	int array[][] = new int[9][9];
	int count=0;
	JLabel jl = null,jl1=null;
	String str="";
	public static void main(String[] args) {
		new Sudo();
	}
	public Sudo()
	{
		this.setLayout(null);
		
		sp = new SudoPanel();
		sp.setBounds(10, 10, 248, 248);
		this.add(sp);
		
		jb = new JButton("求解");
		jb.setBounds(290, 10, 80, 30);
		jb.addActionListener(this);
		this.add(jb);
		
		jl = new JLabel();
		jl.setBounds(280, 50, 120, 50);
		this.add(jl);
		jl1 = new JLabel();
		jl1.setBounds(280, 100, 120, 50);
		this.add(jl1);
		
		this.setSize(400,300);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb)
		{
			array=sp.getArray();
			if(!this.isLegal())
			{
				jl.setText("输入不合法:");
				jl1.setText(str);
			}
			else
			{
				if(this.excute())
				{
					sp.setArray(array);
					
				}else{
					jl.setText("无解");
				}
			}
			
		}
	}
	public boolean excute()
	{
		return excute(0,0);
	}
	public boolean excute(int i,int j)
	{
		//寻找要填充的格子
		outer:
		for(int m=0;m<9;m++)
		{
			for(int n=0;n<9;n++)
			{
				if(array[m][n]==0)
				{
					i=m;
					j=n;
					break outer;
				}
				
			}
		}
		if(array[i][j]!=0)
		{
			//完毕
			this.count++;
			
			return true;
		}
		
		for(int k=1;k<=9;k++)
		{
			if(!check(i,j,k))
				continue;
			array[i][j]=k;
			
			if(i==8&&j==8)
			{
				this.count++;
				return true;
			}
			int nextRow=j<8?i:i+1;
			int nextCol=j<8?j+1:0;
			if(!excute(nextRow,nextCol))
			{
				array[i][j]=0;
			}
			else 
			{
				return true;
			}
		}
		return false;
	}
	public boolean check(int i,int j, int k)
	{
		if(k<=0)
			return false;
		for(int m=0;m<9;m++)
		{	
			if(array[i][m]==k)
				return false;
			if(array[m][j]==k)
				return false;
			if(array[i/3*3+m/3][j/3*3+m%3]==k)
				return false;
		}
		return true;
	}
	public boolean isLegal()
	{
		Set rowset = new HashSet();
		Set colset = new HashSet();
		Set griset = new HashSet();
		for(int i=0;i<9;i++)
		{
			rowset.clear();
			colset.clear();
			griset.clear();
			for(int j=0;j<9;j++)
			{
				if(array[i][j]<0)
				{
					str="数独输入错误";
					return false;
				}
				if(array[i][j]>0&&!rowset.add(array[i][j]+""))
				{
					str="第"+i+"行数独重复";
					return false;
				}
					
				if(array[j][i]>0&&!colset.add(array[j][i]+""))
				{
//					for(int m=0;m<9;m++)
//					{
//						for(int n=0;n<9;n++)
//						{
//							System.out.print(array[m][n]);
//						}
//						System.out.println();
//					}
					str="第"+i+"列数独重复";
					return false;
				}
				if(array[i/3*3+j/3][j%3+(i%3)*3]>0&&!griset.add(array[i/3*3+j/3][j%3+(i%3)*3]+""))
				{
					str="第("+((i/3*3+j/3)/3)+","+((j%3+(i%3)*3)/3)+")宫数独重复";
					return false;
				}
			}
		}
		return true;
	}
}
class SudoPanel extends JPanel
{
	JTextField [][]jf = null;
	Set set = new HashSet();
	int a[][]=new int[9][9];
	JPanel jp[][] = new JPanel[3][3];
	public SudoPanel()
	{
		this.setLayout(null);
		jf = new JTextField[9][9];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				jp[i][j] = new JPanel();
				jp[i][j].setBounds(j*80+(j+1)*2,i*80+(i+1)*2,  80, 80);
				jp[i][j].setLayout(new GridLayout(3,3));
				this.add(jp[i][j]);
			}
		}
		this.updateJf();
		this.getArray();
		
	}
	public void updateJf()
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				jf[i][j] = new JTextField();
				jf[i][j].setFont(new Font("宋体",Font.BOLD,20));
				jp[i/3][j/3].add(jf[i][j]);
			}
		}
		
	}
//	public void paint(Graphics g)
//	{
//		g.setColor(Color.red);
//		for(int i=0;i<4;i++)
//		{
//			g.drawLine((i+1)+i*80, 0, (i+1)+i*80, this.getHeight()-4);
//			g.drawLine(0, (i+1)+i*80, this.getWidth()-4, (i+1)+i*80);
//		}
//		
//		this.setVisible(true);
//	}
	public int[][] getArray()
	{	
		String s = "123456789456789123000000000000500000000000000000000000000000000000000070000000000";
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
//				if(jf[i][j].getText().isEmpty())
//					a[i][j]=0;
//				else
//					a[i][j]=Integer.parseInt((jf[i][j].getText().toString()+"1").trim())/10;
//				jf[i][j].setEditable(false);
//				if(a[i][j]!=0)
//				{
//					set.add(i+""+j);
//				}
				
				
				
				a[i][j]=Integer.parseInt(s.substring(i*9+j, i*9+j+1));
//				jf[i][j].setEditable(false);
				if(a[i][j]>0)
					jf[i][j].setText(a[i][j]+"");
				if(a[i][j]!=0)
				{
					set.add(i+""+j);
				}
			
			}
		}
		
		return a;
	}
	public void setArray(int a[][])
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{
				if(!set.add(i+""+j))
				{
					jf[i][j].setForeground(Color.blue);
					jf[i][j].setText(String.valueOf(a[i][j]));
				}
				else
					jf[i][j].setText(String.valueOf(a[i][j]));
				
			}
		}
//		this.setVisible(true);
	}
}
