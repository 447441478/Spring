package cn.hncu.aop.label;

public class Person {
	
	public Person() {
		System.out.println("构造方法...");
	}
	
	public void run() {
		System.out.println("run()......");
	}
	public void run(int i) {
		System.out.println("run(int i)......");
		i=10/i; //为演示@AfterThrowing
	}
	
	public void eat() {
		System.out.println("eat......");
	}
	
}
