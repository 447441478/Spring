package cn.hncu.ioc.v2;

import cn.hncu.ioc.v2.service.ILoginService;

public class LoginAction {
	
	//注入service
	private ILoginService service;
	//采用 Spring 注入必须 写上相应的setter/getter方法！！！
	public ILoginService getService() {
		return service;
	}
	public void setService(ILoginService service) {
		this.service = service;
	}
	
	private String username;
	private String password;
	
	public LoginAction() {
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//业务方法
	public String execute() {
		return service.login(username, password);
	}
	
}
