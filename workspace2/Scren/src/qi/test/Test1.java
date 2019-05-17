package qi.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Test1 extends JFrame {
	BufferedImage bi=null;
	Robot robot = null;
	BufferedImage bf=null;
	double length=0;
	int width=380;
	public Test1()
	{
		try {
			robot = new Robot();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle r = new Rectangle(d);
			bf = robot.createScreenCapture(r);
			
			bi = (BufferedImage)(ImageIO.read(new File("src/images/00.png")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bi.getHeight()+" "+bi.getWidth());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(d);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void paint(Graphics g)
	{
		this.shot();
	
		g.drawImage(bi, 0, 0,this.getWidth(),this.getHeight(), this);
		g.fillOval(width, 350, 30, 30);
		//	g.drawImage(bf, 400, 0, 1366-400,768,this);
	}
	public static void main(String[] args) {
		new Test1();
	}
	
	public double judge(int c1,int c)
	{
		int red1 = (0xff)&(c1>>16);
		int green1 = (0xff)&(c1>>8);
		int blue1= (0xff)&c1;
		
		int red= (0xff)&(c>>16);
		int green = (0xff)&(c>>8);
		int blue= (0xff)&c;
		int r1 = red1-red;
		int b1 = blue1-blue;
		int g1 = green1-green;
		double t = Math.sqrt(r1*r1+b1*b1+g1*g1);
		return  Math.sqrt(r1*r1+b1*b1+g1*g1);
	}
	public void shot()
	{
		int qizi=-13290178;
		int yinying = -8025430;
		Graphics g = bi.createGraphics();
//		int m=160;//780
//		int n=367;//1000	R:100  G:149  B:105
//		int c=bi.getRGB(m, n);
//		int red = (0xff)&(c>>16);
//		int green = (0xff)&(c>>8);
//		int blue= (0xff)&c;
//		int z=c;
//		System.out.println("1:R:"+red+"  G:"+green+"  B:"+blue+" "+c);
//		g.setColor(Color.black);
//		g.fillOval(m, n, 5, 5);
		g.setColor(Color.red);
		
		int k=0;
		
		int x1 = 0,y1=0;
		int color=0;
		outer:
		for(int j=bi.getHeight()/6+30;j<bi.getHeight();j++)
		{
			for(int i=0;i<width;i++)
			{
				int c1=bi.getRGB(i, j);
				if(judge(c1,qizi)<20)
				{
					
					g.setColor(Color.blue);
					System.out.println(i+" "+j);
					g.fillOval(i, j+55, 10,10);
					break outer;
					
				}
				if(i>5)
				{
					
					if(k==0&&judge(c1,color)>5)
					{
						k=1;
						x1=i;y1=j;
						System.out.println(i+" "+j);
						g.setColor(Color.red);
						g.fillOval(i, j, 30,30);
						
					}else if(k==1&&i<x1)
					{
						
						if(judge(c1,bi.getRGB(i-1, j))>20&&
								judge(c1,bi.getRGB(i, j+1))>20
								&&judge(bi.getRGB(i, j+10), yinying)<20)//&&judge(bi.getRGB(i, j+10), z)<20
						{
							g.setColor(Color.black);
							System.out.println("in:"+i+" "+j);
							g.fillOval(i,j,5,5);
							k=2;
							g.setColor(Color.red);
							g.fillOval(x1-10,j-10,5,5);
							length = Math.sqrt((x1-i)*(x1-i)+(j-y1)*(j-y1));
						}
						
					}
				}
				color=c1;
//				if(i==780&&j==1000)
//				{
//					int c1=bi.getRGB(i, j);
//					int red1 = (0xff)&(c1>>16);
//					int green1 = (0xff)&(c1>>8);
//					int blue1= (0xff)&c1;
//					g.fillOval(i, j, 30, 30);
//				}
				
			}
		}
	}
}

