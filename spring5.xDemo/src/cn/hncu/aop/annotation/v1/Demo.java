package cn.hncu.aop.annotation.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hncu.aop.annotation.domain.Person;

public class Demo {
	
	public static void main(String[] args) {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/aop/annotation/v1/demo.xml");
		Person person = appCtx.getBean( Person.class );
		System.out.println("--------------");
		person.run();
		System.out.println("--------------");
		person.run(5); 
//		person.run(0); //这里演示 @AfterThrowing 注解
		System.out.println("--------------");
		person.eat();
		System.out.println("--------------");
		
		//关流防止内存泄漏
		((AbstractApplicationContext) appCtx).close();
	}
}
