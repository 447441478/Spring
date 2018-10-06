package cn.hncu.aop.annotation.v1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/* 切面=切点+通知：@Aspect = @Pointcut + ( @Before | @After | @Around | @AfterReturning | @AfterThrowing )
 */
@Aspect //被该 注解 注解后的类会被识别为'切面' Advisor
public class MyAdvisor {
	
	//拦截Person类中的所有空参方法
	@Pointcut(value="execution( * cn.hncu..Person.*() )")
	public void pointCut() { //切点注解的函数没有任何函数功能，该函数只是用来承载 @Pointcut和标识它
	}
	//可以有多个切点
	@Pointcut(value="execution( * cn.hncu..Person.*(*) )")
	public void pointCut2() {
		
	}
	
	@Before(value="pointCut()") //通过标识找到对应的切点进行通知
	public void before() { //函数名可以随便取，没有实际意义，只不过规范点方便识别
		System.out.println("...before...");
	}
	@Before(value="pointCut2()") //第二个切点
	public void before2() { 
		System.out.println("...before...");
	}
	
	@After(value="pointCut()") //通过标识找到对应的切点进行通知
	public void after() { //函数名可以随便取，没有实际意义，只不过规范点方便识别
		System.out.println("...after...");
	}
	
	/* 使用@Around 注解时要注意：
	 * 	  被注解的函数一定要有 ProceedingJoinPoint p 作为函数参数， 并且要有一个 Object型 的返回值。
	 */
	@Around(value="pointCut()")
	public Object around(ProceedingJoinPoint p ) throws Throwable {
		String methdName = p.getSignature().getName(); //可以获取到被拦截的方法名等信息
		System.out.println(methdName+">>前面拦拦...");
		Object returnValue = p.proceed(); //放行
		System.out.println(methdName+">>后面拦拦...");
		return returnValue;
	}
	
	/* @AfterReturning 和  @AfterThrowing 是一对'互斥'的注解
	 * @AfterReturning：函数正常返回后通知
	 * @AfterThrowing：函数抛出异常（没抓）后通知
	 */
	@AfterReturning(value="pointCut()")
	public void afterReturning() {
		System.out.println("这是调用完成，正常返回(没有捕捉到异常)以后");
	}
	
	@AfterThrowing(value="pointCut()") 
	public void afterThrowing() {
		System.out.println("这是调用完成，发现有异常");
	}
	@AfterThrowing(value="pointCut2()") //第二个切点
	public void afterThrowing2() {
		System.out.println("这是调用完成，发现有异常");
	}
}
