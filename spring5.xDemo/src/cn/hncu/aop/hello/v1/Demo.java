package cn.hncu.aop.hello.v1;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

import cn.hncu.aop.hello.v1.domain.Person;

/*AOP中的一个重要等式: 
 *     切面=切点+通知  
 *     advisor=pointCut+advice
 *     切面: 定义的一个拦截事件(动作)
 *     切点: 要拦截哪些(个)类的哪些(个)方法
 *     通知: 定义在方法的前面、后面、环绕、出异常 还是 正常返回的时候拦
 */
//存java实现 AOP
public class Demo {
	public static void main(String[] args) {
		//1 被切面的对象(被代理的对象)
		Person person = new Person();
		
		//2.2 切点  
		JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
		//拦截的方法的表达式
		pointcut.setPattern(".*run.*");
		
		//2.3 通知  
		Advice advice = new MethodBeforeAdvice() { 
			//这种是无法进行方法拦截的，只是在方法执行前执行一段代码
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("前面做些事...");
			}
			
		};
		
		// 2.1切面入口  --- 切面=切点+通知  
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
		
		//创建代理bean工厂
		ProxyFactoryBean factory = new ProxyFactoryBean();
		
		//把切面处理方案添加到工厂中
		factory.addAdvisor(advisor); 
		
		//把需要进行切面的对象放入工厂
		factory.setTarget( person ); 
		
		//拿出代理后的对象
		Person proxyPerson = (Person) factory.getObject(); 
		
		proxyPerson.run(); //执行run
		
		System.out.println( proxyPerson.getClass().getSuperclass() );
		System.out.println( proxyPerson.hashCode() );
		
		Person proxyPerson2 = (Person) factory.getObject();
		System.out.println( proxyPerson2.hashCode() );
		
	}
}
