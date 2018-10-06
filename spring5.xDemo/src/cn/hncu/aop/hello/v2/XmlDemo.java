package cn.hncu.aop.hello.v2;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hncu.aop.hello.v2.domain.Person;
import cn.hncu.aop.hello.v2.domain.Student;

public class XmlDemo {
	
	@Test //通过拿到工厂bean再从中拿出代理bean
	public void t1_1() {
		//使用 t1.xml
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/aop/hello/v2/t1.xml");
		ProxyFactoryBean factory = appCtx.getBean(ProxyFactoryBean.class);
		Person person = (Person) factory.getObject();
		person.run();
		person.eat();//没有配置拦截
		
		
		((AbstractApplicationContext) appCtx).close();
	}
	@Test //直接拿出代理bean
	public void t1_2() {
		//使用 t1.xml
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/aop/hello/v2/t1.xml");
		Person person = (Person) appCtx.getBean("factory",Person.class); 
		person.run();
		person.eat();//没有配置拦截
		
		
		((AbstractApplicationContext) appCtx).close();
	}
	
	/* 把 切点bean和通知bean 通过匿名内部bean的方式 放在 切面bean内部
	 * 而且把被代理的bean也使用匿名内部bean的方式放在 代理bean工厂内部
	 * 
	 * 这样可以使 bean之间的关系结构清晰
	 */
	@Test 
	public void t2() {
		//使用 t2.xml
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/aop/hello/v2/t2.xml");
		Person person = (Person) appCtx.getBean("factory",Person.class); 
		person.run();
		person.eat(); //没有配置拦截
		
		
		((AbstractApplicationContext) appCtx).close();
	}
	
	/* 使用DefaultPointcutAdvisor的兄弟类 RegexpMethodPointcutAdvisor
	 * 该类把切点 表达式 融入到前面中 相对于 DefaultPointcutAdvisor 使用起来跟简便
	 */
	@Test 
	public void t3() {
		//使用 t3.xml
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/aop/hello/v2/t3.xml");
		Person person = (Person) appCtx.getBean("factory",Person.class); 
		person.run();
		person.eat();//配置拦截
		
		
		((AbstractApplicationContext) appCtx).close();
	}
	
	/* DefaultAdvisorAutoProxyCreator类 实现了 BeanPostProcessor接口，
	 * BeanPostProcessor：在每个bean的创建-->初始化过程的前/后-->放入 applicationContext 初始化过程的前/后 起作用的
	 * 在每个 bean 创建后 --> 初始化过程的前/后 都会触发 BeanPostProcessor中的方法
	 * DefaultAdvisorAutoProxyCreator类会根据 容器中的 Advisor 的规则把每个符合 的bean进行自动代理
	 * 然后把代理的bean放入 applicationContext 容器中
	 */
	@Test 
	public void t4() {
		//使用 t4.xml
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/aop/hello/v2/t4.xml");
		Person person = (Person) appCtx.getBean(Person.class); 
		person.run();
		person.eat();//配置拦截
		/* 该类中有 切入点 的函数，所以会被 DefaultAdvisorAutoProxyCreator 检测到
		 * 并且放入applicationContext中是代理后的对象，即获得到的是 代理后的对象
		 */
		System.out.println( person.getClass() ); 
		
		Student student = appCtx.getBean(Student.class);
		/* 该类中没有 切入点 的函数，所以不会被 DefaultAdvisorAutoProxyCreator 检测到
		 */
		System.out.println( student.getClass() ); 
		
		
		((AbstractApplicationContext) appCtx).close();
	}
	@Test 
	public void t5() {
		//使用 t5.xml
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/aop/hello/v2/t5.xml");
		Person person = (Person) appCtx.getBean(Person.class); 
		person.run();
		person.eat();//配置拦截
		
		System.out.println( person.getClass() ); 
		
		Student student = appCtx.getBean(Student.class);

		System.out.println( student.getClass() ); 
		
		
		((AbstractApplicationContext) appCtx).close();
	}
	
}
