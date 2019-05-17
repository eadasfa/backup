/*
 * 1.����һ�����ü����ƶ���̹��
 * 2.��������̹��
 * 3.�����ӵ�
 * 4.�����ܶ����ӵ�
 * 5.���ӵ���������̹��ʱ������ը�٣���ըЧ����
 * 6.��ֹ����̹���ص��˶�
 *   6.1�������ж��Ƿ���ײ�ĺ���д��enemytank
 * 7.���Էֹ�
 *   7.1��һ����ʼ��panelll
 *   7.2��˸
 * 8.��������ʱ��ͣ�ͼ���
 *    ���û������ͣʱ���ӵ��ٶȡ�
 *    ̹���ٶ���Ϊ0������̹�˷��򲻱�
 * 9���Լ�¼�ɼ�
 *  9.1���ļ���
 *  9.2��дһ����¼�࣬��ɶ���ҵļ�¼
 *  9.3����ɱ��湲���ٶ��ٵ���̹��
 * 10.java��������
 */
package com.qi;
import java.awt.*; 
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
public class MoveTank extends JFrame implements ActionListener{

	MyPanel2 mp=null;
	//����һ����ʼ��塢
	MyStartPanel msp = null;
	//��������Ҫ�Ĳ˵�
	
	JMenuBar jmb = null;
	//��ʼ��Ϸ
	JMenu jm1 = null;
	JMenuItem jmi1=null;
	//�˳�ϵͳ
	JMenuItem jmi2 =null;
	//�ָ�
	JMenuItem jmi3 =null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MoveTank mt=new MoveTank();
	}

	//���캯��
	public MoveTank()
	{
		//panel��С
		
		//mp = new MyPanel2();
		//  �����߳�
		//Thread t=new Thread(mp);
		//t.start();
		//this.add(mp);
		//ע�����
		//this.addKeyListener(mp);
		//�����˵���ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ��G��");
		//���ÿ�ݷ�ʽ
		jm1.setMnemonic('G');
		jmi1 = new JMenuItem("��ʼ����Ϸ��N��");
		//��JMI1��Ӧ
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		//��JMI2��Ӧ
		jmi2 = new JMenuItem("�˳���Ϸ��E��");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		//���ÿ�ݷ�ʽ
		jmi2.setMnemonic('E');
		//��JMI2��Ӧ
		jmi3 = new JMenuItem("�ָ���һ�֣�R��");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("recover");
		//���ÿ�ݷ�ʽ
		jmi3.setMnemonic('R');
		
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jmb.add(jm1);
		
		msp = new MyStartPanel();
		this.add(msp);
		Thread t=new Thread(msp);
		t.start();
		
		this.setJMenuBar(jmb);
		this.setSize(600,500);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//���û���ͬ�ĵ��������ͬ����
		if(arg0.getActionCommand().equals("newgame")){
			//panel��С
			
			mp = new MyPanel2("newgame");
			//  �����߳�
			Thread t=new Thread(mp);
			t.start();
			//��ɾ���͵����
			this.remove(msp);
			this.add(mp);
			//ע�����
			this.addKeyListener(mp);
			//ˢ�£���ʾ
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
		}
		else if(arg0.getActionCommand().equals("exit")){
			//�û�����˳�
			//�����������
			Recorder rd = new Recorder();
			rd.setEts(mp.enemy);
			Recorder.keepRecording();
			System.exit(0);
		}else if(arg0.getActionCommand().equals("recover")){
			//�û�����˳�
			//�����������
			//panel��С
			
			mp = new MyPanel2("recorver");
			
			
			//  �����߳�
			Thread t=new Thread(mp);
			t.start();
			//��ɾ���͵����
			this.remove(msp);
			this.add(mp);
			//ע�����
			this.addKeyListener(mp);
			//ˢ�£���ʾ
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			
		}
	}
}
class MyStartPanel extends JPanel implements Runnable
{
	
	int times=0;
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//��ʾ��Ϣ
		//��������
		if(times%2==0){
			g.setColor(Color.yellow);
			Font myFont = new Font("������κ",Font.BOLD ,30);
			g.setFont(myFont);
			g.drawString("stage:1", 150, 125);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			
			//�ػ�
			this.repaint();
		}
	}
}
class MyPanel2 extends JPanel implements KeyListener,Runnable
{
	
	//״̬ trueΪ���� false Ϊ��ͣ
	private boolean state = true;
	
