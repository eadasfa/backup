package com.text;

public class Thread2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dog dog=new Dog();
		Thread t = new Thread(dog);
		t.start();
	}

}
class Dog implements Runnable
{
	int times = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
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
