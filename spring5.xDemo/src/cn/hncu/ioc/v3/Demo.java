package cn.hncu.ioc.v3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hncu.ioc.v3.domain.Person;

public class Demo {
	public static void main(String[] args) {
		//加载容器
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/ioc/v3/v3.xml");
		
		//获取bean
		Person person = appCtx.getBean( Person.class );
		System.out.println(person);
		//默认类型
		System.out.println(person.getList().getClass()); //java.util.ArrayList
		System.out.println(person.getSet().getClass()); //java.util.LinkedHashSet
		System.out.println(person.getMap().getClass()); //java.util.LinkedHashMap
		
		
		((AbstractApplicationContext) appCtx).close();
	}
}
