package qi.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Minesweeper extends JFrame implements ActionListener{
	GamePanel gp ;
	int M=9;
	int N=9;
	int bombNumber=10;
	JMenuBar jmb = null;
	JMenu jm = null;
	JMenuItem difficult[]=new JMenuItem[3];
	public static void main(String[] args) {
		new Minesweeper();
	}
	public Minesweeper()
	{
		this.setLayout(new BorderLayout());
		
		this.initMenu();
		this.addGamePanel();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void addGamePanel()
	{
		this.setSize(N*30+30,M*30+60);
		gp = new GamePanel(M, N,bombNumber);
		this.add(gp,"Center");
	}
	public void initMenu()
	{
		jmb = new JMenuBar();
		jm = new JMenu("难度");
		difficult[0] = new JMenuItem("初级★");
		difficult[0].addActionListener(this);
		difficult[0].setActionCommand("初级");
		difficult[1] = new JMenuItem("中级");
		difficult[1].addActionListener(this);
		difficult[1].setActionCommand("中级");
		difficult[2] = new JMenuItem("困难");
		difficult[2].addActionListener(this);
		difficult[2].setActionCommand("困难");
		
//		difficult[0].setMnemonic('G');

		jm.add(difficult[0]);
		jm.add(difficult[1]);
		jm.add(difficult[2]);
		jmb.add(jm);
		this.setJMenuBar(jmb);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="初级")
		{
			difficult[0].setText("初级★");
			difficult[1].setText("中级");
			difficult[2].setText("困难");
			M=9;N=9;
			bombNumber=10;
			this.remove(gp);
			this.addGamePanel();
		}else if(e.getActionCommand()=="中级")
		{
			difficult[0].setText("初级");
			difficult[1].setText("中级★");
			difficult[2].setText("困难");
			M=16;N=16;
			bombNumber=40;
			this.remove(gp);
			this.addGamePanel();
		}else
		{
			difficult[0].setText("初级");
			difficult[1].setText("中级");
			difficult[2].setText("困难★");
			M=16;N=30;
			bombNumber=99;
			this.remove(gp);
			this.addGamePanel();
		}
	}
	
}
class GamePanel extends JPanel implements ActionListener,MouseListener
{
	//M*N方格
	private int M=0;
	private int N=0;
	int bombNumber=0;
	JLabel label[][];
	int value[][];
	JButton button[][];
	Image bomb=null;//炸弹的图片
	Image flag = null;
	Color color = Color.magenta;
	public GamePanel(int M,int N,int bombNumber)
	{
		this.M=M;
		this.N=N;
		this.bombNumber = bombNumber;
		value = new int[M+2][N+2];

		this.produceValue();
//		for(int i=0;i<M+2;i++)
//		{
//			for(int j=0;j<N+2;j++)
//			{
//				System.out.print(value[i][j]+"  ");
//			}
//			System.out.println();
//		}
		label = new JLabel[M+1][N+1];
		button = new JButton[M+1][N+1];
		this.setLayout(new GridLayout(M, N));
		for(int i=1;i<=M;i++)
		{
			for(int j=1;j<=N;j++)
			{
				button[i][j]=new JButton();
				button[i][j].setActionCommand(i+" "+j);
				button[i][j].addActionListener(this);
				button[i][j].addMouseListener(this);
//				button[i][j].setText(value[i][j]+"");
				this.add(button[i][j]);
			}
		}
		//初始化炸弹的图片
		try {
			bomb = ImageIO.read(
					ClassLoader.getSystemResourceAsStream(
					"images/bomb.png"));	
			flag = ImageIO.read(
					ClassLoader.getSystemResourceAsStream(
					"images/flag-icon.psd"));	
		} catch (IOException e1) {
			System.out.println("图片加载失败！");
			e1.printStackTrace();
		}
	}
	
	void produceValue()
	{
		int x,y;
		int i=0;
		//产生炸弹
		while(true)
		{
			x = (int)(Math.random()*(M+1));
			y = (int)(Math.random()*(N+1));
			if(x==0||y==0||x>M||y>N||value[x][y]==-1)
				continue;
			i++;
			value[x][y]=-1;
			if(i==this.bombNumber)
				break;
		}
		//生成值
		for(int m=1;m<=M;m++)
		{
			for(int n=1;n<=N;n++)
			{
				if(value[m][n]!=-1)
				{
					value[m][n] = this.getArroundBombNumber(m, n);
				}
			}
		}
	}
	public int getArroundBombNumber(int x,int y)
	{
		int t=0;
		for(int i=0;i<9;i++)
		{
			t+=value[x+(i/3-1)][y+(i%3-1)]<0?1:0;
		}
		return t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s[] = e.getActionCommand().split(" ");
		
		int i=Integer.parseInt(s[0].trim());
		int j=Integer.parseInt(s[1].trim());
//		jb.setHorizontalAlignment(JButton.LEFT);//按钮上的字左对齐
		JButton jb = (JButton)(e.getSource());
		jb.setBackground(color);
		if(value[i][j]==-1)
		{
			jb.setText(null);
			jb.setIcon(new ImageIcon(bomb));
		}else 
		{
			if(value[i][j]==0)
			{
				int a[][]= new int[M+2][N+2];
				this.isZero(i,j,a);
				this.showZero(a);
			}else
			{
				jb.setMargin(new Insets(0,0,0,0));		//设置边距间隔
//				Insets m =jb.getMargin();
//				System.out.println(m.toString());
				jb.setText(value[i][j]+"");
			}
			jb.setEnabled(false);
			
		}
		
	}
	public void isZero(int i,int j,int a[][])
	{
		a[i][j]=1;
		for(int k=0;k<9;k++)
		{
			int m = i+(k/3-1);
			int n = j+(k%3-1);
			if(k!=4&&m<=M&&n<=N&&m>0&&n>0&&a[m][n]==0)
			{
				if(value[m][n]==0)
				{
					isZero(m, n, a);
				}else
				{
					a[m][n]=1;
				}
			}
		}
	}
	public void showZero(int a[][])
	{
		for(int i=1;i<=M;i++)
		{
			for(int j=1;j<=N;j++)
			{
				
				if(a[i][j]==1)
				{
					
					String s="";
					if(value[i][j]>0)
						s=value[i][j]+"";
					JButton jb = button[i][j];
					jb.setMargin(new Insets(0,0,0,0));	
					jb.setText(s);
					jb.setEnabled(false);
					button[i][j].setBackground(color);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//右键点击
		if(e.getButton()==MouseEvent.BUTTON3)
		{
			JButton jb = (JButton)e.getSource();
			if(jb.isEnabled())
			{
				jb.setMargin(new Insets(0,0,0,0));
				if(jb.getText()==null||jb.getText()=="")
				{		
					jb.setText("◆");
				}else if(jb.getText()=="?")
				{
					jb.setText("");
				}else
				{
					jb.setText("?");
				}
			}
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
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
		
}
