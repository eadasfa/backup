package qi.clickscren2;

/**
 *  点击电脑屏幕
 */


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import  java.awt.Robot;
import java.awt.Toolkit;


import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;




import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;



public class ClickScren extends JFrame implements Runnable{
	static boolean flag=false;//启动线程
	double a=3.8;//系数
	Robot robot =null;
	ClickScren cs = this;
	JLabel state=null;		
	JTextField jtf = null;
	BufferedImage bi=null;//截屏
	Rectangle screenRect=null;//屏幕方框
	int width = 380;//游戏界面宽度
	Picture picture=null;
	public ClickScren(){	
		try {
			robot = new Robot();
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			screenRect = new Rectangle(d);
			picture = new Picture();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.setSize(300, 300);
		this.setResizable(false);
		this.setLocation(900, 300);
		
		this.setLayout(null);
	
		jtf= new JTextField(a+"");
		jtf.setBounds(80, 20, 90, 30);
		this.add(jtf);
		
		state=new JLabel("未启动");
		state.setBounds(200, 60, 100, 50);
		this.add(state);
		JIntellitype.getInstance().registerHotKey(0, 0, KeyEvent.VK_SPACE);
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			
			public void onHotKey(int arg0) {
				switch(arg0){
				
				case 0:

					if(flag)
					{
						flag = false;
						state.setText("未启动");
					}else
					{
						flag = true;
						state.setText("已启动");
						new Thread(cs).start();
					}
//					shot();
//					showPicture();
					break;
				
				default:
				}
			}
		});
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		

	}

	public static void main(String[] args) {
		
		ClickScren cs = new ClickScren();
		
		new Thread(cs).start();
	}

	public void run() {
		// TODO Auto-generated method stub
		while(flag)
		{
			try {
				Thread.sleep(2500);
				shot();
				showPicture();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	public double  getLength()//得到距离
	{
		int qizi=-13290178;//棋子的大致颜色
		int yinying = -8025430;//阴影的大致颜色
		Graphics g = bi.createGraphics();
		int qiziFlag=0;
		int k=0;
		int qiziX=0,qiziY=0;
		int color=0;//保存上一个像素的颜色
		int boardX=0,boardY=0;
		int qiSum=0;
		
		outer:
		for(int j=bi.getHeight()/6+30;j<bi.getHeight();j++)
		{
			for(int i=0;i<width;i++)
			{
				int c1=bi.getRGB(i, j);
				//棋子位置
				if(judge(c1,qizi)<5&&qiziFlag==0)
				{
					qiziX=i;
					qiziY=j+55;
					qiziFlag=1;
					if(boardX!=0)
					{
						if((Math.abs(qiziX-boardX)<30
								||Math.abs(qiziY-boardY)<20)||
								(Math.abs(qiziX-boardX)>220
								||Math.abs(qiziY-boardY)>220))
							qiziFlag=0;
					}
					if(k==2&&qiziFlag==1)
					{
						break outer;
					}
					
				}
				
				if(i>2)
				{
					
					if(k==0&&judge(c1,color)>3)
					{
						if(judge(qizi,bi.getRGB(i, j))<10)
						{
							i+=30;
						}
						else 
						{
							k=1;
							boardX=i;boardY=j;
						}
						
						
					}else if(k==1&&i<boardX)
					{
						
						if(j+10<bi.getHeight()&&judge(c1,bi.getRGB(i-1, j))>5
								&&judge(c1,bi.getRGB(i, j+1))>5
								&&judge(bi.getRGB(i, j+10), yinying)<20
								&&judge(c1,bi.getRGB(i, j-5))>5
								&&judge(qizi,bi.getRGB(i, j))>10)
						{
							picture.x1=boardX;
							picture.y1=j-15;
							boardY=j;
//							System.out.println("board:"+boardX+" "+j);
							
							k=2;
							if(k==2&&qiziFlag==1)
							{
								break outer;
							}
						}
						
					}
				}
				color=c1;

			}
		}
		picture.x0=qiziX;
		picture.y0=qiziY;
		boardY-=15;
		return Math.sqrt((qiziX-boardX)*(qiziX-boardX)+
				(qiziY-boardY)*(qiziY-boardY));
	}
	public double  getLength2()//得到距离
	{
		int qizi=-13290178;//棋子的大致颜色
		int yinying = -8025430;//阴影的大致颜色
		Graphics g = bi.createGraphics();
		int qiziFlag=0;
		int k=0;
		int qiziX=0,qiziY=0;
		int color=0;//保存上一个像素的颜色
		int boardX=0,boardY=0;
		int qiSum=0;
		
		outer:
		for(int j=bi.getHeight()/6+30;j<bi.getHeight();j++)
		{
			for(int i=0;i<width;i++)
			{
				int c1=bi.getRGB(i, j);
				//棋子位置
				if(judge(c1,qizi)<1&&qiziFlag==0)
				{
					qiziX=i;
					qiziY=j+55;
					qiziFlag=1;
					if(boardX!=0)
					{
						if((Math.abs(qiziX-boardX)<20
								||Math.abs(qiziY-boardY)<20)||
								(Math.abs(qiziX-boardX)>220
								||Math.abs(qiziY-boardY)>220))
							qiziFlag=0;
					}
					if(k==2&&qiziFlag==1)
					{
						break outer;
					}
					
				}
				
				if(i>2)
				{
					
					if(k==0&&judge(c1,color)>3)
					{
						if(judge(qizi,bi.getRGB(i, j))<5)
						{
							i+=10;
						}
						else 
						{
							k=1;
							boardX=i;boardY=j;
						}
						
						
					}else if(k==1&&i<boardX)
					{
						
						if(j+10<bi.getHeight()&&judge(c1,bi.getRGB(i-1, j))>5
								&&judge(c1,bi.getRGB(i, j+1))>5
								&&judge(bi.getRGB(i, j+10), yinying)<20
								&&judge(c1,bi.getRGB(i, j-5))>5
								&&judge(qizi,bi.getRGB(i, j))>10)
						{
							picture.x1=boardX;
							picture.y1=j-15;
							boardY=j;
							System.out.println("board:"+boardX+" "+j);
							
							k=2;
							if(k==2&&qiziFlag==1)
							{
								break outer;
							}
						}
						
					}
				}
				color=c1;

			}
		}
		picture.x0=qiziX;
		picture.y0=qiziY;
		boardY-=15;
		return Math.sqrt((qiziX-boardX)*(qiziX-boardX)+
				(qiziY-boardY)*(qiziY-boardY));
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
		try {
			
			bi = robot.createScreenCapture(screenRect);
			picture.bf=bi;
			double length = this.getLength();
			if(length==0.0)
			{
				System.out.println("over");
			}
			robot.mouseMove(100, 100);
			double a = Double.parseDouble(jtf.getText().trim());
			robot.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep((int)(a*length));
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
//			robot.mouseMove(10, 100);
//			robot.mousePress(InputEvent.BUTTON1_MASK);
//		
//			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		//	System.out.println((int)length);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showPicture()
	{
		picture.repaint();
		picture.setVisible(true);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
class Picture extends JFrame
{
	BufferedImage bf = null;
	int x0=0,y0=0,x1=0,y1=0;
	public Picture()
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(d);
		this.setLocation(500, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void paint(Graphics g)
	{
		if(bf!=null)
		{	
			Graphics gg = bf.createGraphics();
			gg.setColor(Color.red);
			gg.drawLine(x0, y0, x1, y1);
			gg.fillOval(x0-5, y0-5, 10, 10);
			gg.setColor(Color.blue);
			gg.fillOval(x1-5, y1-5, 10, 10);
			g.drawImage(bf, 0, 0, this.getWidth(), this.getHeight(),this);
		}
	}
	
}
