package cn.hncu.aop.label;

import org.aspectj.lang.ProceedingJoinPoint;
/* 采用label方式使 java类最多只依赖 org.aspectj.lang.ProceedingJoinPoint
 * 如果把该类无视掉的话，该类就是一个纯粹的POJO(普通的java对象)。
 * 完全看不出来该类是一个切面。
 */
public class HelloWorld {
	
	public void hello() {
		System.out.println("Hello world!");
	}
	
	public void say() {
		System.out.println("say...");
	}
	
	public Object around( ProceedingJoinPoint p ) throws Throwable {
		
		System.out.println("前面拦拦...");
		Object returnValue = p.proceed(); //放行
		System.out.println("后面拦拦...");
		
		return returnValue;
	}
}
