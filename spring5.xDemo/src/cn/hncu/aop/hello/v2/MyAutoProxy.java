package cn.hncu.aop.hello.v2;

import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/* 实现BeanPostProcessor接口可以监听到 bean初始化前/后时刻，并且做些动作
 * 实现ApplicationContextAware接口 可以监听到 applicationContext初始化，通过实现抽象方法就可以获取到 applicationContext 容器
 */
public class MyAutoProxy implements BeanPostProcessor,ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	private List<Object> list;
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if( list != null && list.contains( bean.getClass().getName() ) ) {
			Advisor advisor = applicationContext.getBean(Advisor.class);
			ProxyFactoryBean factory = new ProxyFactoryBean();
			factory.addAdvisor(advisor);
			factory.setTarget( bean );
			return factory.getObject();
		}
		return bean;
	}
	
	/* 观察者模式：
	 * applicationContext初识化后会调用setApplicationContext()方法，
	 * 并且被setApplicationContext作为参数传递给实现了ApplicationContextAware接口的具体类
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
	}
	
}
