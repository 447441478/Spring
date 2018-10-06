package cn.hncu.ioc.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/* 搭建Spring5.0框架最基本需要：
 * 1) 5个jar包: 
 * 		Appache的 commons-logging-1.2.jar
 * 		Spring的  spring-beans-5.0.1.RELEASE.jar
 * 				spring-context-5.0.1.RELEASE.jar
 * 				spring-core-5.0.1.RELEASE.jar
 * 				spring-expression-5.0.1.RELEASE.jar
 * 
 * 2) 需要一个xml文件：
 * 		推荐文件名：'applicationContext.xml' 
 * 		推荐存放路径：ClassPath下
 * 
 * 3) 技术入口：ApplicationContext类
 * 			通过其子类 ClassPathXmlApplicationContext 进行读取xml文件从而加载容器
 */
public class Hello {
	public static void main(String[] args) {
		//1 加载容器
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//通过 观察控制台输出 可以 发现，默认的Bean 是容器一加载 就new出来的。
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//2 从容器中拿出一个Bean
		User user = appCtx.getBean( User.class );
		System.out.println( user.hashCode() );
		
		//观察控制台可以发现 三个User类对象的hashCode是一样的，所以：默认的Bean是单例。
		
		User user2 = appCtx.getBean( User.class );
		System.out.println( user2.hashCode() );
		
		User user3 = appCtx.getBean( User.class );
		System.out.println( user3.hashCode() );
		
		
		((AbstractApplicationContext) appCtx).close();//关流，以防内存泄漏
	}
}
