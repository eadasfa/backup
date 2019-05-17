package com.text;

public class Thread1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cat cat=new Cat();
		cat.start();
	}

}
class Cat extends Thread
{
	int times=0;
	public void run()
	{
		while(true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++times;
			System.out.println("Hello,world!"+times);
			if(times==5)
				break;
		}
	}
}

