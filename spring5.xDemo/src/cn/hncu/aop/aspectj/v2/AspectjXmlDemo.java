package cn.hncu.aop.aspectj.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hncu.aop.aspectj.v2.domian.Person;

/* 演示使用applicationContext的方式
 */
public class AspectjXmlDemo {
	
	public static void main(String[] args) {
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/aop/aspectj/v2/applicationContext.xml");
		Person person = appCtx.getBean(Person.class);
		person.run();
		person.eat();
		((AbstractApplicationContext) appCtx).close();
	}
}
