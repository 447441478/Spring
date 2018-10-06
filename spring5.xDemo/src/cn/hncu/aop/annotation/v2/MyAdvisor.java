package cn.hncu.aop.annotation.v2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/* 切面=切点+通知：@Aspect = str + ( @Before | @After | @Around | @AfterReturning | @AfterThrowing )
 */
@Aspect //被该 注解 注解后的类会被识别为'切面' Advisor
public class MyAdvisor {
	//切点语言表达式
	private final String CUT = "execution( * cn.hncu..Person.*( ) )";
	private final String CUT2 = "execution( * cn.hncu..Person.*( * ) )";
	
	
	@Before(value=CUT) 
	public void before() { 
		System.out.println("...before...");
	}
	@Before(value=CUT2) 
	public void before2() { 
		System.out.println("...before...");
	}
	
	@Around(value=CUT)
	public Object around(ProceedingJoinPoint p ) throws Throwable {
		System.out.println("前面拦拦...");
		Object returnValue = p.proceed(); //放行
		System.out.println("后面拦拦...");
		return returnValue;
	}
}
