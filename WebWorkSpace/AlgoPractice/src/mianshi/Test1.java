package mianshi;

public class Test1 {
	public static void main(String[] args) {
		A a = new B();
	}
}
class A{
	public static void prt() {
		System.out.println("1");
	}
	public A() {
		System.out.println("A");
	}
}
class B extends A{
	public static void prt() {
		System.out.println("2");
	}
	public B() {
		System.out.println("B");
	}
}