	//�ж������Ͼ仹������Ϸ
	//�ҵ�̹��
	Hero tk = null;
	//num Ϊ����̹�� ����
	private int num = Recorder.getEnNum();
	Vector<EnemyTank> enemy = new Vector<EnemyTank>();
	Vector<Node> nodes = new Vector<Node>();
	//��������ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	Vector<Bomb> bombs = new Vector<Bomb>();
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public MyPanel2(String flag){

		
		tk = new Hero(200,170,0,5);
		if(flag.equals("newgame")){
		//��ʼ������̹��
			for(int i=0;i<this.num;i++)
			{
				//��������̹��
				EnemyTank em = new EnemyTank((i+1)*50,15,1,1);
				this.enemy.add(em);
				//����ǰ״̬����̹��
				em.setState(this.isState());
				//��MyPanel�ĵ���̹�˵�������������̹��
				em.setEts(enemy);
				//��������̹��
				Thread t = new Thread(em);
				//�����������ӵ�
				Bullet s = new Bullet(em.getX()-2,em.getY()+16,1);
				//����̹��
				em.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				t.start();
			}
		}
		else{
			nodes=new Recorder().getRecording();
			for(int i=0;i<nodes.size();i++)
			{
				Node node = nodes.get(i);
				//��������̹��
				EnemyTank em = new EnemyTank(node.x,node.y,node.direct,1);
				this.enemy.add(em);
				//����ǰ״̬����̹��
				em.setState(this.isState());
				//��MyPanel�ĵ���̹�˵�������������̹��
				em.setEts(enemy);
				//��������̹��
				Thread t = new Thread(em);
				//�����������ӵ�
				Bullet s = new Bullet(em.getX()-2,em.getY()+16,1);
				//����̹��
				em.ss.add(s);
				Thread t2=new Thread(s);
				t2.start();
				t.start();
			}
		}
		//����ͼƬ���һ��ը��
		try{
			//�����ļ��Ƿ���ڣ�
//			 File file=new File("bomb_2.gif");
//             	System.out.println(file.exists());
             //�ļ�ǰ����·��ֻ����ͬһ������
			image1=ImageIO.read(new File("bomb_1.gif"));
			image2=ImageIO.read(new File("bomb_2.gif"));
			image3=ImageIO.read(new File("bomb_3.gif"));
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		//��������
		PlaySounds ps = new PlaySounds("D:\\MyEclipse\\wokespace\\8564.wav");
		ps.start();
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
	}
	//������ʾ��Ϣ
	public void showInfo(Graphics g){
		//������ʾ��Ϣ̹�ˣ���̹�˲�����ս��
		this.drawTank(60, 320, g, 1, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"",75 , 325);
		
		this.drawTank(110, 320, g, 0, 0);
		g.setColor(Color.black);
		if(Recorder.getMyLife()<0)
			g.drawString((Recorder.getMyLife()+1)+"",125 , 325);
		else 
			g.drawString(Recorder.getMyLife()+"",125 , 325);
        //������ҳɼ�
		g.setColor(Color.black);
		Font f = new Font("����",Font.BOLD,20);
		g.setFont(f);
		g.drawString("�����ܳɼ�", 420, 30);
		this.drawTank(420, 60, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 435, 67);
	}
	public void paint(Graphics g){

		super.paint(g);
		showInfo(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 300);
		
		//�����Լ�̹��
		if(tk.isLive)
		{
			this.drawTank(tk.getX(), tk.getY(), g, 0, this.tk.getDirect());
		}
		
		for(int i=0;i<bombs.size();i++)
		{   
			Bomb b=bombs.get(i);
			if(b.life>6)
			{
				
				g.drawImage(image1, b.getX(), b.getY(), 30, 30,this);
			}else if(b.life>3)
			{
			
				g.drawImage(image2, b.getX(), b.getY(), 30, 30,this);
			}else
			{
				
				g.drawImage(image3, b.getX(), b.getY(), 30, 30,this);
			}
			//�� b ��life��С
			b.lifeDown();
			//���ը������Ϊ��ͽ���ը��ɾ��
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		//��������̹��
		for(int i=0;i<this.enemy.size();i++)
		{
			EnemyTank em = this.enemy.get(i);
			if(em.isLive)
			{
				this.drawTank(em.getX(), em.getY(), g, 1, em.getDirect());
				//�ڻ��������ӵ�
				for(int j=0;j<em.ss.size();j++)
				{
					
					//ȡ���ӵ�
					
					Bullet enemyBullet=em.ss.get(j);
					if(enemyBullet.isLive)
					{
						g.drawOval(enemyBullet.getX(), enemyBullet.getY(), 4, 4);
					}else
					{
						//�������̹��������ɾ��
						em.ss.remove(enemyBullet);
					}
				}
			}	
		}
		
		//�����Լ��ӵ�
		for(int i=0;i<tk.ss.size();i++)
		{
			Bullet s = tk.ss.get(i);
		    g.setColor(Color.CYAN);
			if(!s.isLive)
			{
				tk.ss.remove(s);
			}else if(s!=null)
			{
				g.drawOval(s.getX(), s.getY(), 4, 4);
			}
			
		}
	}
	//�жϵ��˵��ӵ��Ƿ������
		public void hitMe()
		{
			//ȡ��ÿһ��̹��
			for(int i=0;i<enemy.size();i++)
			{
				EnemyTank et = enemy.get(i);
				//ȡ���ӵ�
				for(int j=0;j<et.ss.size();j++)
				{
					Bullet s=et.ss.get(j);
					if(s.isLive)
					{
						if(tk.isLive)
						{
							this.hitTank(s, tk);
							if(tk.isLive==false&&Recorder.getMyLife()>=0)
								Recorder.reduceMyLife();
						}else{
							if(Recorder.getMyLife()>=0){
								tk.setX(200);
								tk.setY(170);
								tk.setDirect(0);
								tk.isLive = true;
							}
						}
					}
				}
			}
		}
	//�ж��Լ��ӵ��Ƿ���е���
	public void hitEnemy()
	{
		for(int i=0;i<tk.ss.size();i++)
		{
			Bullet s=tk.ss.get(i);
			if(s.isLive)
			{
				for(int j=0;j<enemy.size();j++)
				{
					EnemyTank et=enemy.get(j);
					if(et.isLive)
					{
						this.hitTank(s, et);
						
						if(et.isLive==false){
							//���ٵ�������
							Recorder.reduceEnNum();
							//���ӷ���
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	// �ж��ӵ��Ƿ����̹��
	public void hitTank(Bullet s,Tank et)
	{
		switch(et.getDirect())
		{
		//�������̹�����ϻ�����
		case 0:
		case 1:
			if(s.getX()<et.getX()+10&&
			   s.getX()>et.getX()-10&&
			   s.getY()<et.getY()+15&&
			   s.getY()>et.getY()-15)
				//����
				//�ӵ�����  
			{
				s.isLive=false;
			    //�з�����
				et.isLive=false;
				
				
				//����һ��ը��
				
				Bomb b=new Bomb(et.getX()-10,et.getY()-15);
				bombs.add(b);	
				et.setX(-30);
				et.setY(-30);
			}
			break;
		case 2:
		case 3:
			if(s.getX()<et.getX()+15&&
			s.getX()>et.getX()-15&&
			s.getY()<et.getY()+10&&
			s.getY()>et.getY()-10)
			{
				
				s.isLive=false;
				et.isLive=false;
				
				//����һ��ը��
				Bomb b=new Bomb(et.getX()-15,et.getY()-10);
				bombs.add(b);
				et.setX(-30);
				et.setY(-30);
			}
			break;
		}
		
		
	}

	//��̹��
	public void drawTank(int x,int y,Graphics g,int type,int direct)
	{
		switch(type)
		{
		case 0:
			g.setColor(Color.CYAN);
			break;
		case 1:
			g.setColor(Color.YELLOW);
		}
		switch(direct)
		{
		//up
		case 0:
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x-10, y-15, 5, 30,false);
			//�����ұߵľ���
			g.fill3DRect(x+5, y-15, 5, 30,false);
			//�����м����
			g.fill3DRect(x-5, y-10, 10, 20,false);
			//����Բ
			g.fillOval(x-5, y-5, 10, 10);
			//����
			g.drawLine(x, y, x, y-15);
			break;
		//down
		case 1:
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x-10, y-15, 5, 30,false);
			//�����ұߵľ���
			g.fill3DRect(x+5, y-15,5, 30,false);
			//�����м����
			g.fill3DRect(x-5, y-10, 10, 20,false);
			//����Բ
			g.fillOval(x-5, y-5, 10, 10);
			//����
			g.drawLine(x, y, x, y+15);
			break;
		//left
		case 2:
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x-15, y-10, 30, 5,false);
			//�����ұߵľ���
			g.fill3DRect(x-15, y+5, 30, 5,false);
			//�����м����
			g.fill3DRect(x-10, y-5, 20, 10,false);
			//����Բ
			g.fillOval(x-5, y-5, 10, 10);
			//����
			g.drawLine(x, y, x-15, y);
			break;
		//right
		case 3:
			//��̹��(��ʱ�ٷ�װ��һ������)
			//1.��������ľ���
			g.fill3DRect(x-15, y-10, 30, 5,false);
			//�����ұߵľ���
			g.fill3DRect(x-15, y+5, 30, 5,false);
			//�����м����
			g.fill3DRect(x-10, y-5, 20, 10,false);
			//����Բ
			g.fillOval(x-5, y-5, 10, 10);
			//����
			g.drawLine(x, y, x+15, y);
			break;
		}
	}
	
	
	//@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//��ͣ�뿪ʼ
				if(e.getKeyCode()==KeyEvent.VK_SPACE){
					if(this.isState()==true){
						for(int i=0;i<enemy.size();i++){
							EnemyTank et=enemy.get(i);
							et.setSpeed(0);
							for(int j=0;j<et.ss.size();j++){
								Bullet s = et.ss.get(j);
								s.setSpeed(0);
							}
							et.setState(false);
						}
						this.tk.setSpeed(0);
						for(int j=0;j<tk.ss.size();j++){
							Bullet s = tk.ss.get(j);
							s.setSpeed(0);
						}
						this.setState(false);
					}else{
						this.tk.setSpeed(5);
						for(int j=0;j<tk.ss.size();j++){
							Bullet s = tk.ss.get(j);
							s.setSpeed(1);
						}
						for(int i=0;i<enemy.size();i++){
							EnemyTank et=enemy.get(i);
							et.setSpeed(1);
							for(int j=0;j<et.ss.size();j++){
								Bullet s = et.ss.get(j);
								s.setSpeed(1);
							}
							et.setState(true);
						}
						this.setState(true);
					}
				}
			if(e.getKeyCode()==KeyEvent.VK_S)
			{
//				this.x0 = tk.getX()+13;
//				this.y0 = tk.getY()+30;
				//�ж��Լ�̹��������̹���Ƿ��ص�
				if(this.isState()==true){
					this.tk.setDirect(1);
					boolean isdouble=true;
					for(int i=0;i<enemy.size();i++){
						EnemyTank et=enemy.get(i);
						if(!tk.isDouble(et))
							isdouble = false;
					}
					if(isdouble)
					{
						this.tk.moveDown();
					}
				}
			}else if(e.getKeyCode()==KeyEvent.VK_W)
			{
//				this.x0 = tk.getX()+13;
//				this.y0 = tk.getY();
				if(this.isState()==true){
					this.tk.setDirect(0);
					boolean isdouble=true;
					for(int i=0;i<enemy.size();i++){
						EnemyTank et=enemy.get(i);
						if(!tk.isDouble(et))
							isdouble = false;
					}
					if(isdouble)
					{
						this.tk.moveUp();
					}
				}
			}else if(e.getKeyCode()==KeyEvent.VK_A)
			{
//				this.x0 = tk.getX();
//				this.y0 = tk.getY()+13;
				if(this.isState()==true){
					this.tk.setDirect(2);
					boolean isdouble=true;
					for(int i=0;i<enemy.size();i++){
						EnemyTank et=enemy.get(i);
						if(!tk.isDouble(et))
							isdouble = false;
					}
					if(isdouble)
					{
						this.tk.moveLeft();
					}
				}
			}else if(e.getKeyCode()==KeyEvent.VK_D)
			{
//				this.x0 = tk.getX()+30;
//				this.y0 = tk.getY()+13;
				if(this.isState()==true){
					this.tk.setDirect(3);
					boolean isdouble=true;
					for(int i=0;i<enemy.size();i++){
						EnemyTank et=enemy.get(i);
						if(!tk.isDouble(et))
							isdouble = false;
					}
					if(isdouble)
					{
						this.tk.moveRight();
					}
				}
			}
			//tank�ı��ٶ�
			if(e.getKeyCode()==KeyEvent.VK_UP){
				if(this.isState()==true){
					int i = this.tk.getSpeed();
					this.tk.setSpeed(i+1);
				}
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				if(this.isState()==true){
					int i = this.tk.getSpeed();
					if(i-1>=0)
						this.tk.setSpeed(i-1);
				}
			}
			//����J���ӵ�
			if(e.getKeyCode()==KeyEvent.VK_J)
			{
				if(this.isState()==true)
					if(tk.ss.size()<15)
					{
						this.tk.shotEnemy();
					}
			}
			
			//�ı��ӵ��ٶ�
			if(this.tk.s!=null&&this.tk.s.isLive)
			{
				if(this.isState()==true){
					if(e.getKeyCode()==KeyEvent.VK_LEFT){
						
						this.tk.s.setSpeed(this.tk.s.getSpeed()+1);
					}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
						int i = this.tk.s.getSpeed();
						if(i-1>0)
							this.tk.s.setSpeed(i-1);
					}
				}
				
			}
			
			//����repaint()�������ػ�
			this.repaint();
			
			
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {

		// TODO Auto-generated method stub
		//ÿ��100ms�ػ�
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//�ж��Ƿ����
			this.hitEnemy();
			this.hitMe();
			//�ػ�
			this.repaint();
			
		}
	}
}






