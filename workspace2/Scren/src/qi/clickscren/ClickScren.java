/**
 *  点击电脑屏幕
 */
package qi.clickscren;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import  java.awt.Robot;
import java.awt.Toolkit;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;




public class ClickScren extends JFrame implements  Runnable{
	static boolean flag=false;
	double a=3.8;
	Robot robot =null;
	static int x=0;
	static int y=0;
	int x0=0;
	int y0=0;
	JLabel jx=null;
	JLabel jy=null;
	JLabel oldX=null;
	JLabel newX=null;
	JLabel state=null;
	JTextField jtf = null;
	JLabel jt =null;
	JLabel ps1 =null;
	JLabel ps2 =null;
	JLabel ps3 =null;
	JLabel ps4 =null;
	public ClickScren(){	
		try {
			robot = new Robot();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.setSize(300, 300);
		this.setResizable(false);
		this.setLocation(900, 300);
		
		this.setLayout(null);
		jx=new JLabel("x:0");
		jx.setBounds(20, 20, 50, 50);
		jy=new JLabel("y:0");
		jy.setBounds(20, 40, 50, 50);
		this.add(jx);
		this.add(jy);
		
		oldX=new JLabel("oldX:(--,--)");
		oldX.setBounds(20, 60, 100, 50);
		newX=new JLabel("newX:(--,--)");
		newX.setBounds(20, 80, 100, 50);
		this.add(oldX);
		this.add(newX);
		ps4 = new JLabel("时间常数:");
		ps4.setBounds(20, 10, 80, 20);
		this.add(ps4);
		jtf= new JTextField(a+"");
		jtf.setBounds(80, 10, 60, 20);
		jtf.setForeground(Color.red);
		this.add(jtf);
		jtf.setEditable(false);
		
		
		
		state=new JLabel("Flag:"+this.flag+"");
		state.setBounds(200, 60, 100, 50);
		this.add(state);
		ps1 = new JLabel("第一步：将鼠标指向棋子位置，按一下空格,");
		ps1.setForeground(Color.red);
		ps1.setBounds(20, 120, 300, 30);
		ps3 = new JLabel("	                                                           "
				+ "Flag变为true");
		ps3.setForeground(Color.red);
		ps3.setBounds(20, 150, 300, 30);
		ps2 = new JLabel("第二步：将鼠标指向要跳到的位置，按一下空格");
		ps2.setForeground(Color.red);
		ps2.setBounds(20, 180, 300, 30);
		this.add(ps1);
		this.add(ps2);
		this.add(ps3);
	
		JIntellitype.getInstance().registerHotKey(0, 0, KeyEvent.VK_SPACE);
		JIntellitype.getInstance().registerHotKey(1, 0, KeyEvent.VK_R);
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			
			public void onHotKey(int arg0) {
				// TODO Auto-generated method stub
				if(arg0==0){
					if(flag)
					{
						newX.setText("newX:("+x+","+y+")");
						double length = Math.sqrt((x-x0)*(x-x0)+(y-y0)*(y-y0));
						double a = Double.parseDouble(jtf.getText().trim());
						robot.mousePress(InputEvent.BUTTON1_MASK);
						try {
							Thread.sleep((int)(a*length));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						flag = false;
						state.setText(flag+"");
					}else
					{
						x0=x;
						y0=y;
						oldX.setText("oldX:("+x+","+y+")");	
						newX.setText("newX(--,--)");
						flag = true;
						state.setText(flag+"");
					}
				}else if(arg0==1)
				{
					flag = false;
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
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Point point = java.awt.MouseInfo.getPointerInfo().getLocation();
    		x=point.x;
    		y=point.y;
    		jx.setText("x:"+x);
    		jy.setText("y:"+y);	
		}
		
		
	}
	public void jtfMouse()
	{
		jtf.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				requestFocusInWindow();
				jtf.setEditable(true);
				jtf.requestFocus();
				jtf.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			}
		});
	}




}
