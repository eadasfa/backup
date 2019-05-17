package com.test;

public class Tongbu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//只能启动一次

		TicketWindow tw = new TicketWindow();
		Thread t1 =new Thread(tw);
		Thread t2 =new Thread(tw);
		Thread t3 =new Thread(tw);
		
		t1.start();
		t2.start();
		t3.start();
		
		
	}

}
class TicketWindow extends Thread
{
	private int num =2000;
	public void run()
	{
		
		while(true)
		{
			//对象锁
			//synchronized(this)使得一个线程读取此代码块时
			//其他线程等待
			synchronized(this)
			{
				
				if(num>0)
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" 正在卖出第"+num+"票");
					num--;
				}
				else
				{
					break;
				}
			}
		}
	}
}
