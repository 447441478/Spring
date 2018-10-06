package cn.hncu.aop.hello.v2;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/*
 * 实现org.aopalliance.intercept.MethodInterceptor接口，
 * 即可实现同动态代理那样，既可以前面拦截，后面拦截，同时可以控制放行
 */
public class AroundAdviceImpl implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("前面拦拦...");
		//控制下面这一句执行就可以控制放行，也就可以实现方法拦截。
		Object returnValue = invocation.proceed(); //放行
		System.out.println("后面拦拦...");
		return returnValue;
	}


}
