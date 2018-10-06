package cn.hncu.ioc.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * 客户端，调用后台 登录 业务
 */
public class Client {
	
	public static void main(String[] args) {
		// 1加载容器  读取 当前包中的xml文件
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("cn/hncu/ioc/v2/v2.xml");
		
		//获取一个 处理 登录的 Action
		LoginAction loginAction = appCtx.getBean(LoginAction.class);
		
		//执行业务方法
		loginAction.execute();
		
		/* 修改 v2.xml文件中 ser1中'dao'的 ref值为 dao2,
		 * 就可把访问的数据库改成Oracle，而不用改任何代码。
		 * IoC/DI 最强大的功能就是  "解耦" ！！！
		 */
		
		
		((AbstractApplicationContext) appCtx).close();
	}
}
