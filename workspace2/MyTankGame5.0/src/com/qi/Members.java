
package com.qi;
import java.awt.*;

import java.io.*;
import java.util.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.awt.event.*;
//记录点
class Node{
	int x;
	int y;
	int direct;
	public Node(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
}
//纪录类，同时也可以保存玩家的设置
class Recorder
{
	//记录每一关有多少敌人
	private static int enNum = 5;
	//设置我有多少可以用的人
	private static int myLife = 2;
	//记录总共消灭多少敌人
	private static int allEnNum = 0;
	//从文件中恢复记录点
	static Vector<Node> nodes = new Vector<Node>();
	//把玩家击毁敌人坦克数量保存到文件中
	private static FileWriter fw = null;
	private static BufferedWriter bw=null;
	private static FileReader fr = null;
	private static BufferedReader br=null;
	private static Vector<EnemyTank>ets = new Vector<EnemyTank>();
	public Vector<EnemyTank> getEts() {
		return ets;
	}
	public  void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}
	//完成读取
	//从文件中读取记录
	public  Vector<Node> getRecording(){
		try{
			fr = new FileReader("D:\\MyEclipse\\myRecording.txt");
			br = new BufferedReader(fr);
			String n="";
			//现读取第一行
			n=br.readLine();
			allEnNum = Integer.parseInt(n);
			while((n=br.readLine())!=null)
			{
				//split将读取（空格隔开的）存入数组
				String []xyz=n.split(" ");
			
				Node node = new Node(Integer.parseInt(xyz[0]),Integer.parseInt(xyz[1]),Integer.parseInt(xyz[2]));
				
				nodes.add(node);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//后打开
			//先关闭
			try{
				br.close();
				fr.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return nodes;
		
	}
	//保存击毁敌人的数量和坦克坐标，方向
	public static void keepRecording()
	{
		try{
			fw = new FileWriter("D:\\MyEclipse\\myRecording.txt");
			bw = new BufferedWriter(fw);
			bw.write(allEnNum+"\r\n");
			
			//保存当前存在的敌人坦克和方向
			for(int i=0;i<ets.size();i++){
				EnemyTank et=ets.get(i);
				if(et.isLive){
					String recode = et.getX()+" "+et.getY()+" "+et.getDirect();
					bw.write(recode+"\r\n");
				}
					
				for(int j=0;j<et.ss.size();j++){
					Bullet s = et.ss.get(j);
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭流
			//后开先关
			try{
				bw.close();
				fw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//从文件中读取记录
//	public static void keepRecording()
//	{
//		try{
//			fw = new FileWriter("D:\\MyEclipse\\myRecording.txt");
//			bw = new BufferedWriter(fw);
//			bw.write(allEnNum+"\r\n");
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			//关闭流
//			//后开先关
//			try{
//				bw.close();
//				fw.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//	}
	public static int getAllEnNum() {
		return allEnNum;
	}
	public static void setAllEnNum(int allEnNum) {
		Recorder.allEnNum = allEnNum;
	}
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	//减少敌人数
	public static void reduceEnNum(){
		enNum--;
	}
	public static void reduceMyLife(){
		myLife--;
	}
	public static void addEnNumRec()
	{
		allEnNum++;
	}
	
}
class Tank
{	
	boolean isLive = true;
	private int x=0;
	private int y=0;
	//0  up  1 down  2 left 3right
	private int direct=0;
	//speed
	private int speed = 1;
	public Tank(int x,int y,int direct,int speed){
		this.x = x;
		this.y = y;
		
		this.direct = direct;
		this.speed = speed;
	}
	//定义状态
	private boolean state = true;
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	//判断自己前进一步是否与别的坦克重叠
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void moveUp()
	{
			y-=speed;
	}
	public void moveDown()
	{
		y+=speed;
	}
	public void moveLeft()
	{
		x-=speed;
	}
	public void moveRight()
	{
		x+=speed;
	}
	
}
class Hero extends Tank
{

	//子弹向量
	Vector<Bullet> ss = new Vector<Bullet>();

	Bullet s=null;
	//构建函数
	public Hero(int x,int y, int direct,int speed){
		super(x, y,direct,speed);
		
	}
	public boolean isDouble(Tank et)
	{
		if(this.getDirect()==0)
		{
			if(et.getDirect()==0||et.getDirect()==1)
			{
				if(this.getX()-10<et.getX()+10&&
				   this.getX()-10>et.getX()-10&&
				   this.getY()-16<et.getY()+15&&
				   this.getY()-16>et.getY()-15)
				{
					return false;
				}
				if(this.getX()+10<et.getX()+10&&
				   this.getX()+10>et.getX()-10&&
				   this.getY()-16<et.getY()+15&&
				   this.getY()-16>et.getY()-15)
				{
					return false;
				}
			}else if(et.getDirect()==2||et.getDirect()==3)
			{
				if(this.getX()-10<et.getX()+15&&
				   this.getX()-10>et.getX()-15&&
				   this.getY()-16<et.getY()+10&&
				   this.getY()-16>et.getY()-10)
				{
					return false;
				}
				if(this.getX()+10<et.getX()+15&&
				   this.getX()+10>et.getX()-15&&
				   this.getY()-16<et.getY()+10&&
				   this.getY()-16>et.getY()-10)
				{
					return false;
				}
			}
		}else if(this.getDirect()==1)
		{
			if(et.getDirect()==0||et.getDirect()==1)
			{
				if(this.getX()-10<et.getX()+10&&
				   this.getX()-10>et.getX()-10&&
				   this.getY()+16<et.getY()+15&&
				   this.getY()+16>et.getY()-15)
				{
					return false;
				}
				if(this.getX()+10<et.getX()+10&&
				   this.getX()+10>et.getX()-10&&
				   this.getY()+16<et.getY()+15&&
				   this.getY()+16>et.getY()-15)
				{
					return false;
				}
			}else if(et.getDirect()==2||et.getDirect()==3)
			{
				if(this.getX()-10<et.getX()+15&&
				   this.getX()-10>et.getX()-15&&
				   this.getY()+16<et.getY()+10&&
				   this.getY()+16>et.getY()-10)
				{
					return false;
				}
				if(this.getX()+10<et.getX()+15&&
				   this.getX()+10>et.getX()-15&&
				   this.getY()+16<et.getY()+10&&
				   this.getY()+16>et.getY()-10)
				{
					return false;
				}
			}
		}else if(this.getDirect()==2)
		{
			if(et.getDirect()==0||et.getDirect()==1)
			{
				if(this.getY()-10<et.getY()+15&&
				   this.getY()-10>et.getY()-15&&
				   this.getX()-16<et.getX()+10&&
				   this.getX()-16>et.getX()-10)
				{
					return false;
				}
				if(this.getY()+10<et.getY()+15&&
				   this.getY()+10>et.getY()-15&&
				   this.getX()-16<et.getX()+10&&
				   this.getX()-16>et.getX()-10)
				{
					return false;
				}
			}else if(et.getDirect()==2||et.getDirect()==3)
			{
				if(this.getY()-10<et.getY()+10&&
				   this.getY()-10>et.getY()-10&&
				   this.getX()-16<et.getX()+15&&
				   this.getX()-16>et.getX()-15)
				{
					return false;
				}
				if(this.getY()+10<et.getY()+10&&
				   this.getY()+10>et.getY()-10&&
				   this.getX()-16<et.getX()+15&&
				   this.getX()-16>et.getX()-15)
				{
					return false;
				}
			}
		}else if(this.getDirect()==3)
		{
			if(et.getDirect()==0||et.getDirect()==1)
			{
				if(this.getY()-10<et.getY()+15&&
				   this.getY()-10>et.getY()-15&&
				   this.getX()+16<et.getX()+10&&
				   this.getX()+16>et.getX()-10)
				{
					return false;
				}
				if(this.getY()+10<et.getY()+15&&
				   this.getY()+10>et.getY()-15&&
				   this.getX()+16<et.getX()+10&&
				   this.getX()+16>et.getX()-10)
				{
					return false;
				}
			}else if(et.getDirect()==2||et.getDirect()==3)
			{
				if(this.getY()-10<et.getY()+10&&
				   this.getY()-10>et.getY()-10&&
				   this.getX()+16<et.getX()+15&&
				   this.getX()+16>et.getX()-15)
				{
					return false;
				}
				if(this.getY()+10<et.getY()+10&&
				   this.getY()+10>et.getY()-10&&
				   this.getX()+16<et.getX()+15&&
				   this.getX()+16>et.getX()-15)
				{
					return false;
				}
			}
		}
		return true;
	}
	public void shotEnemy()
	{
		
		switch(this.getDirect())
		{
		//UP
		case 0:
			s = new Bullet(this.getX()-2,this.getY()-16,0);
			ss.add(s);
			break;
		//DOWN
		case 1:
			s = new Bullet(this.getX()-2,this.getY()+16,1);
			ss.add(s);
			break;
		//left
		case 2:
			s = new Bullet(this.getX()-16,this.getY()-2,2);
			ss.add(s);
			break;
		//right
		case 3:
			s = new Bullet(this.getX()+16,this.getY()-2,3);
			ss.add(s);
			break;
		}
		//启动子弹
		Thread t=new Thread(s);
		t.start();
	}
	
}
class EnemyTank extends Tank implements Runnable
{

	
	//时间变量，用于发射子弹
	int times=0;
	//定义一个向量可以访问到所有敌人坦克
	
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	//定义向量，存放敌人tank
	Vector<Bullet> ss = new Vector<Bullet>();
	//敌人添加子弹，刚创建与己方子弹死亡
	Bullet s=null;
	public EnemyTank(int x,int y, int direct,int speed){
		super(x, y,direct,speed);
		
	}
	//得到敌人坦克数量
	public void setEts(Vector<EnemyTank> vv)
	{
		this.ets = vv;
	}

	//判断是否碰到别人的坦克
	public boolean isDouble(){
		
		boolean b= true;
		
		switch(this.getDirect())
		{
		case 0:
			//我的坦克向上
			//取出所有敌人坦克
			for(int i=0;i<ets.size();i++)
			{
				//取出tank
				EnemyTank et = ets.get(i);
				if(et!=this)
				{
					if(et.getDirect()==0||et.getDirect()==1)
					{
						if(this.getX()-10<=et.getX()+10&&
						   this.getX()-10>=et.getX()-10&&
						   this.getY()-16<=et.getY()+15&&
						   this.getY()-16>=et.getY()-15)
						{
							b=false;
						}
						if(this.getX()+10<=et.getX()+10&&
						   this.getX()+10>=et.getX()-10&&
						   this.getY()-16<=et.getY()+15&&
						   this.getY()-16>=et.getY()-15)
						{
							b=false;
						}
					}else if(et.getDirect()==2||et.getDirect()==3)
					{
						if(this.getX()-10<=et.getX()+15&&
						   this.getX()-10>=et.getX()-15&&
						   this.getY()-16<=et.getY()+10&&
						   this.getY()-16>=et.getY()-10)
						{
							b=false;
						}
						if(this.getX()+10<=et.getX()+15&&
						   this.getX()+10>=et.getX()-15&&
						   this.getY()-16<=et.getY()+10&&
						   this.getY()-16>=et.getY()-10)
						{
							b=false;
						}
					}
				}
			}
			break;
		case 1:
			for(int i=0;i<ets.size();i++)
			{
				//取出tank
				EnemyTank et = ets.get(i);
				if(et!=this)
				{
					if(et.getDirect()==0||et.getDirect()==1)
					{
						if(this.getX()-10<=et.getX()+10&&
						   this.getX()-10>=et.getX()-10&&
						   this.getY()+16<=et.getY()+15&&
						   this.getY()+16>=et.getY()-15)
						{
							b=false;
						}
						if(this.getX()+10<=et.getX()+10&&
						   this.getX()+10>=et.getX()-10&&
						   this.getY()+16<=et.getY()+15&&
						   this.getY()+16>=et.getY()-15)
						{
							b=false;
						}
					}else if(et.getDirect()==2||et.getDirect()==3)
					{
						if(this.getX()-10<=et.getX()+15&&
						   this.getX()-10>=et.getX()-15&&
						   this.getY()+16<=et.getY()+10&&
						   this.getY()+16>=et.getY()-10)
						{
							b=false;
						}
						if(this.getX()+10<=et.getX()+15&&
						   this.getX()+10>=et.getX()-15&&
						   this.getY()+16<=et.getY()+10&&
						   this.getY()+16>=et.getY()-10)
						{
							b=false;
						}
					}
				}
					
			}
			break;
		case 2:
			for(int i=0;i<ets.size();i++)
			{
				//取出tank
				EnemyTank et = ets.get(i);
				if(et!=this)
				{
					if(et.getDirect()==0||et.getDirect()==1)
					{
						if(this.getY()-10<=et.getY()+15&&
						   this.getY()-10>=et.getY()-15&&
						   this.getX()-16<=et.getX()+10&&
						   this.getX()-16>=et.getX()-10)
						{
							b=false;
						}
						if(this.getY()+10<=et.getY()+15&&
						   this.getY()+10>=et.getY()-15&&
						   this.getX()-16<=et.getX()+10&&
						   this.getX()-16>=et.getX()-10)
						{
							b=false;
						}
					}else if(et.getDirect()==2||et.getDirect()==3)
					{
						if(this.getY()-10<=et.getY()+10&&
						   this.getY()-10>=et.getY()-10&&
						   this.getX()-16<=et.getX()+15&&
						   this.getX()-16>=et.getX()-15)
						{
							b=false;
						}
						if(this.getY()+10<=et.getY()+10&&
						   this.getY()+10>=et.getY()-10&&
						   this.getX()-16<=et.getX()+15&&
						   this.getX()-16>=et.getX()-15)
						{
							b=false;
						}
					}
				}
			}
			break;
		case 3:
			for(int i=0;i<ets.size();i++)
			{
				//取出tank
				EnemyTank et = ets.get(i);
				if(et!=this)
				{
					if(et.getDirect()==0||et.getDirect()==1)
					{
						if(this.getY()-10<=et.getY()+15&&
						   this.getY()-10>=et.getY()-15&&
						   this.getX()+16<=et.getX()+10&&
						   this.getX()+16>=et.getX()-10)
						{
							b=false;
						}
						if(this.getY()+10<=et.getY()+15&&
						   this.getY()+10>=et.getY()-15&&
						   this.getX()+16<=et.getX()+10&&
						   this.getX()+16>=et.getX()-10)
						{
							b=false;
						}
					}else if(et.getDirect()==2||et.getDirect()==3)
					{
						if(this.getY()-10<=et.getY()+10&&
						   this.getY()-10>=et.getY()-10&&
						   this.getX()+16<=et.getX()+15&&
						   this.getX()+16>=et.getX()-15)
						{
							b=false;
						}
						if(this.getY()+10<=et.getY()+10&&
						   this.getY()+10>=et.getY()-10&&
						   this.getX()+16<=et.getX()+15&&
						   this.getX()+16>=et.getX()-15)
						{
							b=false;
						}
					}
				}
			}
			break;
		}
		return b;
	}
	public void run() {
		while(true)
		{	
			switch(this.getDirect())
			{
			case 0:
				//说明坦克正在向上移动
				for(int i=0;i<30;i++)
				{
					if(this.getY()>15&&this.isDouble())
					{
						this.setY(this.getY()-this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 1:
				//说明坦克正在向下移动
				
				for(int i=0;i<30;i++)
				{
					if(this.getY()<285&&this.isDouble())
					{
						this.setY(this.getY()+this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 2:
				//说明坦克正在向左移动
				for(int i=0;i<30;i++)
				{
					if(this.getX()>15&&this.isDouble())
					{
						this.setX(this.getX()-this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			case 3:
				//说明坦克正在向右移动
				for(int i=0;i<30;i++)
				{
					if(this.getX()<385&&this.isDouble())
					{
						this.setX(this.getX()+this.getSpeed());
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			}
			times++;
			//判断是否给坦克加新的子弹
			if(times%2==0&&isState())
			{
				
				if(ss.size()<5)
				{
					
					Bullet s = null;
					switch(this.getDirect())
					{
					case 0:
						
						s = new Bullet(this.getX()-2,this.getY()-16,0);
						this.ss.add(s);
						break;
					//DOWN
					case 1:
						s = new Bullet(this.getX()-2,this.getY()+16,1);
						this.ss.add(s);
						break;
					//left
					case 2:
					    s = new Bullet(this.getX()-16,this.getY()-2,2);
					    this.ss.add(s);
					    break;
					//right
					case 3:
						s = new Bullet(this.getX()+16,this.getY()-2,3);
						this.ss.add(s);
						break;
					}
					//启动子弹线程
					Thread t = new Thread(s);
					t.start();
				}
			}
			
			//让坦克随机产生一个新的方向
			if(this.isState()==true)
				this.setDirect((int)(Math.random()*4));
			//判断敌人是否死亡
			if(this.isLive==false)
			{
				break;
			}
		}
		
		
	}
}
class Bullet implements Runnable
{
	
	
	private int x=0;
	private int y=0;
	private int derect;
	private int speed=2;
	boolean isLive=true;
	public Bullet(int x,int y,int direct)
	{
		this.x = x;
		this.y = y;
		
		this.derect = direct;
	}
	public void run() {
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(this.getDerect())
			{
			case 0:
				y-=this.getSpeed();
				break;
			case 1:
				y+=this.getSpeed();
				break;
			case 2:
				x-=this.getSpeed();
				break;
			case 3:
				x+=this.getSpeed();
				break;
			}
			//子弹何时死亡
			//判断子弹是否碰到边缘
			if(x<0||x>400||y<0||y>300)
			{
				this.isLive=false;
				break;
			}
		}
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDerect() {
		return derect;
	}
	public void setDerect(int derect) {
		this.derect = derect;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
class Bomb
{
	private int x;
	private int y;
	int life=9;
	boolean isLive=true;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//减少生命
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}
		else
		{
			this.isLive=false;
		}
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
//播放声音的类
class PlaySounds extends Thread {

	 private String filename;
	 public PlaySounds(String wavfile) {

	  filename = wavfile;
	 }
	 public void run() {

	  File soundFile = new File(filename);

	  AudioInputStream audioInputStream = null;
	  try {
	   audioInputStream = AudioSystem.getAudioInputStream(soundFile);
	  } catch (Exception e1) {
	   e1.printStackTrace();
	   return;
	  }
	  AudioFormat format = audioInputStream.getFormat();
	  SourceDataLine auline = null;
	  DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
	  try {
	   auline = (SourceDataLine) AudioSystem.getLine(info);
	   auline.open(format);
	  } catch (Exception e) {
	   e.printStackTrace();
	   return;
	  }
	  auline.start();
	  int nBytesRead = 0;
	  //这是缓冲
	  byte[] abData = new byte[512];
	  try {
	   while (nBytesRead != -1) {
	    nBytesRead = audioInputStream.read(abData, 0, abData.length);
	    if (nBytesRead >= 0)
	     auline.write(abData, 0, nBytesRead);
	   }
	  } catch (IOException e) {
	   e.printStackTrace();
	   return;
	  } finally {
	   auline.drain();
	   auline.close();
	  }
	 } 
	}




