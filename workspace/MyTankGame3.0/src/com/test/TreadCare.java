package com.test;

public class TreadCare {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//只能启动一次
//		Cat cat1=new Cat();
//		cat1.start();
//		cat1.start();
		
//		Dog dog = new Dog();
//		Thread t= new Thread(dog);
//		t.start();
//		t.start();
		
		
	}

}
class Cat extends Thread
{
	public void run()
	{
		System.out.println("11");
	}
}
class Dog implements Runnable
{
	public void run()
	{
		System.out.println("22");
	}
}
