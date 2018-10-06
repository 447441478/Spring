package cn.hncu.aop.hello.v2.domain;

public class Person {
	
	public Person() {
		System.out.println("构造方法...");
	}
	
	public void run() {
		System.out.println("run......");
	}
	
	public void eat() {
		System.out.println("eat......");
	}
	public void run(int i) {
		System.out.println("run..."+i+"...");
	}
}
