package cn.hncu.aop.aspectj.v1;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import cn.hncu.aop.hello.v2.domain.Person;
/*
1. aspectj技术主要是改进之前定位切点时不够精确的问题，
如aspectj可以使用切点语言定义切点更具体的特征如方法的返回类型、方法的形参等

2. aspectj定义切点时采用的是切点语言，助理解:
    △设置切点: cut.setExpression("execution( 用切点语言写的切点表达式  )");
    △用切点语言写的切点表达式---相关知识点如下:
  1) 切点表达式格式:  返回类型   包名.[子包名.]类名.方法名(参数类型列表)
  2) "."是包名之间  或   包名与类名  或   类名与方法名  之间的间隔符
  3) ".."在包路径位置代表'任意深的目录'，在参数类型列表位置代表'任意个数与类型的参数'
  4) "*"是操作系统中的通匹符
 */
public class AspectJDemo {
	
	//演示纯java的方式实现AOP
	@Test
	public void t1() {
		//被代理的对象
		Person person = new Person();
		
		/////////////AspectJ加强处///////////////////
		//切点
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		
		//拦截 返回值 为 void 并且是cn/hncu/任意目录/Person类中的任意r开头的空参方法
		//pointcut.setExpression( "execution( void cn.hncu..Person.r*() )" );
		
		//拦截 返回值 为 void 并且是cn/hncu/任意目录/Person类中的任意r开头的参数为int类型的方法
		pointcut.setExpression( "execution( void cn.hncu..Person.r*(int) )" );
		//////////////////////////////////////////
		
		//通知
		Advice advice = new MethodInterceptor() {
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("前面拦拦...");
				Object returnValue = invocation.proceed(); //放行
				System.out.println("后面拦拦...");
				return returnValue;
			}
		};
		
		//切面=切点+通知
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
		
		//创建代理bean工厂
		ProxyFactoryBean factory = new ProxyFactoryBean();
		//添加切面方案
		factory.addAdvisor(advisor);
		//放入目标： 被代理对象
		factory.setTarget( person );
		
		Person person2 = (Person) factory.getObject();
		
		person2.run();
		person2.run(5);
		person2.eat();
		
	}
}
