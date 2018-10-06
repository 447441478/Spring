package cn.hncu.aop.hello.v1;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import cn.hncu.aop.hello.v1.domain.Person;

public class CGLibProxy implements MethodInterceptor {

	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		System.out.println("123456");
		return arg3.invokeSuper(arg0, arg2);
	}
	
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass( Person.class );
		enhancer.setCallback( new CGLibProxy() );
		
		Person person = (Person) enhancer.create();
		person.run();
	}
	
}
